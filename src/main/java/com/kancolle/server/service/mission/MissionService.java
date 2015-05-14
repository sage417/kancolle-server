package com.kancolle.server.service.mission;

import com.kancolle.server.model.kcsapi.misson.MissionStart;

public interface MissionService {

	MissionStart startMission(String member_id, int deck_id, int mission_id);

	void stopMission(int member_id, int deck_id);

	void returnMission(int member_id, int deck_id);
	
	int getMissionTime(int mission_id);
}
