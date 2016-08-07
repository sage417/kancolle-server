package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.dao.annotation.Column;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "api_no", "api_type", "api_state", "api_message"
})
public class MemberLog implements Serializable {

    @JsonProperty(value = "api_no")
    @JSONField(ordinal = 1)
    private int api_no;

    @JsonProperty(value = "api_type")
    @JSONField(ordinal = 2)
    private String api_type;

    @JsonProperty(value = "api_state")
    @JSONField(ordinal = 3)
    private String api_state;

    @JsonProperty(value = "api_message")
    @JSONField(ordinal = 4)
    private String api_message;

    public String getApi_message() {
        return api_message;
    }

    public int getApi_no() {
        return api_no;
    }

    public String getApi_state() {
        return api_state;
    }

    public String getApi_type() {
        return api_type;
    }

    @Column(name = "MESSAGE", type = String.class)
    public void setApi_message(String api_message) {
        this.api_message = api_message;
    }

    @Column(name = "NO", type = int.class)
    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }

    @Column(name = "STATE", type = String.class)
    public void setApi_state(String api_state) {
        this.api_state = api_state;
    }

    @Column(name = "NO", type = int.class)
    public void setApi_type(String api_type) {
        this.api_type = api_type;
    }
}
