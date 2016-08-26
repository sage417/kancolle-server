package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.dao.annotation.Column;

@JsonPropertyOrder(value = {
        "api_mission_id", "api_state",
})
public class MemberMission {
    public static final int STATUS_NEW = 0;

    public static final int STATUS_PROCESSING = 1;

    public static final int STATUS_COMPLETE = 2;

    @JsonProperty(value = "api_mission_id")
    @JSONField(ordinal = 1)
    private int api_mission_id;

    @JsonProperty(value = "api_state")
    @JSONField(ordinal = 2)
    private int api_state;

    public int getApi_mission_id() {
        return api_mission_id;
    }

    public int getApi_state() {
        return api_state;
    }

    @Column(name = "mission_id", type = int.class)
    public void setApi_mission_id(int api_mission_id) {
        this.api_mission_id = api_mission_id;
    }

    @Column(name = "state", type = int.class)
    public void setApi_state(int api_state) {
        this.api_state = api_state;
    }
}
