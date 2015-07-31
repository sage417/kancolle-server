package com.kancolle.server.service.member;

import java.util.List;

import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;
import com.kancolle.server.model.kcsapi.member.record.MemberRecord;
import com.kancolle.server.model.po.member.Member;

public interface MemberService {

    Member getBasic(String member_id);

    String getMemberByApiToken(String api_token);

    List<MemberMission> getMission(String member_id);

    MemberPort getPort(String member_id) throws Exception;

    MemberRecord getRecord(String member_id);

    List<MemberUseItem> getUseItem(String member_id);

    void increaseMemberExp(Member member, long exp);

    /** 获取到达等级所需总经验 */
    long getSumExpByLV(int lv);

    Member getMember(String memberId);

    void updateMember(Member member);

    void updateMemberToken(String member_id);

    void openLargeBuild(String member_id);
}
