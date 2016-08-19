package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "api_cabinet_1","api_cabinet_2"
})
public class ItemShopModel implements Serializable{

    @JsonProperty(value = "api_cabinet_1")
    @JSONField(ordinal = 1)
    private int[] api_cabinet_1;

    @JsonProperty(value = "api_cabinet_2")
    @JSONField(ordinal = 2)
    private int[] api_cabinet_2;

    public int[] getApi_cabinet_1() {
        return api_cabinet_1;
    }

    public void setApi_cabinet_1(int[] api_cabinet_1) {
        this.api_cabinet_1 = api_cabinet_1;
    }

    public int[] getApi_cabinet_2() {
        return api_cabinet_2;
    }

    public void setApi_cabinet_2(int[] api_cabinet_2) {
        this.api_cabinet_2 = api_cabinet_2;
    }
}
