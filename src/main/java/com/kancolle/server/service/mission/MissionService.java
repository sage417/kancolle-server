package com.kancolle.server.service.mission;

import com.kancolle.server.model.kcsapi.misson.MissionResult;
import com.kancolle.server.model.kcsapi.misson.MissionReturn;
import com.kancolle.server.model.kcsapi.misson.MissionStart;
import com.kancolle.server.model.kcsapi.start.sub.MissionModel;
import com.kancolle.server.model.po.deck.DeckPortMission;

public interface MissionService {

    MissionReturn callbackMission(String member_id, int deck_id);

    MissionResult calMissionResult(String member_id, int deck_id);

    DeckPortMission getDeckPortMission(String member_id, int deck_id);

    MissionModel getMission(int mission_id);

    MissionStart startMission(String member_id, int deck_id, int mission_id);

    int updateDeckPortMission(String member_id, int deck_id, DeckPortMission deckPortMission);
}
