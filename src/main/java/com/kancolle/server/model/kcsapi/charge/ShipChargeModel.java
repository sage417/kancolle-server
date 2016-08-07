/**
 *
 */
package com.kancolle.server.model.kcsapi.charge;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.po.ship.MemberShip;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 */
@JsonPropertyOrder(value = {
        "api_id", "api_fuel", "api_bull", "api_onslot"
})
public class ShipChargeModel {

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1)
    private long api_id;

    @JsonProperty(value = "api_fuel")
    @JSONField(ordinal = 2)
    private int api_fuel;

    @JsonProperty(value = "api_bull")
    @JSONField(ordinal = 3)
    private int api_bull;

    @JsonProperty(value = "api_onslot")
    @JSONField(ordinal = 4)
    private int[] api_onslot;

    /**
     * @param memberShip
     */
    public ShipChargeModel(MemberShip memberShip) {
        this.api_id = memberShip.getMemberShipId();
        this.api_fuel = memberShip.getFuel();
        this.api_bull = memberShip.getBull();
        this.api_onslot = memberShip.getOnslot();
    }

    public long getApi_id() {
        return api_id;
    }

    public void setApi_id(long api_id) {
        this.api_id = api_id;
    }

    public int getApi_fuel() {
        return api_fuel;
    }

    public void setApi_fuel(int api_fuel) {
        this.api_fuel = api_fuel;
    }

    public int getApi_bull() {
        return api_bull;
    }

    public void setApi_bull(int api_bull) {
        this.api_bull = api_bull;
    }

    public int[] getApi_onslot() {
        return api_onslot;
    }

    public void setApi_onslot(int[] api_onslot) {
        this.api_onslot = api_onslot;
    }
}
