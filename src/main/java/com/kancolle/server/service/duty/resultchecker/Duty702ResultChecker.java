package com.kancolle.server.service.duty.resultchecker;

import org.springframework.stereotype.Component;

import com.kancolle.server.model.po.duty.MemberDuty;
import com.kancolle.server.service.duty.DutyResultChecker;

@Component
public class Duty702ResultChecker implements DutyResultChecker {

    @Override
    public boolean checkCond(MemberDuty duty) {
        return duty.getCounter() >= 2;
    }

    @Override
    public void updateProgressFlag(MemberDuty memberDuty) {
        if (memberDuty.getCounter() == 1) {
            memberDuty.setProgressFlag(DutyResultChecker.PROCESS_FLAG_50);
        }
    }
}
