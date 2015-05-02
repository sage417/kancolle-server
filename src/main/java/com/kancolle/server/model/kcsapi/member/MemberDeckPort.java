package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;

public class MemberDeckPort {

    @JSONField(ordinal = 1)
    private long api_member_id;

    @JSONField(ordinal = 2)
    private int api_id;

    @JSONField(ordinal = 3)
    private String api_name;

    @JSONField(ordinal = 4)
    private String api_name_id;

    @JSONField(ordinal = 5)
    private JSONArray api_mission;

    @JSONField(ordinal = 6)
    private String api_flagship;

    @JSONField(ordinal = 7)
    private JSONArray api_ship;

    public long getApi_member_id() {
        return api_member_id;
    }

    @Column(name = "member_id", type = long.class)
    public void setApi_member_id(long api_member_id) {
        this.api_member_id = api_member_id;
    }

    public int getApi_id() {
        return api_id;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public String getApi_name() {
        return api_name;
    }

    @Column(name = "NAME", type = String.class)
    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getApi_name_id() {
        return api_name_id;
    }

    @Column(name = "NAME_ID", type = String.class)
    public void setApi_name_id(String api_name_id) {
        this.api_name_id = api_name_id;
    }

    public JSONArray getApi_mission() {
        return api_mission;
    }

    @Column(name = "MISSION", type = String.class)
    public void setApi_mission(String api_mission) {
        this.api_mission = JSON.parseArray(api_mission);
    }

    public String getApi_flagship() {
        return api_flagship;
    }

    @Column(name = "FLAGSHIP", type = String.class)
    public void setApi_flagship(String api_flagship) {
        this.api_flagship = api_flagship;
    }

    public JSONArray getApi_ship() {
        return api_ship;
    }

    @Column(name = "SHIP", type = String.class)
    public void setApi_ship(String api_ship) {
        this.api_ship = JSON.parseArray(api_ship);
    }
}
