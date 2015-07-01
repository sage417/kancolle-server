package com.kancolle.server.dao.member;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;
import com.kancolle.server.model.kcsapi.member.MemberKdock;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;
import com.kancolle.server.model.kcsapi.member.record.MemberRecord;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

public interface MemberDao extends BaseDao<Member> {

    MemberBasic getBasic(String member_id);

    List<MemberFurniture> getFurniture(String member_id);

    List<MemberKdock> getKdock(String member_id);

    String getMemberByApiToken(String api_token);

    List<MemberMission> getMission(String member_id);

    MemberRecord selectMemberRecord(String member_id);

    List<MemberSlotItem> getSlotItem(String member_id);

    List<MemberUseItem> getUseItem(String member_id);

    long getNeedExpByLevel(int lv);

    int getMemberLVByExp(long afterExp);

    Member getMemberById(String memberId);
}
