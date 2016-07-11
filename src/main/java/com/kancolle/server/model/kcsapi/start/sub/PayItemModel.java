package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;

import java.io.Serializable;

public class PayItemModel implements Serializable{

    @JSONField(ordinal = 1)
    private int api_id;

    @JSONField(ordinal = 2)
    private int api_type;

    @JSONField(ordinal = 3)
    private String api_name;

    @JSONField(ordinal = 4)
    private String api_description;

    @JSONField(ordinal = 5)
    private JSONArray api_item;

    @JSONField(ordinal = 6)
    private int api_price;

    public String getApi_description() {
        return api_description;
    }

    public int getApi_id() {
        return api_id;
    }

    public JSONArray getApi_item() {
        return api_item;
    }

    public String getApi_name() {
        return api_name;
    }

    public int getApi_price() {
        return api_price;
    }

    public int getApi_type() {
        return api_type;
    }

    @Column(name = "DESCRIPTION", type = String.class)
    public void setApi_description(String api_description) {
        this.api_description = api_description;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Column(name = "ITEM", type = String.class)
    public void setApi_item(String api_item) {
        this.api_item = JSON.parseArray(api_item);
    }

    @Column(name = "NAME", type = String.class)
    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    @Column(name = "PRICE", type = int.class)
    public void setApi_price(int api_price) {
        this.api_price = api_price;
    }

    @Column(name = "TYPE", type = int.class)
    public void setApi_type(int api_type) {
        this.api_type = api_type;
    }
}
