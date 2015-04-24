package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kancolle.server.mapper.annotation.Column;

public class MapInfoModel {

    private int api_id;

    private int api_maparea_id;

    private int api_no;

    private String api_name;

    private int api_level;

    private String api_opetext;

    private String api_infotext;

    private JSONArray api_item;

    private int api_max_maphp;

    private int api_required_defeat_count;

    private JSONArray api_sally_flag;

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

    public int getApi_no() {
        return api_no;
    }

    @Column(name = "NO", type = int.class)
    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }

    public String getApi_name() {
        return api_name;
    }

    @Column(name = "NAME", type = String.class)
    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public int getApi_level() {
        return api_level;
    }

    @Column(name = "LEVEL", type = int.class)
    public void setApi_level(int api_level) {
        this.api_level = api_level;
    }

    public String getApi_opetext() {
        return api_opetext;
    }

    @Column(name = "OPETEXT", type = String.class)
    public void setApi_opetext(String api_opetext) {
        this.api_opetext = api_opetext;
    }

    public String getApi_infotext() {
        return api_infotext;
    }

    @Column(name = "INFOTEXT", type = String.class)
    public void setApi_infotext(String api_infotext) {
        this.api_infotext = api_infotext;
    }

    public JSONArray getApi_item() {
        return api_item;
    }

    @Column(name = "ITEM", type = JSONArray.class)
    public void setApi_item(String api_item) {
        this.api_item = JSON.parseArray(api_item);
    }

    public int getApi_max_maphp() {
        return api_max_maphp;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_max_maphp(int api_max_maphp) {
        this.api_max_maphp = api_max_maphp;
    }

    public int getApi_required_defeat_count() {
        return api_required_defeat_count;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_required_defeat_count(int api_required_defeat_count) {
        this.api_required_defeat_count = api_required_defeat_count;
    }

    public JSONArray getApi_sally_flag() {
        return api_sally_flag;
    }

    @Column(name = "SALLY_FALG", type = JSONArray.class)
    public void setApi_sally_flag(String api_sally_flag) {
        this.api_sally_flag = JSON.parseArray(api_sally_flag);
    }
}
