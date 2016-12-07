package com.kancolle.server.dao.member.impl;

import com.google.common.collect.ImmutableMap;
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

    private static final ImmutableMap<Integer, Function<MemberMaterial, Integer>> methodMap = new ImmutableMap.Builder<Integer, Function<MemberMaterial, Integer>>()
            .put(1, MemberMaterial::getFuel)
            .put(2, MemberMaterial::getBull)
            .put(3, MemberMaterial::getSteal)
            .put(4, MemberMaterial::getBauxite)
            .put(5, MemberMaterial::getFast_build)
            .put(6, MemberMaterial::getFast_rec)
            .put(7, MemberMaterial::getDev_item)
            .put(8, MemberMaterial::getEnh_item)
            .build();

    @Override
    public String getMemberIdByApiToken(String api_token) {
        return getSqlSession().selectOne("getMemberIdByApiToken", api_token);
    }

    private Map<String, Object> getMemParamMap(Object memberId) {
        return Collections.singletonMap("member_id", memberId);
    }

    @Override
    public List<MemberMission> selectMemberMission(String member_id) {
        return getSqlSession().selectList("selectMemberMission", getMemParamMap(member_id));
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

    @Override
    public List<MemberMaterialDto> selectMemberMaterial(String member_id) {
        MemberMaterial memberMaterial = getSqlSession().selectOne("selectMemberMaterial", member_id);
        return toModel(memberMaterial);
    }

    private List<MemberMaterialDto> toModel(MemberMaterial memberMaterial) {

        final int count = methodMap.size() + 1;

        return IntStream.range(1, count).boxed().map(i -> {
            MemberMaterialDto material = new MemberMaterialDto();
            material.setApi_id(i);
            material.setApi_value(methodMap.get(i).apply(memberMaterial));
            material.setApi_member_id(memberMaterial.getMemberId());
            return material;
        }).collect(Collectors.toList());
    }
}
