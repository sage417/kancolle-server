package com.kancolle.server.service.duty.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.kancolle.server.model.po.duty.MemberDuty.STATE_AVILABLE;
import static com.kancolle.server.model.po.duty.MemberDuty.STATE_PROCESSING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.kancolle.server.dao.duty.MemberDutyDao;
import com.kancolle.server.model.kcsapi.duty.MemberDutyList;
import com.kancolle.server.model.po.duty.MemberDuty;
import com.kancolle.server.service.duty.MemberDutyService;

@Service
public class MemberDutyServiceImpl implements MemberDutyService {
    @Autowired
    MemberDutyDao memberDutyDao;

    @Override
    public MemberDutyList getMemberDutyList(String member_id, int pageNum) {
        int count_of_duty_om_process = memberDutyDao.selectCountOfMemberDutyByState(member_id, STATE_PROCESSING);
        return new MemberDutyList((Page<MemberDuty>) memberDutyDao.selectMemberDutys(member_id, pageNum, 5), count_of_duty_om_process);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberDuty getMemberDuty(String member_id, Integer quest_id) {
        return checkNotNull(memberDutyDao.selectMemberDutyByCond(member_id, quest_id));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void start(String member_id, Integer quest_id) {
        MemberDuty memberDuty = getMemberDuty(member_id, quest_id);
        checkState(memberDuty.getState() == STATE_AVILABLE);
        memberDuty.setState(STATE_PROCESSING);
        memberDutyDao.update(memberDuty);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void stop(String member_id, Integer quest_id) {
        MemberDuty memberDuty = getMemberDuty(member_id, quest_id);
        checkState(memberDuty.getState() == STATE_PROCESSING);
        memberDuty.setState(STATE_AVILABLE);
        memberDutyDao.update(memberDuty);
    }
}
