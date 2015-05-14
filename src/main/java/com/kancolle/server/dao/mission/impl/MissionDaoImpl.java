package com.kancolle.server.dao.mission.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.mission.MissionDao;
import com.kancolle.server.model.kcsapi.start.sub.MissionModel;
import com.kancolle.server.model.po.DeckPortMission;

@Repository
public class MissionDaoImpl<T> extends BaseDaoImpl<T> implements MissionDao<T> {

    @Override
    public DeckPortMission getDeckPortMission(String member_id, int deck_id) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("member_id", member_id);
        params.put("deck_id", deck_id);
        return queryForSingleModel(DeckPortMission.class, "SELECT MISSION_STATUS , MISSION_ID , MISSION_COMPLETE_TIME, MISSION_FLAG FROM v_member_deckport WHERE member_id = :member_id AND ID = :deck_id", params);
    }

    @Override
    public MissionModel getMission(int mission_id) {
        return queryForSingleModel(MissionModel.class, "SELECT * FROM t_mission WHERE ID = :mission_id", getMissionIdParamMap(mission_id));
    }

    private Map<String, Object> getMissionIdParamMap(int mission_id) {
        return Collections.singletonMap("mission_id", mission_id);
    }

    @Override
    public int updateDeckPortMission(String member_id, int deck_id, DeckPortMission deckPortMission) {
        Map<String, Object> params = new HashMap<String, Object>(6);
        params.put("member_id", member_id);
        params.put("deck_id", deck_id);
        params.put("mission_status", deckPortMission.getMissionStatus());
        params.put("mission_id", deckPortMission.getMissionId());
        params.put("mission_complete_time", deckPortMission.getMission_complete_time());
        params.put("mission_flag", deckPortMission.getMission_flag());
        return getTemplate().update("UPDATE v_member_deckport SET MISSION_STATUS = :mission_status, MISSION_ID = :mission_id, MISSION_COMPLETE_TIME = :mission_complete_time, MISSION_FLAG = :mission_flag WHERE member_id = :member_id AND ID = :deck_id", params);
    }
}
