package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;

public class MemberMission {
    public static final int STATUS_NEW = 0;
    
    public static final int STATUS_PROCESSING =1;
    
    public static final int STATUS_COMPLETE=2;

    @JSONField(ordinal = 1)
    private int api_mission_id;

    @JSONField(ordinal = 1)
    private int api_state;

    public int getApi_mission_id() {
        return api_mission_id;
    }

    @Column(name = "mission_id", type = int.class)
    public void setApi_mission_id(int api_mission_id) {
        this.api_mission_id = api_mission_id;
    }

    public int getApi_state() {
        return api_state;
    }

    @Column(name = "state", type = int.class)
    public void setApi_state(int api_state) {
        this.api_state = api_state;
    }
}
