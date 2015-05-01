package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.JSONArray;

public class MemberDeckPort {

    private int api_member_id;

    private int api_id;

    private String api_name;

    private String api_name_id;

    private JSONArray api_mission;

    private String api_flagship;

    private JSONArray api_ship;

    public int getApi_member_id() {
        return api_member_id;
    }

    public void setApi_member_id(int api_member_id) {
        this.api_member_id = api_member_id;
    }

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getApi_name_id() {
        return api_name_id;
    }

    public void setApi_name_id(String api_name_id) {
        this.api_name_id = api_name_id;
    }

    public JSONArray getApi_mission() {
        return api_mission;
    }

    public void setApi_mission(JSONArray api_mission) {
        this.api_mission = api_mission;
    }

    public String getApi_flagship() {
        return api_flagship;
    }

    public void setApi_flagship(String api_flagship) {
        this.api_flagship = api_flagship;
    }

    public JSONArray getApi_ship() {
        return api_ship;
    }

    public void setApi_ship(JSONArray api_ship) {
        this.api_ship = api_ship;
    }
}
