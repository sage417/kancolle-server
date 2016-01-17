/**
 * 
 */
package com.kancolle.server.service.ship.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.kancolle.server.controller.kcsapi.form.ship.Ship3Form;
import com.kancolle.server.controller.kcsapi.form.ship.ShipChargeForm;
import com.kancolle.server.controller.kcsapi.form.ship.ShipPowerUpForm;
import com.kancolle.server.controller.kcsapi.form.ship.ShipSetSlotForm;
import com.kancolle.server.dao.ship.MemberShipDao;
import com.kancolle.server.model.event.PowUpEvent;
import com.kancolle.server.model.kcsapi.charge.ChargeModel;
import com.kancolle.server.model.kcsapi.ship.MemberShipLockResult;
import com.kancolle.server.model.kcsapi.ship.MemberShipPowerupResult;
import com.kancolle.server.model.kcsapi.ship.Ship3Result;
import com.kancolle.server.model.kcsapi.ship.ShipDeckResult;
import com.kancolle.server.model.po.common.MaxMinValue;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.model.po.member.MemberNdock;
import com.kancolle.server.model.po.resource.MemberRescourceResult;
import com.kancolle.server.model.po.resource.Resource;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.service.deckport.MemberDeckPortService;
import com.kancolle.server.service.member.MemberNdockService;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.service.ship.utils.ChargeType;
import com.kancolle.server.service.slotitem.MemberSlotItemService;
import com.kancolle.server.utils.logic.MemberShipUtils;
import com.kancolle.server.utils.logic.common.LvUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;
import static com.kancolle.server.model.kcsapi.ship.MemberShipPowerupResult.RESULT_FAILED;
import static com.kancolle.server.model.kcsapi.ship.MemberShipPowerupResult.RESULT_SUCCESS;
import static com.kancolle.server.utils.logic.MemberShipUtils.calMemberShipPropertiesViaSlot;

/**
 * @author J.K.SAGE
 * @Date 2015年6月23日
 *
 */

