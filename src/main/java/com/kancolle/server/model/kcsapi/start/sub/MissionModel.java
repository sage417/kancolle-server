package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.mapper.annotation.Column;

public class MissionModel {

    @JSONField(ordinal = 1)
    private int api_id;

    @JSONField(ordinal = 2)
    private int api_maparea_id;

    @JSONField(ordinal = 3)
    private String api_name;

    @JSONField(ordinal = 4)
    private String api_details;

    @JSONField(ordinal = 5)
    private int api_time;

    @JSONField(ordinal = 6)
    private int api_difficulty;

    @JSONField(ordinal = 7)
    private double api_use_fuel;

    @JSONField(ordinal = 8)
    private double api_use_bull;

    @JSONField(ordinal = 9)
    private JSONArray api_win_item1;

    @JSONField(ordinal = 10)
    private JSONArray api_win_item2;

    @JSONField(ordinal = 11)
    private int api_return_flag;

    public int getApi_id() {
        return api_id;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_maparea_id() {
        return api_maparea_id;
    }

    @Column(name = "MAPAREA_ID", type = int.class)
    public void setApi_maparea_id(int api_maparea_id) {
        this.api_maparea_id = api_maparea_id;
    }

    public String getApi_name() {
        return api_name;
    }

    @Column(name = "NAME", type = String.class)
    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getApi_details() {
        return api_details;
    }

    @Column(name = "DETAILS", type = String.class)
    public void setApi_details(String api_details) {
        this.api_details = api_details;
    }

    public int getApi_time() {
        return api_time;
    }

    @Column(name = "TIME", type = int.class)
    public void setApi_time(int api_time) {
        this.api_time = api_time;
    }

    public int getApi_difficulty() {
        return api_difficulty;
    }

    @Column(name = "DIFFICULTY", type = int.class)
    public void setApi_difficulty(int api_difficulty) {
        this.api_difficulty = api_difficulty;
    }

    public double getApi_use_fuel() {
        return api_use_fuel;
    }

    @Column(name = "USE_FUEL", type = double.class)
    public void setApi_use_fuel(double api_use_fuel) {
        this.api_use_fuel = api_use_fuel;
    }

    public double getApi_use_bull() {
        return api_use_bull;
    }

    @Column(name = "USE_BULL", type = double.class)
    public void setApi_use_bull(double api_use_bull) {
        this.api_use_bull = api_use_bull;
    }

    public JSONArray getApi_win_item1() {
        return api_win_item1;
    }

    @Column(name = "WIN_ITEM1", type = String.class)
    public void setApi_win_item1(String api_win_item1) {
        this.api_win_item1 = JSON.parseArray(api_win_item1);
    }

    public JSONArray getApi_win_item2() {
        return api_win_item2;
    }

    @Column(name = "WIN_ITEM2", type = String.class)
    public void setApi_win_item2(String api_win_item2) {
        this.api_win_item2 = JSONArray.parseArray(api_win_item2);
    }

    public int getApi_return_flag() {
        return api_return_flag;
    }

    @Column(name = "RETURN_FLAG", type = int.class)
    public void setApi_return_flag(int api_return_flag) {
        this.api_return_flag = api_return_flag;
    }
}
