package com.kancolle.server.modle.kcsapi.start.sub;

import java.util.Map;

public class ShipTypeModel {

    private int api_id;

    private int api_sortno;

    private String api_name;

    private int api_scnt;

    private int api_kcnt;

    private Map<String, Integer> api_equip_type;

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_sortno() {
        return api_sortno;
    }

    public void setApi_sortno(int api_sortno) {
        this.api_sortno = api_sortno;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public int getApi_scnt() {
        return api_scnt;
    }

    public void setApi_scnt(int api_scnt) {
        this.api_scnt = api_scnt;
    }

    public int getApi_kcnt() {
        return api_kcnt;
    }

    public void setApi_kcnt(int api_kcnt) {
        this.api_kcnt = api_kcnt;
    }

    public Map<String, Integer> getApi_equip_type() {
        return api_equip_type;
    }

    public void setApi_equip_type(Map<String, Integer> api_equip_type) {
        this.api_equip_type = api_equip_type;
    }
}
