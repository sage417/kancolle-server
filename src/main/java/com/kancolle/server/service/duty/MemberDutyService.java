package com.kancolle.server.service.duty;

import com.kancolle.server.model.event.PowUpEvent;
import com.kancolle.server.model.kcsapi.duty.DutyItemGetResult;
import com.kancolle.server.model.kcsapi.duty.MemberDutyPageList;
import com.kancolle.server.model.po.duty.MemberDuty;

import java.util.List;

public interface MemberDutyService {

    MemberDutyPageList getMemberDutyList(long member_id, int pageNum);

    MemberDuty getMemberDuty(long member_id, Integer quest_id);

    List<MemberDuty> getMemberDutysByState(long member_id, int stateProcessing);

    void start(long member_id, Integer quest_id);

    void stop(long member_id, Integer quest_id);

    void listenPowUpEvent(PowUpEvent event);

    DutyItemGetResult clearitemget(long member_id, Integer quest_id);

}
