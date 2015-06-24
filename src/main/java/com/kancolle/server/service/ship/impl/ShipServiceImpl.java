package com.kancolle.server.service.ship.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kancolle.server.controller.kcsapi.form.ship.ShipChargeForm;
import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.model.kcsapi.charge.ChargeModel;
import com.kancolle.server.model.po.resource.Resource;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.service.ship.enums.ChargeType;
import com.kancolle.server.utils.logic.LVUtil;

@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private ShipDao shipDao;

    @Autowired
    private MemberResourceService memberResourceService;

    @Autowired
    private MemberShipService memberShipService;

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
        int afterLv = shipDao.getShipLVByExp(afterExp);

        if (LVUtil.isShipLVOver(afterLv))
            // 获得经验后总经验（经过修正）
            afterExp = this.getSumExpByLevel(afterLv);

        // 下一级所需总经验
        long nextLvExp = afterLv > nowLv ? this.getSumExpByLevel(afterLv + 1) : exps[1];

        int progress = (int) Math.floorDiv(100L * (afterExp - this.getSumExpByLevel(afterLv)), this.getNextLVExp(afterLv));

        memberShip.setLv(afterLv);
        memberShip.setExp(new long[] { afterExp, nextLvExp - afterExp, progress });

        // TODO 舰娘属性增长

        shipDao.update(memberShip);
    }

    /**
     * 获取舰娘升级到下一级所需经验（差分经验）
     * 
     * @param nowLevel
     *            当前等级
     * @return
     */
    private long getNextLVExp(int nowLevel) {
        return getTargetLVExp(nowLevel, nowLevel + 1);
    }

    private long getTargetLVExp(int startLevel, int targetLevel) {
        return getSumExpByLevel(targetLevel) - getSumExpByLevel(startLevel);
    }

    /**
     * 获取舰娘所需要到此等级的总经验
     */
    @Override
    public long getSumExpByLevel(int level) {
        return shipDao.getNeedExpByLevel(level);
    }

    @Override
    public int getCountOfMemberShip(String member_id) {
        return shipDao.selectCountOfMemberShip(member_id);
    }

    @Override
    public void consume(MemberShip memberShip, boolean fuel, boolean bull) {
        Ship ship = memberShip.getShip();
        if (fuel)
            memberShip.setFuel(memberShip.getFuel() - Math.round(0.2f * ship.getFuelMax()));

        if (bull)
            memberShip.setBull(memberShip.getBull() - Math.round(0.2f * ship.getBullMax()));
        shipDao.update(memberShip);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    private boolean charge(MemberShip memberShip, ChargeType chargeType) {
        Ship ship = memberShip.getShip();

        int chargeFuel = 0;
        int chargeBull = 0;
        int comsumeBauxite = 0;

        if (chargeType == ChargeType.ALL || chargeType == ChargeType.FUEL) {
            chargeFuel = ship.getFuelMax() - memberShip.getFuel();
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
            chargeBull = ship.getBullMax() - memberShip.getBull();
            memberShip.setBull(ship.getBullMax());
        }
        // 扣除资源
        memberResourceService.consumeResource(memberShip.getMemberId(), chargeFuel, chargeBull, 0, comsumeBauxite, 0, 0, 0, 0);
        shipDao.update(memberShip);

        return comsumeBauxite > 0;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public ChargeModel chargeShips(String member_id, ShipChargeForm form) {

        List<MemberShip> actualMemberShips = form.getApi_id_items().stream().map(memberShipId -> memberShipService.getMemberShip(member_id, memberShipId)).filter(memberShip -> memberShip != null)
                .collect(Collectors.toList());
        if (actualMemberShips.size() != form.getApi_id_items().size()) {
            // TODO 记录
            throw new IllegalArgumentException();
        }

        ChargeType chargeType = ChargeType.getChargeType(form.getApi_kind());

        boolean useBauxite = false;
        for (MemberShip memberShip : actualMemberShips) {
            useBauxite = useBauxite | charge(memberShip, chargeType);
        }

        Resource rescource = memberResourceService.getMemberResouce(Long.valueOf(member_id));
        return new ChargeModel(actualMemberShips, rescource, useBauxite);
    }
}
