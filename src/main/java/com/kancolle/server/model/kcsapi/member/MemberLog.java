package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;

public class MemberLog {

    @JSONField(ordinal = 1)
    private int api_no;

    @JSONField(ordinal = 2)
    private String api_type;

    @JSONField(ordinal = 3)
    private String api_state;

    @JSONField(ordinal = 4)
    private String api_message;

    public int getApi_no() {
        return api_no;
    }

    @Column(name = "NO", type = int.class)
    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }

    public String getApi_type() {
        return api_type;
    }

    @Column(name = "NO", type = int.class)
    public void setApi_type(String api_type) {
        this.api_type = api_type;
    }

    public String getApi_state() {
        return api_state;
    }

    @Column(name = "STATE", type = String.class)
    public void setApi_state(String api_state) {
        this.api_state = api_state;
    }

    public String getApi_message() {
        return api_message;
    }

    @Column(name = "MESSAGE", type = String.class)
    public void setApi_message(String api_message) {
        this.api_message = api_message;
    }
}
