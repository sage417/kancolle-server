package com.kancolle.server.service.duty;

import com.kancolle.server.model.po.duty.MemberDuty;

public interface DutyResultChecker {
    public static final int PROCESS_FLAG_0 = 0;
    public static final int PROCESS_FLAG_50 = 1;
    public static final int PROCESS_FLAG_80 = 2;

    boolean checkCond(MemberDuty duty);

    void updateProgressFlag(MemberDuty memberDuty);

}
