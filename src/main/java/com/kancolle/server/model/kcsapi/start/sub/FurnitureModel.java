package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;

public class FurnitureModel {

    @JSONField(ordinal = 1)
    private int api_id;

    @JSONField(ordinal = 1)
    private int api_type;

    @JSONField(ordinal = 2)
    private int api_no;

    @JSONField(ordinal = 3)
    private String api_title;

    @JSONField(ordinal = 4)
    private String api_description;

    @JSONField(ordinal = 5)
    private int api_rarity;

    @JSONField(ordinal = 6)
    private int api_price;

    @JSONField(ordinal = 7)
    private int api_saleflg;

    @JSONField(ordinal = 8)
    private int api_season;

    public String getApi_description() {
        return api_description;
    }

    public int getApi_id() {
        return api_id;
    }

    public int getApi_no() {
        return api_no;
    }

    public int getApi_price() {
        return api_price;
    }

    public int getApi_rarity() {
        return api_rarity;
    }

    public int getApi_saleflg() {
        return api_saleflg;
    }

    public int getApi_season() {
        return api_season;
    }

    public String getApi_title() {
        return api_title;
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

    @Column(name = "NO", type = int.class)
    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }

    @Column(name = "PRICE", type = int.class)
    public void setApi_price(int api_price) {
        this.api_price = api_price;
    }

    @Column(name = "RARITY", type = int.class)
    public void setApi_rarity(int api_rarity) {
        this.api_rarity = api_rarity;
    }

    @Column(name = "SALEFLG", type = int.class)
    public void setApi_saleflg(int api_saleflg) {
        this.api_saleflg = api_saleflg;
    }

    @Column(name = "SEASON", type = int.class)
    public void setApi_season(int api_season) {
        this.api_season = api_season;
    }

    @Column(name = "TITLE", type = String.class)
    public void setApi_title(String api_title) {
        this.api_title = api_title;
    }

    @Column(name = "TYPE", type = int.class)
    public void setApi_type(int api_type) {
        this.api_type = api_type;
    }
}
