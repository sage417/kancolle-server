package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;

public class UseItemModel {

    @JSONField(ordinal = 1)
    private int api_id;

    @JSONField(ordinal = 2)
    private int api_usetype;

    @JSONField(ordinal = 3)
    private int api_category;

    @JSONField(ordinal = 4)
    private String api_name;

    @JSONField(ordinal = 5)
    private JSONArray api_description;

    @JSONField(ordinal = 6)
    private int api_price;

    public int getApi_category() {
        return api_category;
    }

    public JSONArray getApi_description() {
        return api_description;
    }

    public int getApi_id() {
        return api_id;
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

    @Column(name = "CATEGORY", type = int.class)
    public void setApi_category(int api_category) {
        this.api_category = api_category;
    }

    @Column(name = "DESCRIPTION", type = String.class)
    public void setApi_description(String api_description) {
        this.api_description = JSON.parseArray(api_description);
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
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
}
