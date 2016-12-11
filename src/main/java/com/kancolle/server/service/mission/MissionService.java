package com.kancolle.server.service.mission;

import com.kancolle.server.controller.kcsapi.form.mission.MissionStartForm;
import com.kancolle.server.model.kcsapi.misson.MissionResult;
import com.kancolle.server.model.kcsapi.misson.MissionReturn;
import com.kancolle.server.model.kcsapi.misson.MissionStart;
import com.kancolle.server.model.po.mission.Mission;

import java.util.List;

public interface MissionService {

    List<Mission> getMissions();

    Mission getMission(Integer mission_id);

    MissionStart startMission(long member_id, MissionStartForm form);

    MissionResult calMissionResult(long member_id, Integer deck_id);

    MissionReturn callbackMission(long member_id, int deck_id);

    void initMemberMission(long member_id);
}
