package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;

public class MapBgmModel {

    @JSONField(ordinal = 1)
    private int api_id;

    @JSONField(ordinal = 2)
    private int api_maparea_id;

    @JSONField(ordinal = 3)
    private int api_no;

    @JSONField(ordinal = 4)
    private JSONArray api_map_bgm;

    @JSONField(ordinal = 5)
    private JSONArray api_boss_bgm;

    public JSONArray getApi_boss_bgm() {
        return api_boss_bgm;
    }

    public int getApi_id() {
        return api_id;
    }

    public JSONArray getApi_map_bgm() {
        return api_map_bgm;
    }

    public int getApi_maparea_id() {
        return api_maparea_id;
    }

    public int getApi_no() {
        return api_no;
    }

    @Column(name = "BOSS_BGM", type = String.class)
    public void setApi_boss_bgm(String api_boss_bgm) {
        this.api_boss_bgm = JSON.parseArray(api_boss_bgm);
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Column(name = "MAP_BGM", type = String.class)
    public void setApi_map_bgm(String api_map_bgm) {
        this.api_map_bgm = JSON.parseArray(api_map_bgm);
    }

    @Column(name = "MAPAREA_ID", type = int.class)
    public void setApi_maparea_id(int api_maparea_id) {
        this.api_maparea_id = api_maparea_id;
    }

    @Column(name = "NO", type = int.class)
    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }
}
