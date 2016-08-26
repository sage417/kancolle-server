package com.kancolle.server.service.duty;

import com.kancolle.server.model.event.PowUpEvent;
import com.kancolle.server.model.kcsapi.duty.DutyItemGetResult;
import com.kancolle.server.model.kcsapi.duty.MemberDutyPageList;
import com.kancolle.server.model.po.duty.MemberDuty;

import java.util.List;

public interface MemberDutyService {

    MemberDutyPageList getMemberDutyList(String member_id, int pageNum);

    MemberDuty getMemberDuty(String member_id, Integer quest_id);

    List<MemberDuty> getMemberDutysByState(String member_id, int stateProcessing);

    void start(String member_id, Integer quest_id);

    void stop(String member_id, Integer quest_id);

    void listenPowUpEvent(PowUpEvent event);

    DutyItemGetResult clearitemget(String member_id, Integer quest_id);

}
