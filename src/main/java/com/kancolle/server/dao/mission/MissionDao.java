package com.kancolle.server.dao.mission;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.start.sub.MissionModel;
import com.kancolle.server.model.po.DeckPortMission;

public interface MissionDao extends BaseDao<MissionModel> {

    DeckPortMission getDeckPortMission(String member_id, int deck_id);

    MissionModel getMission(int mission_id);

    int updateDeckPortMission(String member_id, int deck_id, DeckPortMission deckPortMission);
}
