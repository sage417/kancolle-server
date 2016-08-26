package com.kancolle.server.dao.mission;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.mission.Mission;

import java.util.List;

public interface MissionDao extends BaseDao<Mission> {

    List<Mission> selectMissions();

    Mission selectMission(Integer mission_id);

    void insertMemberMissionRecords(long member_id);
}
