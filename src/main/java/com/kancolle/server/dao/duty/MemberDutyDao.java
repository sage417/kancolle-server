package com.kancolle.server.dao.duty;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.duty.MemberDuty;

import java.util.List;

public interface MemberDutyDao extends BaseDao<MemberDuty> {

    List<MemberDuty> selectMemberDutys(long member_id, int pageNum, int pageSize);

    List<MemberDuty> selectMembersDutyByState(long member_id, int state);

    MemberDuty selectMemberDutyByCond(long member_id, Integer quest_id);

    int selectCountOfMemberDutysByState(long member_id, int stateProcessing);

    void deleteDuty(MemberDuty duty);

    void insertAfterDutys(MemberDuty duty);

}
