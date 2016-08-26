package com.kancolle.server.model.kcsapi.ship;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.response.APIResponse;

import java.util.List;

public class Ship2Result extends APIResponse<List<MemberShip>> {

    @JsonProperty(value = "api_data_deck")
    @JSONField(ordinal = 4)
    private List<MemberDeckPort> api_data_deck;

    public Ship2Result(List<MemberShip> memberShips, List<MemberDeckPort> memberDeckPorts) {
        super.setApi_data(memberShips);
        this.api_data_deck = memberDeckPorts;
    }

    public List<MemberDeckPort> getApi_data_deck() {
        return api_data_deck;
    }

    public void setApi_data_deck(List<MemberDeckPort> api_data_deck) {
        this.api_data_deck = api_data_deck;
    }
}
