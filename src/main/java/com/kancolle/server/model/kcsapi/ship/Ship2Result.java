package com.kancolle.server.model.kcsapi.ship;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.response.APIResponse;

public class Ship2Result extends APIResponse<List<MemberShip>> {

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
