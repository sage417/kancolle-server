package com.kancolle.server.service.member;

import java.util.List;
import java.util.Map;

import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;
import com.kancolle.server.model.kcsapi.member.MemberKdock;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.kcsapi.member.MemberRecord;
import com.kancolle.server.model.kcsapi.member.MemberSlotItem;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;
import com.kancolle.server.model.po.member.Member;

public interface MemberService {

    void changeShip(String member_id, int fleet_id, long ship_id, int ship_idx);

    void destroyShip(String member_id, long api_ship_id);

    MemberBasic getBasic(String member_id);

    List<MemberFurniture> getFurniture(String member_id);

    List<MemberKdock> getKdock(String member_id);

    String getMemberByApiToken(String api_token);

    List<MemberMission> getMission(String member_id);

    MemberPort getPort(String member_id) throws Exception;

    MemberRecord getRecord(String member_id);

    List<MemberSlotItem> getSlotItem(String member_id);

    Map<String, Object> getUnsetSlot(String member_id);

    List<MemberUseItem> getUseItem(String member_id);

    void increaseMemberExp(Member member, long exp);

    long getSumExpByLV(int lv);

    Member getMember(long memberId);
}
