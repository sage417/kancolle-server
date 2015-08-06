package com.kancolle.server.model.kcsapi.duty.bunous;

import com.alibaba.fastjson.annotation.JSONField;

public class BounsItem implements Bouns {

    @JSONField(ordinal = 1)
    private int api_id;

    @JSONField(ordinal = 2)
    private String api_name;

    public BounsItem(int item_id, String item_name) {
        this.api_id = item_id;
        this.api_name = item_name;
    }

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }
}
