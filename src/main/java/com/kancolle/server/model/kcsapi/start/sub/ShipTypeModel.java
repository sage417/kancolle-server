package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.mapper.annotation.Column;
import com.kancolle.server.mapper.annotation.Table;

@Table("t_ship_type")
public class ShipTypeModel {

    @JSONField(ordinal = 1)
    private int api_id;

    @JSONField(ordinal = 2)
    private int api_sortno;

    @JSONField(ordinal = 3)
    private String api_name;

    @JSONField(ordinal = 4)
    private int api_scnt;

    @JSONField(ordinal = 5)
    private int api_kcnt;

    @JSONField(ordinal = 6)
    private JSONObject api_equip_type;

    public int getApi_id() {
        return api_id;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_sortno() {
        return api_sortno;
    }

    @Column(name = "SORTNO", type = int.class)
    public void setApi_sortno(int api_sortno) {
        this.api_sortno = api_sortno;
    }

    public String getApi_name() {
        return api_name;
    }

    @Column(name = "NAME", type = String.class)
    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public int getApi_scnt() {
        return api_scnt;
    }

    @Column(name = "SCNT", type = int.class)
    public void setApi_scnt(int api_scnt) {
        this.api_scnt = api_scnt;
    }

    public int getApi_kcnt() {
        return api_kcnt;
    }

    @Column(name = "KCNT", type = int.class)
    public void setApi_kcnt(int api_kcnt) {
        this.api_kcnt = api_kcnt;
    }

    public JSONObject getApi_equip_type() {
        return api_equip_type;
    }

    @Column(name = "EQUIP_TYPE", type = JSONObject.class)
    public void setApi_equip_type(String api_equip_type) {
        this.api_equip_type = JSON.parseObject(api_equip_type);
    }
}
