package com.kancolle.server.service.duty.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.kancolle.server.model.po.duty.Duty.OPERATE_TYPE_POWUP;
import static com.kancolle.server.model.po.duty.MemberDuty.STATE_AVILABLE;
import static com.kancolle.server.model.po.duty.MemberDuty.STATE_FINISH;
import static com.kancolle.server.model.po.duty.MemberDuty.STATE_PROCESSING;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.github.pagehelper.Page;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.kancolle.server.dao.duty.MemberDutyDao;
import com.kancolle.server.model.event.PowUpEvent;
import com.kancolle.server.model.kcsapi.duty.MemberDutyList;
import com.kancolle.server.model.po.duty.MemberDuty;
import com.kancolle.server.service.duty.DutyResultChecker;
import com.kancolle.server.service.duty.MemberDutyService;

@Service
public class MemberDutyServiceImpl implements MemberDutyService {

    @Autowired
    public void register(EventBus eventBus) {
        eventBus.register(this);
    }

    @Autowired
    MemberDutyDao memberDutyDao;

    @Override
    public MemberDutyList getMemberDutyList(String member_id, int pageNum) {
        int count_of_duty_om_process = memberDutyDao.selectCountOfMemberDutysByState(member_id, STATE_PROCESSING);
        return new MemberDutyList((Page<MemberDuty>) memberDutyDao.selectMemberDutys(member_id, pageNum, 5), count_of_duty_om_process);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberDuty getMemberDuty(String member_id, Integer quest_id) {
        return checkNotNull(memberDutyDao.selectMemberDutyByCond(member_id, quest_id));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<MemberDuty> getMemberDutysByState(String member_id, int state) {
        return memberDutyDao.selectMembersDutyByState(member_id, state);
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

    @Override
    @Subscribe
    public void listenPowUpEvent(PowUpEvent event) {
        List<MemberDuty> accecptDutys = getMemberDutysByState(event.getMember_id(), STATE_PROCESSING);
        List<MemberDuty> eventDutys = accecptDutys.stream().filter(memberDuty -> memberDuty.getDuty().getOperate() == OPERATE_TYPE_POWUP).collect(Collectors.toList());
        for (MemberDuty memberDuty : eventDutys) {
            memberDuty.setCounter(memberDuty.getCounter() + 1);
            DutyResultChecker checker = ContextLoader.getCurrentWebApplicationContext().getBean(String.format("duty%dResultChecker", memberDuty.getDutyNo()), DutyResultChecker.class);
            if (checker.checkCond(memberDuty)) {
                // 任务完成
                memberDuty.setProgressFlag(0);
                memberDuty.setState(STATE_FINISH);
            } else {
                checker.updateProgressFlag(memberDuty);
            }
            memberDutyDao.update(memberDuty);
        }
    }
}
