/**
 * 
 */
package com.kancolle.server.service.ship.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kancolle.server.controller.kcsapi.form.ship.ShipChargeForm;
import com.kancolle.server.dao.ship.MemberShipDao;
import com.kancolle.server.model.kcsapi.charge.ChargeModel;
import com.kancolle.server.model.po.resource.Resource;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.ship.enums.ChargeType;

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
    private MemberResourceService memberResourceService;

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

        List<MemberShip> memberShips = memberShipIds.stream().map(memberShipId -> getMemberShip(member_id, memberShipId)).filter(memberShip -> memberShip != null)
                .collect(Collectors.toList());
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
}
