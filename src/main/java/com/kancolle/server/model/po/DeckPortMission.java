package com.kancolle.server.model.po;

import com.kancolle.server.dao.annotation.Column;

public class DeckPortMission {

    private int missionStatus;

    private int missionId;

    private long mission_complete_time;

    private int mission_flag;
    
    public DeckPortMission() {
        super();
    }

    public DeckPortMission(int missionStatus, int missionId, long mission_complete_time, int mission_flag) {
        super();
        this.missionStatus = missionStatus;
        this.missionId = missionId;
        this.mission_complete_time = mission_complete_time;
        this.mission_flag = mission_flag;
    }

    public int getMissionStatus() {
        return missionStatus;
    }

    @Column(name="MISSION_STATUS",type=int.class)
    public void setMissionStatus(int missionStatus) {
        this.missionStatus = missionStatus;
    }

    public int getMissionId() {
        return missionId;
    }

    @Column(name="MISSION_ID",type=int.class)
    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public long getMission_complete_time() {
        return mission_complete_time;
    }

    @Column(name="MISSION_COMPLETE_TIME",type=long.class)
    public void setMission_complete_time(long mission_complete_time) {
        this.mission_complete_time = mission_complete_time;
    }

    public int getMission_flag() {
        return mission_flag;
    }

    @Column(name="MISSION_FLAG",type=int.class)
    public void setMission_flag(int mission_flag) {
        this.mission_flag = mission_flag;
    }
}
