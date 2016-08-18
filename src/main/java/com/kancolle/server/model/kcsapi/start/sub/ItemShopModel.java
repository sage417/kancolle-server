package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "cabinet_1","cabinet_2"
})
public class ItemShopModel implements Serializable{

    @Id
    private String id;

    @JsonProperty(value = "api_cabinet_1")
    @JSONField(ordinal = 1)
    private JSONArray cabinet_1;

    @JsonProperty(value = "api_cabinet_2")
    @JSONField(ordinal = 2)
    private JSONArray cabinet_2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JSONArray getCabinet_1() {
        return cabinet_1;
    }

    public JSONArray getCabinet_2() {
        return cabinet_2;
    }

    public void setCabinet_1(JSONArray cabinet_1) {
        this.cabinet_1 = cabinet_1;
    }

    public void setCabinet_2(JSONArray cabinet_2) {
        this.cabinet_2 = cabinet_2;
    }
}
