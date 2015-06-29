/**
 * 
 */
package com.kancolle.server.service.ship.impl;

import static com.kancolle.server.utils.logic.MemberShipUtils.calMemberShipPropertiesViaSlot;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableList;
import com.kancolle.server.controller.kcsapi.form.ship.Ship3Form;
import com.kancolle.server.controller.kcsapi.form.ship.ShipChargeForm;
import com.kancolle.server.controller.kcsapi.form.ship.ShipSetSlotForm;
import com.kancolle.server.dao.port.PortDao;
import com.kancolle.server.dao.ship.MemberShipDao;
import com.kancolle.server.model.kcsapi.charge.ChargeModel;
import com.kancolle.server.model.kcsapi.ship.Ship3Result;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.service.ship.utils.ChargeType;
import com.kancolle.server.service.slotitem.MemberSlotItemService;
import com.kancolle.server.utils.logic.LVUtil;

/**
 * @author J.K.SAGE
 * @Date 2015年6月23日
 *
 */

@Service
public class MemberShipServiceImpl implements MemberShipService {
    @Autowired
    private MemberShipDao memberShipDao;

    @Autowired
    private PortDao portDao;

    @Autowired
    private ShipService shipService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberResourceService memberResourceService;

    @Autowired
    private MemberSlotItemService memberSlotItemService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberShip getMemberShip(String member_id, long ship_id) {
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
        memberResourceService.consumeResource(Long.valueOf(member_id), chargeFuel, chargeBull, 0, comsumeBauxite, 0, 0, 0, 0);
        memberShipDao.chargeMemberShips(member_id, memberShipIds, chargeKind);

        return new ChargeModel(memberShips, memberResourceService.getMemberResouce(Long.valueOf(member_id)), comsumeBauxite > 0);
    }

    @Override
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

        // TODO 舰娘属性增长

        memberShipDao.updateMemberExp(memberShip);
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
        return new Ship3Result(getMemberShip(member_id, memberShipId), portDao.getDeckPort(member_id), memberService.getUnsetSlot(member_id));
    }
}
