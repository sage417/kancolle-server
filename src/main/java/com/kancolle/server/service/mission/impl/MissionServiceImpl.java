package com.kancolle.server.service.mission.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.mission.MissionDao;
import com.kancolle.server.model.kcsapi.misson.MissionStart;
import com.kancolle.server.service.mission.MissionService;

@Service
public class MissionServiceImpl implements MissionService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static final int MISSION_PROCESSING = 1;
    
    public static final int MISSION_COMPLETE = 2;
    
    public static final int MISSION_RETURNING = 3;
    
    public static final int MISSION_FLAG = 0; 

    @Autowired
    private MissionDao<?> missionDao;

    @Override
    public MissionStart startMission(String member_id, int deck_id, int mission_id) {
        Instant now = Instant.now();

        int mission_time = this.getMissionTime(mission_id);
        Instant mission_complete_instant = now.plus(mission_time, ChronoUnit.MINUTES);

        long mission_complete_longtime = mission_complete_instant.getEpochSecond();
        missionDao.updateDeckPortMission(member_id, deck_id, MISSION_PROCESSING, mission_id, mission_complete_longtime, MISSION_FLAG);

        return new MissionStart(mission_complete_longtime, FORMATTER.format(LocalDateTime.ofInstant(mission_complete_instant, ZoneId.systemDefault())));
    }

    @Override
    public void stopMission(int member_id, int deck_id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void returnMission(int member_id, int deck_id) {
        // TODO Auto-generated method stub

    }

    @Override
    @Cacheable(value = "mission", key = "#mission_id")
    public int getMissionTime(int mission_id) {
        return missionDao.getMissionTime(mission_id);
    }
}
