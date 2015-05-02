package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;

public class MemberNDock {

    @JSONField(ordinal = 1)
    private long api_member_id;

    @JSONField(ordinal = 2)
    private int api_id;

    @JSONField(ordinal = 3)
    private int api_state;

    @JSONField(ordinal = 4)
    private int api_ship_id;

    @JSONField(ordinal = 5)
    private long api_complete_time;

    @JSONField(ordinal = 6)
    private String api_complete_time_str;

    @JSONField(ordinal = 7)
    private int api_item1;

    @JSONField(ordinal = 8)
    private int api_item2;

    @JSONField(ordinal = 9)
    private int api_item3;

    @JSONField(ordinal = 10)
    private int api_item4;

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

    public int getApi_state() {
        return api_state;
    }

    @Column(name = "STATE", type = int.class)
    public void setApi_state(int api_state) {
        this.api_state = api_state;
    }

    public int getApi_ship_id() {
        return api_ship_id;
    }

    @Column(name = "SHIP_ID", type = int.class)
    public void setApi_ship_id(int api_ship_id) {
        this.api_ship_id = api_ship_id;
    }

    public long getApi_complete_time() {
        return api_complete_time;
    }

    @Column(name = "COMPLETE_TIME", type = long.class)
    public void setApi_complete_time(long api_complete_time) {
        this.api_complete_time = api_complete_time;
    }

    public String getApi_complete_time_str() {
        return api_complete_time_str;
    }

    @Column(name = "COMPLETE_TIME_STR", type = String.class)
    public void setApi_complete_time_str(String api_complete_time_str) {
        this.api_complete_time_str = api_complete_time_str;
    }

    public int getApi_item1() {
        return api_item1;
    }

    @Column(name = "ITEM1", type = int.class)
    public void setApi_item1(int api_item1) {
        this.api_item1 = api_item1;
    }

    public int getApi_item2() {
        return api_item2;
    }

    @Column(name = "ITEM2", type = int.class)
    public void setApi_item2(int api_item2) {
        this.api_item2 = api_item2;
    }

    public int getApi_item3() {
        return api_item3;
    }

    @Column(name = "ITEM3", type = int.class)
    public void setApi_item3(int api_item3) {
        this.api_item3 = api_item3;
    }

    public int getApi_item4() {
        return api_item4;
    }

    @Column(name = "ITEM4", type = int.class)
    public void setApi_item4(int api_item4) {
        this.api_item4 = api_item4;
    }
}
