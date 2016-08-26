package com.kancolle.server.model.kcsapi.ship;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;

import java.util.List;

@JsonPropertyOrder(value = {
        "api_ship_data", "api_deck_data"
})
public class ShipDeckResult {

    @JsonProperty(value = "api_ship_data")
    @JSONField(ordinal = 1)
    private List<MemberShip> api_ship_data;

    @JsonProperty(value = "api_deck_data")
    @JSONField(ordinal = 2)
    private List<MemberDeckPort> api_deck_data;

    public ShipDeckResult(List<MemberShip> memberShips, List<MemberDeckPort> deckPorts) {
        this.api_ship_data = memberShips;
        this.api_deck_data = deckPorts;
    }

    public List<MemberShip> getApi_ship_data() {
        return api_ship_data;
    }

    public void setApi_ship_data(List<MemberShip> api_ship_data) {
        this.api_ship_data = api_ship_data;
    }

    public List<MemberDeckPort> getApi_deck_data() {
        return api_deck_data;
    }

    public void setApi_deck_data(List<MemberDeckPort> api_deck_data) {
        this.api_deck_data = api_deck_data;
    }

}
