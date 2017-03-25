package com.kancolle.server.service.duty;

import com.kancolle.server.model.po.duty.MemberDuty;

public interface DutyResultChecker {
    int PROCESS_FLAG_0 = 0;
    int PROCESS_FLAG_50 = 1;
    int PROCESS_FLAG_80 = 2;

    boolean checkCond(MemberDuty duty);

    void updateProgressFlag(MemberDuty memberDuty);

}