@Service
public class MemberShipServiceImpl implements MemberShipService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberShipServiceImpl.class);

    @Autowired
    private MemberShipDao memberShipDao;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ShipService shipService;

    @Autowired
    private MemberResourceService memberResourceService;

    @Autowired
    private MemberSlotItemService memberSlotItemService;

    @Autowired
    private MemberDeckPortService memberDeckPortService;

    @Autowired
    private MemberNdockService memberNdockService;

    @Autowired
    private EventBus eventBus;

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
    public void destoryShips(String member_id, List<MemberShip> destoryShips) {
        long[] InNdockshipIds = memberNdockService.getMemberNdocks(member_id).stream().mapToLong(MemberNdock::getMemberShipId).toArray();

        for (MemberShip destoryShip : destoryShips) {
            if (destoryShip == null) {
                throw new IllegalArgumentException();
            }
            long destoryShipId = destoryShip.getMemberShipId();
            MemberDeckPort deckport = memberDeckPortService.getMemberDeckPortContainsMemberShip(member_id, destoryShipId);

            if (deckport != null) {
                if (deckport.getDeckId() == 1 && deckport.getShip()[0] == destoryShipId)
                    throw new IllegalStateException("不能解體旗艦");
                if (deckport.getMemberId() > 0)
                    throw new IllegalStateException("不能解体远征舰队中的舰娘");
            }

            if (ArrayUtils.contains(InNdockshipIds, destoryShipId))
                throw new IllegalStateException("不能解体入渠中的舰娘");

            if (destoryShip.isLocked())
                throw new IllegalStateException("不能解体上锁的舰娘");

            if (destoryShip.isLockedEquip())
                throw new IllegalStateException("不能解体拥有上锁装备的舰娘");
        }
        List<Long> member_ship_ids = destoryShips.stream().map(MemberShip::getMemberShipId).collect(Collectors.toList());
        memberShipDao.deleteMemberShips(member_id, member_ship_ids);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public MemberRescourceResult destroyShipAndReturnResource(String member_id, Long member_ship_id) {
        MemberShip memberShip = getMemberShip(member_id, member_ship_id);
        if (memberShip == null) {
            throw new IllegalArgumentException();
        }

        destoryShips(member_id, Collections.singletonList(memberShip));

        memberResourceService.increaseMaterial(member_id, memberShip.getShip().getBrokenArray());
        Resource memberResource = memberResourceService.getMemberResouce(member_id);

        return new MemberRescourceResult(memberResource);
    }

    @Override
    public int getCountOfMemberShip(String member_id) {
        return memberShipDao.selectCountOfMemberShips(member_id);
    }

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
    public Ship3Result getShip3(String member_id, Ship3Form form) {
        Long memberShipId = form.getApi_shipid();
        int sortKey = form.getApi_sort_key();
        int sort_order = form.getSpi_sort_order();
        return new Ship3Result(getMemberShip(member_id, memberShipId), memberDeckPortService.getMemberDeckPorts(member_id), memberSlotItemService.getUnsetSlotMap(member_id));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void increaseMemberShipExp(MemberShip memberShip, int exp) {
        checkNotNull(memberShip);
        checkArgument(exp >= 0);

        // 当前等级
        int nowLv = memberShip.getLv();
        // 99级和150级不获得经验
        if (LvUtils.isShipLVOver(nowLv))
            return;
        // 当前总经验
        long[] exps = memberShip.getExp();
        // 获得经验后总经验（未修正前）
        long afterExp = exps[0] + exp;
        // 获得经验后等级（经过修正）
        int afterLv = shipService.getShipLVByExp(afterExp);

        if (LvUtils.isShipLVOver(afterLv))
            // 获得经验后总经验（经过修正）
            afterExp = shipService.getSumExpByLevel(afterLv);

        // 下一级所需总经验
        long nextLvExp = shipService.getSumExpByLevel(afterLv + 1);

        int progress = (int) Math.floorDiv(100L * (afterExp - shipService.getSumExpByLevel(afterLv)), shipService.getNextLVExp(afterLv));

        memberShip.setLv(afterLv);
        memberShip.setExp(new long[] { afterExp, nextLvExp - afterExp, progress });

        memberShipDao.updateMemberExp(memberShip);
        // ----------属性成长-----------//
        Ship ship = memberShip.getShip();
        int shipKaihi = LvUtils.getLvValue(ship.getKaihi(), afterLv);
        int shipTaisen = LvUtils.getLvValue(ship.getKaihi(), afterLv);
        int shipSakuteki = LvUtils.getLvValue(ship.getKaihi(), afterLv);
        memberShip.getTaisen().setMinValue(shipTaisen);
        memberShip.getKaihi().setMinValue(shipKaihi);
        memberShip.getSakuteki().setMinValue(shipSakuteki);
        memberShipDao.updateMemberShipSlotValue(memberShip);
        // ----------属性成长-----------//
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
        Long target_ship_id = form.getApi_id();
        List<Long> powup_ship_ids = form.getApi_id_items();

        MemberShip targetShip = getMemberShip(member_id, target_ship_id);
        if (targetShip == null) {
            LOGGER.warn("用户ID:{} 获取不存在的舰娘{}", member_id, target_ship_id);
            throw new IllegalArgumentException();
        }

        // TODO 合成舰娘不能在入渠状态
        // TODO 合成舰娘不能在远征状态
        MemberDeckPort deckport = memberDeckPortService.getMemberDeckPortContainsMemberShip(member_id, target_ship_id);

        int[] powUpMaxArray = MemberShipUtils.getShipPowupMaxArray(targetShip.getShip());

        int[] powupArray = new int[] { 0, 0, 0, 0 };
        float powupLuck = 0f;

        List<MemberShip> powupShips = Lists.newArrayListWithCapacity(powup_ship_ids.size());

        for (Long powup_ship_id : powup_ship_ids) {
            MemberShip powupShip = getMemberShip(member_id, powup_ship_id);
            if (powupShip == null) {
                LOGGER.warn("用户ID:{} 获取不存在的舰娘{}", member_id, target_ship_id);
                throw new IllegalArgumentException();
            }
            if (powupShip.isLocked() || powupShip.isLockedEquip()) {
                LOGGER.warn("用户ID:{} 请求解体上锁的舰娘{}", member_id, target_ship_id);
                throw new IllegalStateException();
            }
            powupShips.add(powupShip);

            // -----------如果在舰队中则退出舰队-------------//

            deckport = memberDeckPortService.getMemberDeckPortContainsMemberShip(member_id, powup_ship_id);
            // 如果合成舰娘在舰队中
            if (deckport != null) {
                if (deckport.getMission()[0] != 0)
                    throw new IllegalStateException();
                memberDeckPortService.removeDeckPortShips(deckport, Collections.singletonList(powupShip));
            }

            // -----------如果在舰队中则退出舰队-------------//

            // -----------解除装备并解体装备-------------//
            if (!powupShip.getSlot().isEmpty()) {
                List<MemberSlotItem> removeSlotitems = unsetAllSlotitems(powupShip);
                memberSlotItemService.destorySlotitems(member_id, removeSlotitems);
            }
            // -----------解除装备并解体装备-------------//

            int[] shippowup = powupShip.getShip().getPowUpArray();
            for (int i = 0; i < shippowup.length; i++) {
                powupArray[i] += shippowup[i];
            }

            // ------------增加运------------//
            if (powupShip.getShip().getShipId() == 163) {
                powupLuck += 1.2f;
            } else if (powupShip.getShip().getShipId() == 402) {
                powupLuck += 1.6f;
            }
            // ------------增加运------------//
        }

        boolean powUpResult = false;

        for (int i = 0; i < powupArray.length; i++) {
            if (powupArray[i] > 0) {
                // 奖励补正
                powupArray[i] += (powupArray[i] + 1) / 5;
                // 随机补正
                powupArray[i] /= RandomUtils.nextInt(1, 3);
                // 最大值补正
                if (powupArray[i] > 0) {
                    powUpResult = true;
                    targetShip.getKyouka()[i] = targetShip.getKyouka()[i] + powupArray[i];
                    if (targetShip.getKyouka()[i] > powUpMaxArray[i])
                        targetShip.getKyouka()[i] = powUpMaxArray[i];
                }
            }
        }

        int addLuck = (int) powupLuck;

        if (addLuck > 0) {
            if (addLuck < 8)
                // 幸运随机补正
                addLuck /= RandomUtils.nextInt(1, 3);
            if (addLuck > 0) {
                powUpResult = true;
                MaxMinValue luck = targetShip.getLucky();
                luck.setMinValue(luck.getMinValue() + addLuck);
                if (luck.getMinValue() > luck.getMaxValue())
                    // 幸运最大值补正
                    luck.setMinValue(luck.getMaxValue());
            }
        }
        updateShipProperties(targetShip);

        destoryShips(member_id, powupShips);

        eventBus.post(new PowUpEvent(member_id));

        return new MemberShipPowerupResult(powUpResult ? RESULT_SUCCESS : RESULT_FAILED, targetShip, memberDeckPortService.getMemberDeckPorts(member_id));
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
        if (memberShip == null)
            throw new IllegalArgumentException(String.format("無法找到艦娘,member_id:{},ship_id:{}", member_id, memberShipId));

        List<MemberSlotItem> slotItems = memberShip.getSlot();

        if (memberSlotItemId == -1L) {
            List<MemberSlotItem> rmSlot = Collections.singletonList(slotItems.get(slotIndex));
            slotItems.removeAll(rmSlot);
            memberShipDao.removeSlot(memberShip, rmSlot);
        } else {
            MemberSlotItem memberSlotItem = memberSlotItemService.getMemberSlotItem(member_id, memberSlotItemId);

            if (memberSlotItem == null) {
                throw new IllegalArgumentException(String.format("無法找到裝備,member_id:{},slotitem_id:{}", member_id, memberSlotItemId));
            }

            if (!shipService.canEquip(memberShip.getShip().getType(), memberSlotItem.getSlotItem().getType()[2]))
                throw new IllegalArgumentException(String.format("該類型裝備艦娘無法裝備,member_ship:{},slotitem:{}", memberShip, memberSlotItem));

            if (slotIndex >= slotItems.size()) {
                slotItems.add(memberSlotItem);
                memberShipDao.addSlot(memberShip, memberSlotItem);
            } else {
                MemberSlotItem replacedSlotItem = slotItems.set(slotIndex, memberSlotItem);
                memberShipDao.replaceSlot(memberShip, replacedSlotItem, memberSlotItem);
            }
        }
        updateShipProperties(memberShip);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.SUPPORTS)
    public List<MemberSlotItem> unsetAllSlotitems(MemberShip memberShip) {
        List<MemberSlotItem> removeSlots = ImmutableList.copyOf(memberShip.getSlot());
        memberShip.getSlot().removeAll(removeSlots);
        memberShipDao.removeSlot(memberShip, removeSlots);
        return removeSlots;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void unsetslotAll(String member_id, Long memberShip_id) {
        MemberShip memberShip = getMemberShip(member_id, memberShip_id);
        if (memberShip == null) {
            LOGGER.warn("用户ID{}查询不存在的舰娘{}", member_id, memberShip_id);
            throw new IllegalArgumentException();
        }
        unsetAllSlotitems(memberShip);
        updateShipProperties(memberShip);
    }

    /**
     * 更新装备、 近现代改修需要调用此方法<br>
     * 更新幸运值不需要<br>
     * 
     * @param memberShip
     */
    private void updateShipProperties(MemberShip memberShip) {
        calMemberShipPropertiesViaSlot(memberShip);
        memberShipDao.updateMemberShipSlotValue(memberShip);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.SUPPORTS)
    public void updateHpAndCond(MemberShip memberShip) {
        memberShipDao.updateMemberShipHpAndCond(memberShip);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.SUPPORTS)
    public MemberShip createShip(String member_id, int createShipId) {
        Member member = memberService.getMember(member_id);
        checkState(getCountOfMemberShip(member_id) < member.getMaxChara());
        return memberShipDao.createShip(member_id, createShipId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.SUPPORTS)
    public void updateShipOnSlot(MemberShip keyShip) {
        Arrays.stream(keyShip.getOnslot()).forEach(eq -> checkArgument(eq > -1));
        memberShipDao.updateShipOnSlot(keyShip.getMemberId(), keyShip.getMemberShipId(), keyShip.getOnslot());
    }

    @Override
    public ShipDeckResult getShipDeck(String member_id, int deckPortId) {
        MemberDeckPort deckPort = memberDeckPortService.getUnNullableMemberDeckPort(member_id,deckPortId);
        return new ShipDeckResult(deckPort.getShips(), memberDeckPortService.getMemberDeckPorts(member_id));
    }
}
