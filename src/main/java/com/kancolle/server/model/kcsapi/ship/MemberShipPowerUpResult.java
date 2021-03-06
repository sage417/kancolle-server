/**
 *
 */
package com.kancolle.server.model.kcsapi.ship;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年7月3日
 */
@JsonPropertyOrder(value = {
        "api_powerup_flag", "api_ship", "api_deck"
})
public class MemberShipPowerUpResult {
    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_FAILED = 0;

    @JsonProperty(value = "api_powerup_flag")
    @JSONField(ordinal = 1)
    private int api_powerup_flag;

    @JsonProperty(value = "api_ship")
    @JSONField(ordinal = 2)
    private MemberShip api_ship;

    @JsonProperty(value = "api_deck")
    @JSONField(ordinal = 3)
    private List<MemberDeckPort> api_deck;

    public MemberShipPowerUpResult(int success, MemberShip memberShip, List<MemberDeckPort> memberDeckPorts) {
        this.api_powerup_flag = success;
        this.api_ship = memberShip;
        this.api_deck = memberDeckPorts;
    }

    public int getApi_powerup_flag() {
        return api_powerup_flag;
    }

    public void setApi_powerup_flag(int api_powerup_flag) {
        this.api_powerup_flag = api_powerup_flag;
    }

    public MemberShip getApi_ship() {
        return api_ship;
    }

    public void setApi_ship(MemberShip api_ship) {
        this.api_ship = api_ship;
    }

    public List<MemberDeckPort> getApi_deck() {
        return api_deck;
    }

    public void setApi_deck(List<MemberDeckPort> api_deck) {
        this.api_deck = api_deck;
    }
}
