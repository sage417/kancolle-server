package com.kancolle.server.model.kcsapi.misson;

public class MissionReturn {

    private long[] api_mission;

    public MissionReturn() {
    }

    public MissionReturn(long[] api_mission) {
        this.api_mission = api_mission;
    }

    public long[] getApi_mission() {
        return api_mission;
    }

    public void setApi_mission(long[] api_mission) {
        this.api_mission = api_mission;
    }
}
