package com.kancolle.server.dao.mission.impl;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.mission.MissionDao;
import com.kancolle.server.model.po.mission.Mission;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public void insertMemberMissionRecords(long member_id) {
        getSqlSession().insert("insertMemberMissionRecords", member_id);
    }
}
