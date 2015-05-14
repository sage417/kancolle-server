package com.kancolle.server.service.exp;

import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.po.MissionExp;

public interface ExpService {

    int getMemberLevel(long exp);

    int getShipLevel(long exp);

    long getShipNextExp(int now_level);

    long getMemberNextExp(int now_level);

    MissionExp getMissionExp(int missionId);

    MemberBasic updateMemberExpAndLevel(String member_id, int exp);

    long[] updateShipExpAndLevel(String member_id, long ship_id, int i);
}
