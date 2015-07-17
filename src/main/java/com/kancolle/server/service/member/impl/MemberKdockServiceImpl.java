/**
 * 
 */
package com.kancolle.server.service.member.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.controller.kcsapi.form.kdock.CreateShipForm;
import com.kancolle.server.dao.member.MemberKdockDao;
import com.kancolle.server.model.po.kdock.CreateShipResult;
import com.kancolle.server.model.po.member.MemberKdock;
import com.kancolle.server.model.po.resource.Resource;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.service.member.MemberKdockService;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.utils.DateUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年7月10日
 *
 */
@Service
public class MemberKdockServiceImpl implements MemberKdockService {
    @Autowired
    private MemberKdockDao memberKdockDao;

    @Autowired
    private ShipService shipService;

    @Autowired
    private MemberResourceService memberResourceService;

    @Override
    public List<MemberKdock> getMemberKdocks(String member_id) {
        return memberKdockDao.selectMemberKdocks(member_id);
    }

    @Override
    public MemberKdock getMemberKdockByCond(String member_id, Integer kdock_id) {
        return memberKdockDao.selectMemberKdockByCond(member_id, kdock_id);
    }

    @Override
    public CreateShipResult createShip(String member_id, CreateShipForm form) {
        Instant now = Instant.now();

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
        Resource memberResource = memberResourceService.getMemberResouce(member_id);

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
        kdock.setState(large_flag ? MemberKdock.STATUS_BUILDING : MemberKdock.STATUS_LARGE_BUILDING);
        
        memberKdockDao.update(kdock);

        return null;
    }

    private Ship getShipId(int fuel, int bull, int steel, int baxuite, int dev_item) {
        List<Ship> ships = shipService.getShipCanBuild();
        return ships.get(RandomUtils.nextInt(0, ships.size() + 1));
    }
}
