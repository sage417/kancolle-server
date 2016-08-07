package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.dao.annotation.Column;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "api_id", "api_name", "api_type"
})
public class MapAreaModel implements Serializable {

    private static final long serialVersionUID = 2814768531801354507L;

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1)
    private int api_id;

    @JsonProperty(value = "api_name")
    @JSONField(ordinal = 2)
    private String api_name;

    @JsonProperty(value = "api_type")
    @JSONField(ordinal = 3)
    private int api_type;

    public int getApi_id() {
        return api_id;
    }

    public String getApi_name() {
        return api_name;
    }

    public int getApi_type() {
        return api_type;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Column(name = "NAME", type = String.class)
    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    @Column(name = "TYPE", type = int.class)
    public void setApi_type(int api_type) {
        this.api_type = api_type;
    }
}
