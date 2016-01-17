package com.kancolle.server.model.kcsapi.ship;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;

import java.util.List;

public class ShipDeckResult {

    @JSONField(ordinal = 1)
    private List<MemberShip> api_ship_data;

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
