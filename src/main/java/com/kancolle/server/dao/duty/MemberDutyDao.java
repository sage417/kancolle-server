package com.kancolle.server.dao.duty;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.duty.MemberDuty;

import java.util.List;

public interface MemberDutyDao extends BaseDao<MemberDuty> {

    List<MemberDuty> selectMemberDutys(String member_id, int pageNum, int pageSize);

    List<MemberDuty> selectMembersDutyByState(String member_id, int state);

    MemberDuty selectMemberDutyByCond(String member_id, Integer quest_id);

    int selectCountOfMemberDutysByState(String member_id, int stateProcessing);

    void deleteDuty(MemberDuty duty);

    void insertAfterDutys(MemberDuty duty);

}
