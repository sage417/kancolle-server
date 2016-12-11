package com.kancolle.server.dao.member;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.member.MemberMaterialDto;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.record.MemberRecord;
import com.kancolle.server.model.po.member.Member;

import java.util.List;

public interface MemberDao extends BaseDao<Member> {

    Long getMemberIdByApiToken(String api_token);

    List<MemberMission> selectMemberMission(long member_id);

    MemberRecord selectMemberRecord(long member_id);

    long getNeedExpByLevel(int lv);

    int getMemberLVByExp(long afterExp);

    Member getMemberById(long member_id);

    void updateMemberToken(long member_id, String token);

    int insert(Member member);

    List<MemberMaterialDto> selectMemberMaterial(long member_id);
}
