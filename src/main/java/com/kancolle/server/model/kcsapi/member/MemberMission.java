package com.kancolle.server.model.kcsapi.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.ibatis.type.Alias;

@Alias("MemberMission")
@JsonPropertyOrder(value = {
        "mission_id", "state",
})
public class MemberMission {
    public static final int STATUS_NEW = 0;

    public static final int STATUS_PROCESSING = 1;

    public static final int STATUS_COMPLETE = 2;

    @JsonIgnore
    private long id;

    @JsonIgnore
    private long member_id;

    @JsonProperty(value = "api_mission_id")
    private int mission_id;

    @JsonProperty(value = "api_state")
    private int state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public int getMission_id() {
        return mission_id;
    }

    public void setMission_id(int mission_id) {
        this.mission_id = mission_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
