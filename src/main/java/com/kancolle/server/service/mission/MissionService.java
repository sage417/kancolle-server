package com.kancolle.server.service.mission;

import com.kancolle.server.model.kcsapi.misson.MissionReturn;
import com.kancolle.server.model.kcsapi.misson.MissionStart;
import com.kancolle.server.model.po.DeckPortMission;

public interface MissionService {

    MissionStart startMission(String member_id, int deck_id, int mission_id);

    void calMissionResult(int member_id, int deck_id);

    MissionReturn callbackMission(String member_id, int deck_id);

    int getMissionTime(int mission_id);
    
    DeckPortMission getDeckPortMission(String member_id, int deck_id);
    
    int updateDeckPortMission(String member_id, int deck_id,DeckPortMission deckPortMission);
}
