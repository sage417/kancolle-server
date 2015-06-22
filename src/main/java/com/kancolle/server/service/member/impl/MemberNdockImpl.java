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
import com.kancolle.server.model.kcsapi.member.MemberNdock;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.member.MemberNdockService;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.utils.DateUtils;
import com.kancolle.server.utils.logic.NdockUtils;

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
        MemberNdock memberNdock = getMemberNdockByCond(member_id, form.getApi_ndock_id());
        if (memberNdock == null || memberNdock.getState() != MemberNdock.STATE_AVILABLE) {
            // TODO log
            throw new IllegalArgumentException();
        }

        MemberShip memberShip = shipService.getMemberShip(member_id, form.getApi_ship_id());
        if (memberShip == null || memberShip.getNowHp() >= memberShip.getMaxHp()) {
            // TODO log
            throw new IllegalArgumentException();
        }
        boolean useFastRecovery = form.getApi_highspeed().intValue() == 1;

        if (!useFastRecovery) {
            memberNdock.setState(MemberNdock.STATE_USING);
            memberNdock.setMemberShipId(memberShip.getMemberShipId());

            long seconds = NdockUtils.getNdockTime(memberShip);

            Instant now = Instant.now();

            Instant completeInstant = now.plus(seconds, ChronoUnit.SECONDS);

            memberNdock.setCompleteTime(completeInstant.toEpochMilli());
            memberNdock.setCompleteTimeStr(DateUtils.format(completeInstant));

            // TODO 消耗资源
            memberNdock.setItem1(0);
            memberNdock.setItem3(0);

            updateMemberNdock(memberNdock);
        }

        memberResourceService.consumeResource(Long.valueOf(member_id), memberNdock.getItem1(), 0, memberNdock.getItem3(), form.getApi_highspeed().intValue(), 0, 0, 0, 0);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.SUPPORTS)
    public void updateMemberNdock(MemberNdock memberNdock) {
        memberNdockDao.update(memberNdock);
    }
}
