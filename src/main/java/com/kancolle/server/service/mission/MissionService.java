package com.kancolle.server.service.mission;

import com.kancolle.server.controller.kcsapi.form.mission.MissionStartForm;
import com.kancolle.server.model.kcsapi.misson.MissionResult;
import com.kancolle.server.model.kcsapi.misson.MissionReturn;
import com.kancolle.server.model.kcsapi.misson.MissionStart;
import com.kancolle.server.model.po.mission.Mission;

public interface MissionService {

    MissionReturn callbackMission(String member_id, int deck_id);

    MissionResult calMissionResult(String member_id, int deck_id);

    Mission getMission(Integer mission_id);

    MissionStart startMission(String member_id, MissionStartForm form);
}
