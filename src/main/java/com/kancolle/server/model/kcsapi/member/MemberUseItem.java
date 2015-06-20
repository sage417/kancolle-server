package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;

public class MemberUseItem {

    @JSONField(ordinal = 1)
    private String api_member_id;

    @JSONField(ordinal = 2)
    private int api_id;

    @JSONField(ordinal = 3)
    private int api_value;

    @JSONField(ordinal = 4)
    private int api_usetype;

    @JSONField(ordinal = 5)
    private int api_category;

    @JSONField(ordinal = 6)
    private String api_name;

    @JSONField(ordinal = 7)
    private String api_description;

    @JSONField(ordinal = 8)
    private int api_price;

    @JSONField(ordinal = 9)
    private int api_count;

    public int getApi_category() {
        return api_category;
    }

    public int getApi_count() {
        return api_count;
    }

    public String getApi_description() {
        return api_description;
    }

    public int getApi_id() {
        return api_id;
    }

    public String getApi_member_id() {
        return api_member_id;
    }

    public String getApi_name() {
        return api_name;
    }

    public int getApi_price() {
        return api_price;
    }

    public int getApi_usetype() {
        return api_usetype;
    }

    public int getApi_value() {
        return api_value;
    }

    @Column(name = "CATEGORY", type = int.class)
    public void setApi_category(int api_category) {
        this.api_category = api_category;
    }

    @Column(name = "COUNT", type = int.class)
    public void setApi_count(int api_count) {
        this.api_count = api_count;
    }

    @Column(name = "DESCRIPTION", type = String.class)
    public void setApi_description(String api_description) {
        this.api_description = api_description;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Column(name = "member_id", type = String.class)
    public void setApi_member_id(String api_member_id) {
        this.api_member_id = api_member_id;
    }

    @Column(name = "NAME", type = String.class)
    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    @Column(name = "PRICE", type = int.class)
    public void setApi_price(int api_price) {
        this.api_price = api_price;
    }

    @Column(name = "USETYPE", type = int.class)
    public void setApi_usetype(int api_usetype) {
        this.api_usetype = api_usetype;
    }

    @Column(name = "COUNT", type = int.class)
    public void setApi_value(int api_value) {
        this.api_value = api_value;
    }
}
