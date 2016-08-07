package com.kancolle.server.service.duty.resultchecker;

import com.kancolle.server.model.po.duty.MemberDuty;
import com.kancolle.server.service.duty.DutyResultChecker;
import org.springframework.stereotype.Component;

@Component
public class Duty701ResultChecker implements DutyResultChecker {

    @Override
    public boolean checkCond(MemberDuty duty) {
        return duty.getCounter() >= 1;
    }

    @Override
    public void updateProgressFlag(MemberDuty memberDuty) {
    }
}
