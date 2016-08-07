package com.kancolle.server.model.kcsapi.duty;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.kcsapi.duty.bunous.Bonus;
import com.kancolle.server.model.kcsapi.duty.bunous.BonusItem;
import com.kancolle.server.model.kcsapi.duty.bunous.BonusShip;

@JsonPropertyOrder(value = {
        "api_type", "api_count", "api_item"
})
public class DutyBonusResult {

    @JsonProperty(value = "api_type")
    @JSONField(ordinal = 1)
    private int api_type;

    @JsonProperty(value = "api_count")
    @JSONField(ordinal = 2)
    private int api_count;

    @JsonProperty(value = "api_item")
    @JSONField(ordinal = 3)
    private Bonus api_item;

    private DutyBonusResult(int api_type, int api_count) {
        super();
        this.api_type = api_type;
        this.api_count = api_count;
    }

    public DutyBonusResult(int api_type, int api_count, int item_id, String item_name) {
        this(api_type, api_count);
        this.api_item = new BonusItem(item_id, item_name);
    }

    public DutyBonusResult(int api_type, int api_count, int ship_id) {
        this(api_type, api_count);
        this.api_item = new BonusShip(ship_id);
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

    public Bonus getApi_item() {
        return api_item;
    }

    public void setApi_item(Bonus api_item) {
        this.api_item = api_item;
    }
}
