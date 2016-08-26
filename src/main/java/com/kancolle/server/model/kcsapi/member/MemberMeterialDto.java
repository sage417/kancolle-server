package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.dao.annotation.Column;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "api_member_id", "api_id", "api_value"
})
public class MemberMeterialDto implements Serializable {

    @JsonProperty(value = "api_member_id")
    @JSONField(ordinal = 1)
    private long api_member_id;

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 2)
    private int api_id;

    @JsonProperty(value = "api_value")
    @JSONField(ordinal = 3)
    private int api_value;

    public int getApi_id() {
        return api_id;
    }

    public long getApi_member_id() {
        return api_member_id;
    }

    public int getApi_value() {
        return api_value;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Column(name = "member_id", type = long.class)
    public void setApi_member_id(long api_member_id) {
        this.api_member_id = api_member_id;
    }

    @Column(name = "VALUE", type = int.class)
    public void setApi_value(int api_value) {
        this.api_value = api_value;
    }
}
