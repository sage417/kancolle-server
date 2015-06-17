package com.kancolle.server.model.kcsapi.member.record;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;

public class MemberRecordFight {

    @JSONField(ordinal = 1)
    private String api_win;

    @JSONField(ordinal = 2)
    private String api_lose;

    @JSONField(ordinal = 3)
    private String api_rate;

    public MemberRecordFight() {
        this.api_win = StringUtils.EMPTY;
        this.api_lose = StringUtils.EMPTY;
        this.api_rate = StringUtils.EMPTY;
    }

    public MemberRecordFight(int api_win, int api_lose) {
        this.api_win = Integer.toString(api_win);
        this.api_lose = Integer.toString(api_lose);
        int api_count = api_win + api_lose;
        this.api_rate = String.format("%.2f", api_count == 0 ? 0f : (1f * api_win) / api_count);
    }

    public String getApi_win() {
        return api_win;
    }

    public void setApi_win(String api_win) {
        this.api_win = api_win;
    }

    public String getApi_lose() {
        return api_lose;
    }

    public void setApi_lose(String api_lose) {
        this.api_lose = api_lose;
    }

    public String getApi_rate() {
        return api_rate;
    }

    public void setApi_rate(String api_rate) {
        this.api_rate = api_rate;
    }
}
