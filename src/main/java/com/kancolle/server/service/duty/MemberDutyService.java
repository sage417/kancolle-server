package com.kancolle.server.service.duty;

import com.kancolle.server.model.kcsapi.duty.MemberDutyList;
import com.kancolle.server.model.po.duty.MemberDuty;

public interface MemberDutyService {

    MemberDutyList getMemberDutyList(String member_id, int pageNum);

    MemberDuty getMemberDuty(String member_id, Integer quest_id);

    void start(String member_id, Integer quest_id);

    void stop(String member_id, Integer quest_id);

}
