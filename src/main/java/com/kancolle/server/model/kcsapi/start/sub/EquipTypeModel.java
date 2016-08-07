package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.dao.annotation.Column;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "api_id", "api_name", "api_show_flg"
})
public class EquipTypeModel implements Serializable {

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1)
    private int api_id;

    @JsonProperty(value = "api_name")
    @JSONField(ordinal = 2)
    private String api_name;

    @JsonProperty(value = "api_show_flg")
    @JSONField(ordinal = 3)
    private int api_show_flg;

    public int getApi_id() {
        return api_id;
    }

    public String getApi_name() {
        return api_name;
    }

    public int getApi_show_flg() {
        return api_show_flg;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Column(name = "Type", type = String.class)
    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    @Column(name = "SHOW_FLAG", type = int.class)
    public void setApi_show_flg(int api_show_flg) {
        this.api_show_flg = api_show_flg;
    }
}
