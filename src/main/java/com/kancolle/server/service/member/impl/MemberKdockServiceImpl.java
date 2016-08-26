/**
 *
 */
package com.kancolle.server.service.member.impl;

import com.google.common.collect.Lists;
import com.kancolle.server.controller.kcsapi.form.kdock.CreateShipForm;
import com.kancolle.server.dao.member.MemberKdockDao;
import com.kancolle.server.model.kcsapi.kcock.GetShipResult;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.model.po.member.MemberKdock;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.service.member.MemberKdockService;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.utils.DateUtils;
import com.kancolle.server.utils.factory.BasicFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

/**
 * @author J.K.SAGE
 * @Date 2015年7月10日
 */
@Service
public class MemberKdockServiceImpl implements MemberKdockService {
    @Autowired
    private MemberKdockDao memberKdockDao;

    @Autowired
    private ShipService shipService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberResourceService memberResourceService;

    @Autowired
    private MemberShipService memberShipService;

    @Override
    public List<MemberKdock> getMemberKdocks(String member_id) {
        return memberKdockDao.selectMemberKdocks(member_id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberKdock getMemberKdockByCond(String member_id, Integer kdock_id) {
        return memberKdockDao.selectMemberKdockByCond(member_id, kdock_id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public MemberKdock createShip(String member_id, CreateShipForm form) {
        Instant now = Instant.now();

        Member member = memberService.getMember(member_id);
        if (memberShipService.getCountOfMemberShip(member_id) == member.getMaxChara()) {
            throw new IllegalStateException();
        }

        Integer kdock_id = form.getApi_kdock_id();
        int fuel = form.getApi_item1();
        int bull = form.getApi_item2();
        int steel = form.getApi_item3();
        int baxuite = form.getApi_item4();
        int dev_item = form.getApi_item5();
        boolean fastBuild = form.getApi_highspeed();
        boolean large_flag = form.getApi_large_flag();

        MemberKdock kdock = getMemberKdockByCond(member_id, kdock_id);
        if (kdock == null || kdock.getState() != MemberKdock.STATUS_AVILABLE) {
            throw new IllegalStateException();
        }

        memberResourceService.consumeResource(member_id, fuel, bull, steel, baxuite, 0, fastBuild ? large_flag ? 20 : 1 : 0, dev_item, 0);

        Ship ship = getShipId(fuel, bull, steel, baxuite, dev_item);

        Instant finishTime = now.plus(ship.getBuildTime(), ChronoUnit.MINUTES);

        kdock.setItem1(fuel);
        kdock.setItem2(bull);
        kdock.setItem3(steel);
        kdock.setItem4(baxuite);
        kdock.setItem5(dev_item);

        kdock.setCompleteTime(finishTime.toEpochMilli());
        kdock.setCompleteTimeStr(DateUtils.format(finishTime));
        kdock.setCreateShipId(ship.getShipId());
        kdock.setState(fastBuild ? MemberKdock.STATUS_FINISHED : MemberKdock.STATUS_BUILDING);

        memberKdockDao.update(kdock);

        return kdock;
    }

    private Ship getShipId(int fuel, int bull, int steel, int baxuite, int dev_item) {
        int ship_id = 0;
        if (fuel >= 300 && steel >= 400 && baxuite >= 300) {
            ship_id = BasicFactory.getCAShipId();
        } else if (fuel >= 400 && steel >= 600) {
            ship_id = BasicFactory.getBBShipId();
        } else if (fuel >= 250 && steel >= 200) {
            ship_id = BasicFactory.getCLShipId();
        } else {
            ship_id = BasicFactory.getBasicShipId();
        }
        return shipService.getShipById(ship_id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void speedUp(String member_id, Integer kdock_id) {
        MemberKdock kdock = getMemberKdockByCond(member_id, kdock_id);
        if (kdock == null || kdock.getState() != MemberKdock.STATUS_BUILDING) {
            throw new IllegalStateException();
        }

        boolean large_flag = kdock.getItem1() > 1500;

        memberResourceService.consumeResource(member_id, 0, 0, 0, 0, 0, large_flag ? 20 : 1, 0, 0);

        kdock.setState(MemberKdock.STATUS_FINISHED);

        memberKdockDao.update(kdock);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public GetShipResult getShip(String member_id, Integer kdock_id) {
        List<MemberKdock> kdocks = getMemberKdocks(member_id);

        Optional<MemberKdock> optional = kdocks.stream().filter(memberKdock -> memberKdock.getKdockId() == kdock_id.intValue()).findFirst();

        if (!optional.isPresent()) {
            throw new IllegalArgumentException();
        }

        MemberKdock kdock = optional.get();
        if (kdock.getState() != MemberKdock.STATUS_FINISHED) {
            throw new IllegalStateException();
        }

        MemberShip memberShip = memberShipService.createShip(member_id, kdock.getCreateShipId());

        kdock.setItem1(0);
        kdock.setItem2(0);
        kdock.setItem3(0);
        kdock.setItem4(0);
        kdock.setItem5(0);
        kdock.setCompleteTime(0);
        kdock.setCompleteTimeStr("0");
        kdock.setCreateShipId(0);
        kdock.setState(MemberKdock.STATUS_AVILABLE);

        memberKdockDao.update(kdock);

        return new GetShipResult(memberShip, kdocks);
    }

    @Override
    public void initMemberKdock(long member_id) {
        List<MemberKdock> kdocks = Lists.newArrayListWithCapacity(4);
        MemberKdock kdock;
        for (int id = 1; id < 5; id++) {
            kdock = id < 3 ?
                    new MemberKdock(member_id, id, MemberKdock.STATUS_AVILABLE) :
                    new MemberKdock(member_id, id, MemberKdock.STATUS_UNAVILABLE);
            kdocks.add(kdock);
        }
        memberKdockDao.insertMemberKdocks(kdocks);

    }
}
