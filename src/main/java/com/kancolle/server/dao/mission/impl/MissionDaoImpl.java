package com.kancolle.server.dao.mission.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.mission.MissionDao;

@Repository
public class MissionDaoImpl<T> extends BaseDaoImpl<T>implements MissionDao<T> {
    
    private Map<String, Object> getMissionIdParamMap(int mission_id){
        return Collections.singletonMap("mission_id", mission_id);
    }

    @Override
    public int getMissionTime(int mission_id) {
        return getTemplate().queryForObject("SELECT TIME FROM t_mission WHERE ID = :mission_id", getMissionIdParamMap(mission_id), Integer.class);
    }

    @Override
    public int updateDeckPortMission(String member_id, int deck_id, int mission_status,int mission_id,long mission_complete_time,int mission_flag) {
        Map<String, Object> params = new HashMap<String, Object>(6);
        params.put("member_id", member_id);
        params.put("deck_id", deck_id);
        params.put("mission_status", mission_status);
        params.put("mission_id", mission_id);
        params.put("mission_complete_time", mission_complete_time);
        params.put("mission_flag", mission_flag);
        return getTemplate().update("UPDATE v_member_deckport SET MISSION_STATUS = :mission_status, MISSION_ID = :mission_id, MISSION_COMPLETE_TIME = :mission_complete_time, MISSION_FLAG = :mission_flag WHERE member_id = :member_id AND ID = :deck_id", params);
    }
}
