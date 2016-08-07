package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.dao.annotation.Column;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "api_id", "api_original_ship_id", "api_upgrade_type", "api_upgrade_level",
        "api_drawing_count", "api_sortno"
})
public class ShipUpgradeModel implements Serializable {

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1)
    private int api_id;

    @JsonProperty(value = "api_original_ship_id")
    @JSONField(ordinal = 2)
    private int api_original_ship_id;

    @JsonProperty(value = "api_upgrade_type")
    @JSONField(ordinal = 3)
    private int api_upgrade_type;

    @JsonProperty(value = "api_upgrade_level")
    @JSONField(ordinal = 4)
    private int api_upgrade_level;

    @JsonProperty(value = "api_drawing_count")
    @JSONField(ordinal = 5)
    private int api_drawing_count;

    @JsonProperty(value = "api_sortno")
    @JSONField(ordinal = 6)
    private int api_sortno;

    public int getApi_drawing_count() {
        return api_drawing_count;
    }

    public int getApi_id() {
        return api_id;
    }

    public int getApi_original_ship_id() {
        return api_original_ship_id;
    }

    public int getApi_sortno() {
        return api_sortno;
    }

    public int getApi_upgrade_level() {
        return api_upgrade_level;
    }

    public int getApi_upgrade_type() {
        return api_upgrade_type;
    }

    @Column(name = "DRAWING_COUNT", type = int.class)
    public void setApi_drawing_count(int api_drawing_count) {
        this.api_drawing_count = api_drawing_count;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Column(name = "ORIGINAL_SHIP_ID", type = int.class)
    public void setApi_original_ship_id(int api_original_ship_id) {
        this.api_original_ship_id = api_original_ship_id;
    }

    @Column(name = "SORTNO", type = int.class)
    public void setApi_sortno(int api_sortno) {
        this.api_sortno = api_sortno;
    }

    @Column(name = "UPGRADE_LEVEL", type = int.class)
    public void setApi_upgrade_level(int api_upgrade_level) {
        this.api_upgrade_level = api_upgrade_level;
    }

    @Column(name = "UPGRADE_TYPE", type = int.class)
    public void setApi_upgrade_type(int api_upgrade_type) {
        this.api_upgrade_type = api_upgrade_type;
    }
}
