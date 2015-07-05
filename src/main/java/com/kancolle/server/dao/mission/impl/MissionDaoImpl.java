package com.kancolle.server.dao.mission.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.mission.MissionDao;
import com.kancolle.server.model.po.mission.Mission;

@Repository
public class MissionDaoImpl extends BaseDaoImpl<Mission> implements MissionDao {

    @Override
    public List<Mission> selectMissions() {
        return getSqlSession().selectList("selectMissionByCond");
    }

    @Override
    public Mission selectMission(Integer mission_id) {
        return getSqlSession().selectOne("selectMissionByCond", mission_id);
    }
}
