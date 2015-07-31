package com.kancolle.server.service.duty.resultchecker;

import org.springframework.stereotype.Component;

import com.kancolle.server.model.po.duty.MemberDuty;
import com.kancolle.server.service.duty.DutyResultChecker;

@Component
public class Duty703ResultChecker implements DutyResultChecker {

    @Override
    public boolean checkCond(MemberDuty duty) {
        return duty.getCounter() >= 15;
    }

    @Override
    public void updateProgressFlag(MemberDuty memberDuty) {
        int counter = memberDuty.getCounter();
        if (counter / 15f > 0.8f) {
            memberDuty.setProgressFlag(PROCESS_FLAG_80);
        } else if (counter / 15f > 0.5f) {
            memberDuty.setProgressFlag(PROCESS_FLAG_50);
        }
    }
}
