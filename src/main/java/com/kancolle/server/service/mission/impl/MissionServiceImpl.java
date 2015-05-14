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
import com.kancolle.server.model.kcsapi.misson.MissionReturn;
import com.kancolle.server.model.kcsapi.misson.MissionStart;
import com.kancolle.server.model.po.DeckPortMission;
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
        
        DeckPortMission deckPortMission = new DeckPortMission(MISSION_PROCESSING, mission_id, mission_complete_longtime, MISSION_FLAG);
        
        this.updateDeckPortMission(member_id, deck_id, deckPortMission);

        return new MissionStart(mission_complete_longtime, FORMATTER.format(LocalDateTime.ofInstant(mission_complete_instant, ZoneId.systemDefault())));
    }

    @Override
    public void calMissionResult(int member_id, int deck_id) {
        // TODO Auto-generated method stub

    }

    @Override
    public MissionReturn callbackMission(String member_id, int deck_id) {
        Instant now = Instant.now();

        DeckPortMission deckMission = this.getDeckPortMission(member_id, deck_id);
        int mission_id = deckMission.getMissionId();
        long mission_complete_longtime = deckMission.getMission_complete_time();

        int mission_time = this.getMissionTime(mission_id);
        Instant mission_start_instant = now.plus(-mission_time, ChronoUnit.MINUTES);

        // 返回所需时间为远征出发时间和远征剩余时间小的一个的1/3
        long return_longtime = Math.min(mission_complete_longtime - now.getEpochSecond(), now.getEpochSecond() - mission_start_instant.getEpochSecond()) / 3;
        long mission_return_longtime = now.plus(return_longtime, ChronoUnit.SECONDS).getEpochSecond();

        DeckPortMission deckPortMission = new DeckPortMission(MISSION_RETURNING, mission_id, mission_return_longtime, MISSION_FLAG);
        this.updateDeckPortMission(member_id, deck_id, deckPortMission);

        return new MissionReturn(new long[] { MISSION_RETURNING, mission_id, mission_return_longtime, MISSION_FLAG });
    }

    @Override
    @Cacheable(value = "mission", key = "#mission_id")
    public int getMissionTime(int mission_id) {
        return missionDao.getMissionTime(mission_id);
    }

    @Override
    public DeckPortMission getDeckPortMission(String member_id, int deck_id) {
        return missionDao.getDeckPortMission(member_id, deck_id);
    }

    @Override
    public int updateDeckPortMission(String member_id, int deck_id,DeckPortMission deckPortMission) {
        return missionDao.updateDeckPortMission(member_id, deck_id, deckPortMission);
    }
}
