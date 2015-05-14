package com.kancolle.server.dao.exp;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.MissionExp;

public interface ExpDao<T> extends BaseDao<T> {

    int getMemberLevel(long exp);

    MissionExp getMissionExp(int missionId);

    int updateMemberExpAndLevel(String member_id, long api_experience, int api_level);

    long getMemberNextExp(int now_level);

    int getShipLevel(long exp);

    int updateShipExpAndLevel(String member_id, long ship_id, long[] experiences, int level);

    long getShipNextExp(int now_level);

}
