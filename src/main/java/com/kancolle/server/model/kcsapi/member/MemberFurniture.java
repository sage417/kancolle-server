package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;

public class MemberFurniture {

    @JSONField(ordinal = 1)
    private long api_member_id;

    @JSONField(ordinal = 2)
    private int api_id;

    @JSONField(ordinal = 3)
    private int api_furniture_type;

    @JSONField(ordinal = 4)
    private int api_furniture_no;

    @JSONField(ordinal = 5)
    private int api_furniture_id;

    public long getApi_member_id() {
        return api_member_id;
    }

    @Column(name = "member_id", type = long.class)
    public void setApi_member_id(long api_member_id) {
        this.api_member_id = api_member_id;
    }

    public long getApi_id() {
        return api_id;
    }

    @Column(name = "furniture_id", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_furniture_type() {
        return api_furniture_type;
    }

    @Column(name = "furniture_type", type = int.class)
    public void setApi_furniture_type(int api_furniture_type) {
        this.api_furniture_type = api_furniture_type;
    }

    public int getApi_furniture_no() {
        return api_furniture_no;
    }

    @Column(name = "furniture_no", type = int.class)
    public void setApi_furniture_no(int api_furniture_no) {
        this.api_furniture_no = api_furniture_no;
    }

    public int getApi_furniture_id() {
        return api_furniture_id;
    }

    @Column(name = "furniture_id", type = int.class)
    public void setApi_furniture_id(int api_furniture_id) {
        this.api_furniture_id = api_furniture_id;
    }
}
