package com.kancolle.server.model.kcsapi.duty;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.kcsapi.duty.bunous.Bouns;
import com.kancolle.server.model.kcsapi.duty.bunous.BounsItem;
import com.kancolle.server.model.kcsapi.duty.bunous.BounsShip;

public class DutyBouns {

    @JSONField(ordinal = 1)
    private int api_type;

    @JSONField(ordinal = 2)
    private int api_count;

    @JSONField(ordinal = 3)
    private Bouns api_item;

    private DutyBouns(int api_type, int api_count) {
        super();
        this.api_type = api_type;
        this.api_count = api_count;
    }

    public DutyBouns(int api_type, int api_count, int item_id, String item_name) {
        this(api_type, api_count);
        this.api_item = new BounsItem(item_id, item_name);
    }

    public DutyBouns(int api_type, int api_count, int ship_id) {
        this(api_type, api_count);
        this.api_item = new BounsShip(ship_id);
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

    public Bouns getApi_item() {
        return api_item;
    }

    public void setApi_item(Bouns api_item) {
        this.api_item = api_item;
    }
}
