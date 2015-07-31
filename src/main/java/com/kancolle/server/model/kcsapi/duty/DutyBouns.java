package com.kancolle.server.model.kcsapi.duty;

import com.alibaba.fastjson.annotation.JSONField;

public class DutyBouns {

    @JSONField(ordinal = 1)
    private int api_type;

    @JSONField(ordinal = 2)
    private int api_count;

    @JSONField(ordinal = 3)
    private BounsItem api_item;

    public DutyBouns(int api_type, int api_count, int item_id, String item_name) {
        super();
        this.api_type = api_type;
        this.api_count = api_count;
        this.api_item = new BounsItem(item_id, item_name);
    }

    public int getApi_type() {
        return api_type;
    }

    public void setApi_type(int api_type) {
        this.api_type = api_type;
    }

    public int getApi_count() {
        return api_count;
    }

    public void setApi_count(int api_count) {
        this.api_count = api_count;
    }

    public BounsItem getApi_item() {
        return api_item;
    }

    public void setApi_item(BounsItem api_item) {
        this.api_item = api_item;
    }
}
