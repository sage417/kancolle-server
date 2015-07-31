package com.kancolle.server.dao.duty.impl;

import static com.google.common.collect.Maps.newHashMap;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.duty.MemberDutyDao;
import com.kancolle.server.model.po.duty.MemberDuty;

@Repository
public class MemberDutyDaoImpl extends BaseDaoImpl<MemberDuty>implements MemberDutyDao {

    @Override
    public void update(MemberDuty t) {
        super.update(t);
    }

    @Override
    public List<MemberDuty> selectMemberDutys(String member_id, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return getSqlSession().selectList("selectMemberDutys", member_id);
    }

    @Override
    public int selectCountOfMemberDutysByState(String member_id, int state) {
        Map<String, Object> params = newHashMap();
        params.put("member_id", member_id);
        params.put("state", state);
        return getSqlSession().selectOne("selectCountOfMemberDutysByState", params);
    }

    @Override
    public MemberDuty selectMemberDutyByCond(String member_id, Integer quest_id) {
        Map<String, Object> params = newHashMap();
        params.put("member_id", member_id);
        params.put("quest_id", quest_id);
        return getSqlSession().selectOne("selectMemberDutyByCond", params);
    }

    @Override
    public List<MemberDuty> selectMembersDutyByState(String member_id, int state) {
        Map<String, Object> params = newHashMap();
        params.put("member_id", member_id);
        params.put("state", state);
        return getSqlSession().selectList("selectMemberDutysByState", params);
    }

    @Override
    public void deleteDuty(MemberDuty duty) {
        getSqlSession().delete("deleteDuty", duty);
    }

    @Override
    public void insertAfterDutys(MemberDuty duty) {
        getSqlSession().insert("insertAfterDutys", duty);
    }
}
