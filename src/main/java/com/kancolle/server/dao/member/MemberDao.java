package com.kancolle.server.dao.member;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.member.MemberMaterialDto;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.record.MemberRecord;
import com.kancolle.server.model.po.member.Member;

import java.util.List;

public interface MemberDao extends BaseDao<Member> {

    String getMemberIdByApiToken(String api_token);

    List<MemberMission> selectMemberMission(String member_id);

    MemberRecord selectMemberRecord(String member_id);

    long getNeedExpByLevel(int lv);

    int getMemberLVByExp(long afterExp);

    Member getMemberById(String memberId);

    void updateMemberToken(String member_id, String token);

    int insert(Member member);

    List<MemberMaterialDto> selectMemberMaterial(String member_id);
}
