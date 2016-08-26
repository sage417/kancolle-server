package com.kancolle.server.model.kcsapi.battle.event;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Package: com.kancolle.server.model.kcsapi.battle.event
 * Author: mac
 * Date: 16/4/1
 */
@JsonPropertyOrder(value = {"ship_id", "ship_type", "ship_name", "ship_getmes"})
public class BattleGetShipResult {

    @JSONField(name = "api_ship_id", ordinal = 1)
    @JsonProperty(value = "api_ship_id")
    private int ship_id;

    @JSONField(name = "api_ship_type", ordinal = 2)
    @JsonProperty(value = "api_ship_type")
    private String ship_type;

    @JSONField(name = "api_ship_name", ordinal = 3)
    @JsonProperty(value = "api_ship_name")
    private String ship_name;

    @JSONField(name = "api_ship_getmes", ordinal = 4)
    @JsonProperty(value = "api_ship_getmes")
    private String ship_getmes;

    public String getShip_getmes() {
        return ship_getmes;
    }

    public void setShip_getmes(String ship_getmes) {
        this.ship_getmes = ship_getmes;
    }

    public int getShip_id() {
        return ship_id;
    }

    public void setShip_id(int ship_id) {
        this.ship_id = ship_id;
    }

    public String getShip_name() {
        return ship_name;
    }

    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }

    public String getShip_type() {
        return ship_type;
    }

    public void setShip_type(String ship_type) {
        this.ship_type = ship_type;
    }
}
