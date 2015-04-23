package com.kancolle.server.modle.kcsapi.start.sub;

public class UseItemModel {

    private int api_id;

    private int api_usetype;

    private int api_category;

    private String api_name;

    private String[] api_description;

    private int api_price;

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_usetype() {
        return api_usetype;
    }

    public void setApi_usetype(int api_usetype) {
        this.api_usetype = api_usetype;
    }

    public int getApi_category() {
        return api_category;
    }

    public void setApi_category(int api_category) {
        this.api_category = api_category;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String[] getApi_description() {
        return api_description;
    }

    public void setApi_description(String[] api_description) {
        this.api_description = api_description;
    }

    public int getApi_price() {
        return api_price;
    }

    public void setApi_price(int api_price) {
        this.api_price = api_price;
    }
}
