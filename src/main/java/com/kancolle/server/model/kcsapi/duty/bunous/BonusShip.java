package com.kancolle.server.model.kcsapi.duty.bunous;

import com.alibaba.fastjson.annotation.JSONField;

public class BonusShip implements Bonus {

    @JSONField(ordinal = 1)
    private int api_ship_id;

    public BonusShip(int ship_id) {
        this.api_ship_id = ship_id;
    }

    public int getApi_ship_id() {
        return api_ship_id;
    }

    public void setApi_ship_id(int api_ship_id) {
        this.api_ship_id = api_ship_id;
    }
}
