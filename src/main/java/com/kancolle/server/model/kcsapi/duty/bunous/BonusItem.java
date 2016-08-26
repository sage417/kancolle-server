package com.kancolle.server.model.kcsapi.duty.bunous;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {
        "api_id", "api_name"
})
public class BonusItem implements Bonus {

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1)
    private int api_id;

    @JsonProperty(value = "api_name")
    @JSONField(ordinal = 2)
    private String api_name;

    public BonusItem(int item_id, String item_name) {
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
