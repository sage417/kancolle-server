package com.kancolle.server.dao.member.impl;

import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.record.MemberRecord;
import com.kancolle.server.model.po.member.Member;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {

    @Override
    public String getMemberIdByApiToken(String api_token) {
        return getTemplate().queryForObject("SELECT member_id FROM t_member WHERE api_token = :token", Collections.singletonMap("token", api_token), String.class);
    }

    private Map<String, Object> getMemParamMap(Object memberId) {
        return Collections.singletonMap("member_id", memberId);
    }

    @Override
    public List<MemberMission> getMission(String member_id) {
        return queryForModels(MemberMission.class, "SELECT * FROM v_member_mission WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public MemberRecord selectMemberRecord(String member_id) {
        return getSqlSession().selectOne("selectMemberRecord", member_id);
    }

    @Override
    public long getNeedExpByLevel(int nowLv) {
        return getSqlSession().selectOne("selectNeedMemberExpByLevel", nowLv);
    }

    @Override
    public int getMemberLVByExp(long nowExp) {
        return getSqlSession().selectOne("selectMemberLVByExp", nowExp);
    }

    @Override
    public Member getMemberById(String memberId) {
        return getSqlSession().selectOne("selectMemberById", memberId);
    }

    @Override
    public void updateMemberToken(String member_id, String token) {
        Map<String, String> params = Maps.newHashMap();
        params.put("member_id", member_id);
        params.put("token", token);
        getSqlSession().update("updateMemberToken", params);
    }

    @Override
    public int insert(Member member) {
        return getSqlSession().insert("insert", member);
    }
}
