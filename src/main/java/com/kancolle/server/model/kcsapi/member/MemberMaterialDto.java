package com.kancolle.server.model.kcsapi.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "api_member_id", "api_id", "api_value"
})
public class MemberMaterialDto implements Serializable {

    @JsonProperty(value = "api_member_id")
    private long api_member_id;

    @JsonProperty(value = "api_id")
    private int api_id;

    @JsonProperty(value = "api_value")
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

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public void setApi_member_id(long api_member_id) {
        this.api_member_id = api_member_id;
    }

    public void setApi_value(int api_value) {
        this.api_value = api_value;
    }
}
