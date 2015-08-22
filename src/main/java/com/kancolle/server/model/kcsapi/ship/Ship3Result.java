package com.kancolle.server.model.kcsapi.ship;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;

public class Ship3Result {

    @JSONField(ordinal = 1)
    private List<MemberShip> api_ship_data;

    @JSONField(ordinal = 2)
    private List<MemberDeckPort> api_deck_data;

    @JSONField(ordinal = 3)
    private Map<String, Object> api_slot_data;

    public Ship3Result(MemberShip memberShip, List<MemberDeckPort> deckPorts, Map<String, Object> unsetSlot) {
        this.api_ship_data = Collections.singletonList(memberShip);
        this.api_deck_data = deckPorts;
        this.api_slot_data = unsetSlot;
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

    public Map<String, Object> getApi_slot_data() {
        return api_slot_data;
    }

    public void setApi_slot_data(Map<String, Object> api_slot_data) {
        this.api_slot_data = api_slot_data;
    }

}
