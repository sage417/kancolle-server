package com.kancolle.server.service.member;

import java.util.List;
import java.util.Map;

import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;
import com.kancolle.server.model.kcsapi.member.MemberKdock;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.kcsapi.member.MemberSlotItem;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;

public interface MemberService {

    String getMemberByApiToken(String api_token);

    MemberBasic getBasic(String member_id);

    List<MemberFurniture> getFurniture(String member_id);

    List<MemberSlotItem> getSlotItem(String member_id);

    List<MemberUseItem> getUseItem(String member_id);

    List<MemberKdock> getKdock(String member_id);

    MemberPort getPort(String member_id) throws Exception;

    Map<String, Object> getUnsetSlot(String member_id);

    void destroyShip(String member_id, long api_ship_id);

    void changeShip(String member_id, int fleet_id, long ship_id, int ship_idx);

}
