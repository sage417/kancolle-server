package com.kancolle.server.dao.mission;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.mission.Mission;

public interface MissionDao extends BaseDao<Mission> {

    List<Mission> selectMissions();

    Mission selectMission(Integer mission_id);
}
