/**
 * 
 */
package com.kancolle.server.service.member.impl;

import com.google.common.collect.Lists;
import com.kancolle.server.controller.kcsapi.form.ndock.NdockStartForm;
import com.kancolle.server.dao.member.MemberNdockDao;
import com.kancolle.server.model.po.member.MemberNdock;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.member.MemberNdockService;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
@Service
public class MemberNdockServiceImpl implements MemberNdockService {

    @Autowired
    private MemberNdockDao memberNdockDao;

    @Autowired
    private MemberResourceService memberResourceService;

    @Autowired
    private MemberShipService memberShipService;

    @Override
    public List<MemberNdock> getMemberNdocks(String member_id) {
        return memberNdockDao.selectMemberNdocks(member_id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberNdock getMemberNdockByCond(String member_id, int ndockId) {
        return memberNdockDao.selectMemberNdock(member_id, ndockId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    private void updateMemberNdock(MemberNdock memberNdock) {
        memberNdockDao.update(memberNdock);
    }

    @Override
    @Transactional
    public void start(String member_id, NdockStartForm form) {
        Long member_ship_id = form.getApi_ship_id();
        Integer ndock_id = form.getApi_ndock_id();
        boolean useHighSpeed = form.getApi_highspeed() == 1;

        MemberShip memberShip = checkNotNull(memberShipService.getMemberShip(member_id, member_ship_id));
        checkState(memberShip.getNowHp() < memberShip.getMaxHp());

        memberResourceService.consumeResource(member_id, memberShip.getApi_ndock_item()[0], 0, memberShip.getApi_ndock_item()[1], 0, form.getApi_highspeed(), 0, 0, 0);

        if (useHighSpeed)
            repairMemberShip(memberShip);
        else {
            MemberNdock memberNdock = checkNotNull(getMemberNdockByCond(member_id, ndock_id));
            checkState(memberNdock.getState() == MemberNdock.STATE_AVILABLE);

            // 状态设定
            memberNdock.setState(MemberNdock.STATE_USING);
            memberNdock.setMemberShipId(memberShip.getMemberShipId());

            // 完了时间设定
            Instant completeInstant = Instant.now().plus(memberShip.getApi_ndock_time(), ChronoUnit.MILLIS);
            memberNdock.setCompleteTime(completeInstant.toEpochMilli());
            memberNdock.setCompleteTimeStr(DateUtils.format(completeInstant));

            // 消耗资源记录
            memberNdock.setItem1(memberShip.getApi_ndock_item()[0]);
            memberNdock.setItem3(memberShip.getApi_ndock_item()[1]);

            updateMemberNdock(memberNdock);
        }
    }

    @Override
    @Transactional
    public void speedChange(String member_id, int ndock_id) {
        memberResourceService.consumeResource(member_id, 0, 0, 0, 0, 1, 0, 0, 0);

        MemberNdock memberNdock = checkNotNull(getMemberNdockByCond(member_id, ndock_id));
        checkState(memberNdock.getState() == MemberNdock.STATE_USING);

        MemberShip memberShip = memberShipService.getMemberShip(member_id, Long.valueOf(memberNdock.getMemberShipId()));
        repairMemberShip(checkNotNull(memberShip));

        memberNdock.setState(MemberNdock.STATE_AVILABLE);
        memberNdock.setMemberShipId(0L);
        memberNdock.setCompleteTime(0L);
        memberNdock.setCompleteTimeStr("0");
        updateMemberNdock(memberNdock);
    }

    @Override
    public void initMemberNdock(long member_id) {
        List<MemberNdock> ndocks = Lists.newArrayListWithCapacity(4);
        MemberNdock ndock;
        for (int id = 1; id < 5; id++) {
            ndock = id < 3?
                    new MemberNdock(member_id, id, MemberNdock.STATE_AVILABLE):
                    new MemberNdock(member_id, id, MemberNdock.STATE_UNAVILABLE);
            ndocks.add(ndock);
        }
        memberNdockDao.insertMemberNdocks(ndocks);
    }

    private void repairMemberShip(MemberShip memberShip) {
        memberShip.setNowHp(memberShip.getMaxHp());
        if (memberShip.getCond() < 40)
            memberShip.setCond(40);
        memberShipService.updateHpAndCond(memberShip);
    }
}
