package com.kancolle.server.model.kcsapi.member.record;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;

public class MemberRecordMission {

    @JSONField(ordinal = 1)
    private String api_count;

    @JSONField(ordinal = 2)
    private String api_success;

    @JSONField(ordinal = 3)
    private String api_rate;

    public MemberRecordMission() {
        this.api_count = StringUtils.EMPTY;
        this.api_success = StringUtils.EMPTY;
        this.api_rate = StringUtils.EMPTY;
    }

    public MemberRecordMission(int api_success, int api_count) {
        this.api_success = Integer.toString(api_success);
        this.api_count = Integer.toString(api_count);
        this.api_rate = String.format("%.2f", api_count == 0 ? 0d : (100d * api_success) / api_count);
    }

    public String getApi_count() {
        return api_count;
    }

    public void setApi_count(String api_count) {
        this.api_count = api_count;
    }

    public String getApi_success() {
        return api_success;
    }

    public void setApi_success(String api_success) {
        this.api_success = api_success;
    }

    public String getApi_rate() {
        return api_rate;
    }

    public void setApi_rate(String api_rate) {
        this.api_rate = api_rate;
    }

}
