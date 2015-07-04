/**
 * 
 */
package com.kancolle.server.service.ship.impl;

import static com.kancolle.server.model.po.ship.MemberShipPowerupResult.RESULT_FAILED;
import static com.kancolle.server.model.po.ship.MemberShipPowerupResult.RESULT_SUCCESS;
import static com.kancolle.server.utils.logic.MemberShipUtils.calMemberShipPropertiesViaSlot;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableList;
import com.kancolle.server.controller.kcsapi.form.ship.Ship3Form;
import com.kancolle.server.controller.kcsapi.form.ship.ShipChargeForm;
import com.kancolle.server.controller.kcsapi.form.ship.ShipPowerUpForm;
import com.kancolle.server.controller.kcsapi.form.ship.ShipSetSlotForm;
import com.kancolle.server.dao.ship.MemberShipDao;
import com.kancolle.server.model.kcsapi.charge.ChargeModel;
import com.kancolle.server.model.kcsapi.ship.MemberShipLockResult;
import com.kancolle.server.model.kcsapi.ship.Ship3Result;
import com.kancolle.server.model.po.common.MaxMinValue;
import com.kancolle.server.model.po.member.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.MemberShipPowerupResult;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.service.member.MemberDeckPortService;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.service.ship.utils.ChargeType;
import com.kancolle.server.service.slotitem.MemberSlotItemService;
import com.kancolle.server.utils.logic.LVUtil;
import com.kancolle.server.utils.logic.MemberShipUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年6月23日
 *
 */

@Service
public class MemberShipServiceImpl implements MemberShipService {
    private static final Random r = new Random();

    @Autowired
    private MemberShipDao memberShipDao;

    @Autowired
    private ShipService shipService;

    @Autowired
    private MemberResourceService memberResourceService;

    @Autowired
    private MemberSlotItemService memberSlotItemService;

    @Autowired
    private MemberDeckPortService memberDeckPortService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberShip getMemberShip(String member_id, Long ship_id) {
        return memberShipDao.selectMemberShip(member_id, ship_id);
    }

    @Override
    public List<MemberShip> getMemberShips(String memberId) {
        return memberShipDao.selectMemberShips(memberId);
    }

