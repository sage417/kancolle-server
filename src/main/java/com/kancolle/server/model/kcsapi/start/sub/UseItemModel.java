package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kancolle.server.mapper.annotation.Column;

public class UseItemModel {

    private int api_id;

    private int api_usetype;

    private int api_category;

    private String api_name;

    private JSONArray api_description;

    private int api_price;

    public int getApi_id() {
        return api_id;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_usetype() {
        return api_usetype;
    }

    @Column(name = "USETYPE", type = int.class)
    public void setApi_usetype(int api_usetype) {
        this.api_usetype = api_usetype;
    }

    public int getApi_category() {
        return api_category;
    }

    @Column(name = "CATEGORY", type = int.class)
    public void setApi_category(int api_category) {
        this.api_category = api_category;
    }

    public String getApi_name() {
        return api_name;
    }

    @Column(name = "NAME", type = int.class)
    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public JSONArray getApi_description() {
        return api_description;
    }

    @Column(name = "DESCRIPTION", type = int.class)
    public void setApi_description(String api_description) {
        this.api_description = JSON.parseArray(api_description);
    }

    public int getApi_price() {
        return api_price;
    }

    @Column(name = "PRICE", type = int.class)
    public void setApi_price(int api_price) {
        this.api_price = api_price;
    }
}
