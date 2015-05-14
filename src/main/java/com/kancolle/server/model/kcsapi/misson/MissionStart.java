package com.kancolle.server.model.kcsapi.misson;

import com.alibaba.fastjson.annotation.JSONField;

public class MissionStart {

    @JSONField(ordinal = 1)
    private long api_complatetime;

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

    public void setApi_complatetime(long api_complatetime) {
        this.api_complatetime = api_complatetime;
    }

    public String getApi_complatetime_str() {
        return api_complatetime_str;
    }

    public void setApi_complatetime_str(String api_complatetime_str) {
        this.api_complatetime_str = api_complatetime_str;
    }
}