    @Override
    public int getCountOfMemberShip(String member_id) {
        return memberShipDao.selectCountOfMemberShips(member_id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public ChargeModel chargeShips(String member_id, ShipChargeForm form) {
        List<Long> memberShipIds = form.getApi_id_items();
        int chargeKind = form.getApi_kind();
        int onslot = form.getApi_onslot();

        List<MemberShip> memberShips = memberShipIds.stream().map(memberShipId -> getMemberShip(member_id, memberShipId)).filter(memberShip -> memberShip != null).collect(Collectors.toList());
        if (memberShips.size() != form.getApi_id_items().size()) {
            // TODO 记录
            throw new IllegalArgumentException();
        }

        ChargeType chargeType = ChargeType.getChargeType(chargeKind);

        int chargeFuel = 0;
        int chargeBull = 0;
        int comsumeBauxite = 0;

        for (MemberShip memberShip : memberShips) {
            Ship ship = memberShip.getShip();

            if (chargeType == ChargeType.ALL || chargeType == ChargeType.FUEL) {
                chargeFuel += ship.getFuelMax() - memberShip.getFuel();
                memberShip.setFuel(ship.getFuelMax());

                for (int i = 0; i < ship.getMaxEq().length; i++) {
                    if (ship.getMaxEq()[i] > 0) {
                        int comsumePlaneCount = ship.getMaxEq()[i] - memberShip.getOnslot()[i];
                        if (comsumePlaneCount > 0)
                            comsumeBauxite += 5 * comsumePlaneCount;
                    }
                }
                memberShip.setOnslot(memberShip.getShip().getMaxEq());
            }

            if (chargeType == ChargeType.ALL || chargeType == ChargeType.BULL) {
                chargeBull += ship.getBullMax() - memberShip.getBull();
                memberShip.setBull(ship.getBullMax());
            }

        }
        memberResourceService.consumeResource(member_id, chargeFuel, chargeBull, 0, comsumeBauxite, 0, 0, 0, 0);
        memberShipDao.chargeMemberShips(member_id, memberShipIds, chargeKind);

        return new ChargeModel(memberShips, memberResourceService.getMemberResouce(member_id), comsumeBauxite > 0);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void increaseMemberShipExp(MemberShip memberShip, int exp) {
        if (memberShip == null || exp < 0) {
            throw new IllegalArgumentException();
        }

        // 当前等级
        int nowLv = memberShip.getLv();
        // 99级和150级不获得经验
        if (LVUtil.isShipLVOver(nowLv))
            return;
        // 当前总经验
        long[] exps = memberShip.getExp();
        // 获得经验后总经验（未修正前）
        long afterExp = exps[0] + exp;
        // 获得经验后等级（经过修正）
        int afterLv = shipService.getShipLVByExp(afterExp);

        if (LVUtil.isShipLVOver(afterLv))
            // 获得经验后总经验（经过修正）
            afterExp = shipService.getSumExpByLevel(afterLv);

        // 下一级所需总经验
        long nextLvExp = afterLv > nowLv ? shipService.getSumExpByLevel(afterLv + 1) : exps[1];

        int progress = (int) Math.floorDiv(100L * (afterExp - shipService.getSumExpByLevel(afterLv)), shipService.getNextLVExp(afterLv));

        memberShip.setLv(afterLv);
        memberShip.setExp(new long[] { afterExp, nextLvExp - afterExp, progress });

        memberShipDao.updateMemberExp(memberShip);
        // ----------属性成长-----------//
        Ship ship = memberShip.getShip();
        int shipKaihi = LVUtil.getLvValue(ship.getKaihi(), afterLv);
        int shipTaisen = LVUtil.getLvValue(ship.getKaihi(), afterLv);
        int shipSakuteki = LVUtil.getLvValue(ship.getKaihi(), afterLv);
        memberShip.getTaisen().setMinValue(shipTaisen);
        memberShip.getKaihi().setMinValue(shipKaihi);
        memberShip.getSakuteki().setMinValue(shipSakuteki);
        memberShipDao.updateMemberShipSlotValue(memberShip);
        // ----------属性成长-----------//
    }

    /**
     * 改装舰娘的装备到底会发生什么？<br>
     * 1. t_member_ship slot 装备顺序发生改变<br>
     * 2. t_member_ship_slot_mapping 增删改装备记录<br>
     * 3. t_member 舰娘的属性根据对应装备的属性发生变化<br>
     * <br>
     * 注意：<br>
     * 根据舰娘船只种类所装备的装备种类有限制<br>
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void setSlot(String member_id, ShipSetSlotForm form) {
        Long memberShipId = form.getApi_id();
        Long memberSlotItemId = form.getApi_item_id();
        int slotIndex = form.getApi_slot_idx();

        MemberShip memberShip = getMemberShip(member_id, memberShipId);
        if (memberShip == null) {
            // TODO
            throw new IllegalArgumentException();
        }

        List<MemberSlotItem> slotItems = memberShip.getSlot();

        if (memberSlotItemId == -1L) {
            List<MemberSlotItem> rmSlot = Collections.singletonList(slotItems.get(slotIndex));
            slotItems.removeAll(rmSlot);
            memberShipDao.removeSlot(memberShip, rmSlot);
        } else {
            MemberSlotItem memberSlotItem = memberSlotItemService.getMemberSlotItem(member_id, memberSlotItemId);

            if (memberSlotItem == null) {
                // TODO
                throw new IllegalArgumentException();
            }

            if (!shipService.canEquip(memberShip.getShip().getType(), memberSlotItem.getSlotItem().getType()[2])) {
                // TODO
                throw new IllegalArgumentException();
            }

            if (slotIndex >= slotItems.size()) {
                slotItems.add(memberSlotItem);
                memberShipDao.addSlot(memberShip, memberSlotItem);
            } else {
                MemberSlotItem replacedSlotItem = slotItems.set(slotIndex, memberSlotItem);
                memberShipDao.replaceSlot(memberShip, replacedSlotItem, memberSlotItem);
            }
        }
        calMemberShipPropertiesViaSlot(memberShip);
        memberShipDao.updateMemberShipSlotValue(memberShip);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void unsetslotAll(String member_id, Long memberShip_id) {
        MemberShip memberShip = getMemberShip(member_id, memberShip_id);
        if (memberShip == null) {
            // TODO
            throw new IllegalArgumentException();
        }
        List<MemberSlotItem> removeSlots = ImmutableList.copyOf(memberShip.getSlot());
        memberShip.getSlot().removeAll(removeSlots);
        memberShipDao.removeSlot(memberShip, removeSlots);
        calMemberShipPropertiesViaSlot(memberShip);
        memberShipDao.updateMemberShipSlotValue(memberShip);
    }

    @Override
    public Ship3Result getShip3(String member_id, Ship3Form form) {
        Long memberShipId = form.getApi_shipid();
        int sortKey = form.getApi_sort_key();
        int sort_order = form.getSpi_sort_order();
        return new Ship3Result(getMemberShip(member_id, memberShipId), memberDeckPortService.getMemberDeckPorts(member_id), memberSlotItemService.getUnsetSlot(member_id));
    }

    @Override
    public MemberShipLockResult lock(String member_id, Long member_ship_id) {
        MemberShip memberShip = getMemberShip(member_id, member_ship_id);
        if (memberShip == null) {
            throw new IllegalArgumentException();
        }
        Boolean lock = Boolean.valueOf(!memberShip.isLocked());
        memberShipDao.updateMemberShipLockStatue(member_id, member_ship_id, lock);
        return new MemberShipLockResult(lock);
    }

    /**
     * 近现代改修需要做些什么？<br>
     * 检查舰娘是否上锁<br>
     * 检查舰娘身上是否有上锁装备<br>
     * 如果舰娘在舰队中则检查舰队状态是否为空闲状态<br>
     * 删除舰队中舰娘的信息（两处）<br>
     * 删除舰娘身上装备信息（两处）<br>
     * 删除舰娘<br>
     * 更新增加的属性<br>
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public MemberShipPowerupResult powerup(String member_id, ShipPowerUpForm form) {
        Long ship_id = form.getApi_id();
        List<Long> member_ship_ids = form.getApi_id_items();

        MemberShip memberShip = getMemberShip(member_id, ship_id);
        if (memberShip == null) {
            throw new IllegalArgumentException();
        }

        Ship ship = memberShip.getShip();

        // length = 4
        int[] powUpMaxArray = MemberShipUtils.getShipPowupMaxArray(ship);
        // length = 4
        int[] powUpArray = new int[] { 0, 0, 0, 0 };
        float powupLuck = 0f;

        for (Long id : member_ship_ids) {
            MemberShip powupShip = getMemberShip(member_id, id);
            if (powupShip == null) {
                throw new IllegalArgumentException();
            }
            if (powupShip.isLocked() || powupShip.isLockedEquip()) {
                throw new IllegalStateException();
            }
            MemberDeckPort deckport = memberDeckPortService.getMemberDeckPortContainsMemberShip(member_id, id);
            if (deckport != null) {
                if (deckport.getMission()[0] != 0)
                    throw new IllegalStateException();
                memberDeckPortService.removeDeckPortShips(deckport, Collections.singletonList(powupShip));
            }

            if (!powupShip.getSlot().isEmpty()) {
                List<Long> slotitem_ids = powupShip.getSlot().stream().map(MemberSlotItem::getMemberSlotItemId).collect(Collectors.toList());
                unsetslotAll(member_id, id);
                memberSlotItemService.distorySlotitems(member_id, slotitem_ids);
            }

            int[] shippowup = powupShip.getShip().getPowUpArray();
            for (int i = 0; i < shippowup.length; i++) {
                powUpArray[i] += shippowup[i];
            }
            if (powupShip.getShip().getShipId() == 163) {
                powupLuck += 1.2f;
            } else if (powupShip.getShip().getShipId() == 402) {
                powupLuck += 1.6f;
            }
        }

        boolean powUpResult = false;

        for (int i = 0; i < powUpArray.length; i++) {
            if (powUpArray[i] > 0) {
                // 奖励补正
                powUpArray[i] += (powUpArray[i] + 1) / 5;
                // 随机补正
                powUpArray[i] /= (r.nextInt(2) + 1);
                // 最大值补正
                if (powUpArray[i] > 0) {
                    powUpResult = true;
                    memberShip.getKyouka()[i] = memberShip.getKyouka()[i] + powUpArray[i];
                    if (memberShip.getKyouka()[i] > powUpMaxArray[i])
                        memberShip.getKyouka()[i] = powUpMaxArray[i];
                }
            }
        }

        int addLuck = (int) powupLuck;

        if (addLuck > 0) {
            if (addLuck < 8)
                // 幸运随机补正
                addLuck /= (r.nextInt(2) + 1);
            if (addLuck > 0) {
                powUpResult = true;
                MaxMinValue luck = memberShip.getLucky();
                luck.setMinValue(luck.getMinValue() + addLuck);
                if (luck.getMinValue() > luck.getMaxValue())
                    // 幸运最大值补正
                    luck.setMinValue(luck.getMaxValue());
            }
        }
        calMemberShipPropertiesViaSlot(memberShip);
        memberShipDao.updateMemberShipSlotValue(memberShip);

        destoryShips(member_id, member_ship_ids);

        return new MemberShipPowerupResult(powUpResult ? RESULT_SUCCESS : RESULT_FAILED, memberShip, memberDeckPortService.getMemberDeckPorts(member_id));
    }

    @Override
    public void destoryShips(String member_id, List<Long> member_ship_ids) {
        for (Long member_ship_id : member_ship_ids) {
            MemberShip memberShip = getMemberShip(member_id, member_ship_id);
            if (memberShip == null || memberShip.isLocked() || memberShip.isLockedEquip()) {
                throw new IllegalStateException();
            }
        }
        memberShipDao.deleteMemberShips(member_id, member_ship_ids);
    }
}
