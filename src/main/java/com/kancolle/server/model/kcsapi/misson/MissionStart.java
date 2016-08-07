package com.kancolle.server.model.kcsapi.misson;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {
        "api_complatetime", "api_complatetime_str"
})
public class MissionStart {

    @JsonProperty(value = "api_complatetime")
    @JSONField(ordinal = 1)
    private long api_complatetime;

    @JsonProperty(value = "api_complatetime_str")
    @JSONField(ordinal = 2)
    private String api_complatetime_str;

    public MissionStart() {
    }

    public MissionStart(long api_complatetime, String api_complatetime_str) {
        this.api_complatetime = api_complatetime;
        this.api_complatetime_str = api_complatetime_str;
    }

    public long getApi_complatetime() {
        return api_complatetime;
    }

    public String getApi_complatetime_str() {
        return api_complatetime_str;
    }

    public void setApi_complatetime(long api_complatetime) {
        this.api_complatetime = api_complatetime;
    }

    public void setApi_complatetime_str(String api_complatetime_str) {
        this.api_complatetime_str = api_complatetime_str;
    }
}
