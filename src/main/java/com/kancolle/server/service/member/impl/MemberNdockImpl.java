/**
 * 
 */
package com.kancolle.server.service.member.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kancolle.server.controller.kcsapi.form.ndock.NdockStartForm;
import com.kancolle.server.dao.member.MemberNdockDao;
import com.kancolle.server.model.po.member.MemberNdock;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.member.MemberNdockService;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.utils.DateUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
@Service
public class MemberNdockImpl implements MemberNdockService {

    @Autowired
    MemberNdockDao memberNdockDao;

    @Autowired
    private ShipService shipService;

    @Autowired
    private MemberResourceService memberResourceService;

    @Autowired
    private MemberShipService memberShipService;

    @Override
    public List<MemberNdock> getMemberNdocks(String member_id) {
        return memberNdockDao.selectMemberNdocks(member_id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberNdock getMemberNdockByCond(String member_id, int ndockId) {
        return memberNdockDao.selectMemberNdock(member_id, ndockId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void start(String member_id, NdockStartForm form) {
        MemberShip memberShip = memberShipService.getMemberShip(member_id, form.getApi_ship_id());
        if (memberShip == null || memberShip.getNowHp() >= memberShip.getMaxHp()) {
            // TODO log
            throw new IllegalArgumentException();
        }

        memberResourceService.consumeResource(member_id, memberShip.getApi_ndock_item()[0], 0, memberShip.getApi_ndock_item()[1], 0, form.getApi_highspeed().intValue(), 0, 0, 0);

        useNdock(member_id, form.getApi_ndock_id(), memberShip);

        if (form.getApi_highspeed().intValue() == 1)
            clearNdock(member_id, form.getApi_ndock_id());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.SUPPORTS)
    private void updateMemberNdock(MemberNdock memberNdock) {
        memberNdockDao.update(memberNdock);
    }

    private void useNdock(String member_id, int ndock_id, MemberShip memberShip) {
        MemberNdock memberNdock = getMemberNdockByCond(member_id, ndock_id);
        if (memberNdock == null || memberNdock.getState() != MemberNdock.STATE_AVILABLE) {
            // TODO log
            throw new IllegalArgumentException();
        }
        memberNdock.setState(MemberNdock.STATE_USING);
        memberNdock.setMemberShipId(memberShip.getMemberShipId());

        Instant now = Instant.now();
        Instant completeInstant = now.plus(memberShip.getApi_ndock_time(), ChronoUnit.MILLIS);

        memberNdock.setCompleteTime(completeInstant.toEpochMilli());
        memberNdock.setCompleteTimeStr(DateUtils.format(completeInstant));

        memberNdock.setItem1(memberShip.getApi_ndock_item()[0]);
        memberNdock.setItem3(memberShip.getApi_ndock_item()[1]);

        updateMemberNdock(memberNdock);
    }

    private void clearNdock(String member_id, int ndock_id) {
        MemberNdock memberNdock = getMemberNdockByCond(member_id, ndock_id);
        if (memberNdock == null || memberNdock.getState() != MemberNdock.STATE_USING) {
            // TODO log
            throw new IllegalArgumentException();
        }
        memberNdock.setState(MemberNdock.STATE_AVILABLE);
        memberNdock.setMemberShipId(0L);
        memberNdock.setCompleteTime(0L);
        memberNdock.setCompleteTimeStr("0");
        updateMemberNdock(memberNdock);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void speedchange(String member_id, int api_ndock_id) {
        memberResourceService.consumeResource(member_id, 0, 0, 0, 0, 1, 0, 0, 0);
        clearNdock(member_id, api_ndock_id);
    }
}
