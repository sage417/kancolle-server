package com.kancolle.server.modle.kcsapi.start.sub;

public class PayItemModel {

    private int api_id;

    private int api_type;

    private String api_name;

    private String api_description;

    private int[] api_item;

    private int api_price;

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_type() {
        return api_type;
    }

    public void setApi_type(int api_type) {
        this.api_type = api_type;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getApi_description() {
        return api_description;
    }

    public void setApi_description(String api_description) {
        this.api_description = api_description;
    }

    public int[] getApi_item() {
        return api_item;
    }

    public void setApi_item(int[] api_item) {
        this.api_item = api_item;
    }

    public int getApi_price() {
        return api_price;
    }

    public void setApi_price(int api_price) {
        this.api_price = api_price;
    }
}
