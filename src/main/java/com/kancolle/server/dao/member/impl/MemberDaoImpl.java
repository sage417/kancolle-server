package com.kancolle.server.dao.member.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.model.kcsapi.member.MemberMaterialDto;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.record.MemberRecord;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.model.po.member.MemberMaterial;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {

    private static final ImmutableList<Function<MemberMaterial, Integer>> methodList =
            ImmutableList.of(m -> -1,
                    MemberMaterial::getFuel,
                    MemberMaterial::getBull,
                    MemberMaterial::getSteal,
                    MemberMaterial::getBauxite,
                    MemberMaterial::getFast_build,
                    MemberMaterial::getFast_rec,
                    MemberMaterial::getDev_item,
                    MemberMaterial::getEnh_item
            );

    @Override
    public Long getMemberIdByApiToken(String api_token) {
        return getSqlSession().selectOne("getMemberIdByApiToken", api_token);
    }

    private Map<String, Object> getMemParamMap(Object memberId) {
        return Collections.singletonMap("member_id", memberId);
    }

    @Override
    public List<MemberMission> selectMemberMission(long member_id) {
        return getSqlSession().selectList("selectMemberMission", getMemParamMap(member_id));
    }

    @Override
    public MemberRecord selectMemberRecord(long member_id) {
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
    public Member getMemberById(long member_id) {
        return getSqlSession().selectOne("selectMemberById", member_id);
    }

    @Override
    public void updateMemberToken(long member_id, String token) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("member_id", member_id);
        params.put("token", token);
        getSqlSession().update("updateMemberToken", params);
    }

    @Override
    public int insert(Member member) {
        return getSqlSession().insert("insert", member);
    }

    @Override
    public List<MemberMaterialDto> selectMemberMaterial(long member_id) {
        MemberMaterial memberMaterial = getSqlSession().selectOne("selectMemberMaterial", member_id);
        return IntStream.range(1, methodList.size())
                .mapToObj(i -> new MemberMaterialDto(member_id, i, methodList.get(i).apply(memberMaterial)))
                .collect(Collectors.toList());
    }
}
