package com.kancolle.server.model.kcsapi.kcock;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.po.member.MemberKdock;
import com.kancolle.server.model.po.ship.MemberShip;

import java.util.List;

@JsonPropertyOrder(value = {
        "api_id", "api_ship_id", "api_kdock", "api_ship",
        "api_slotitem"
})
public class GetShipResult {

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1)
    private long api_id;

    @JsonProperty(value = "api_ship_id")
    @JSONField(ordinal = 2)
    private int api_ship_id;

    @JsonProperty(value = "api_kdock")
    @JSONField(ordinal = 3)
    private List<MemberKdock> api_kdock;

    @JsonProperty(value = "api_ship")
    @JSONField(ordinal = 4)
    private MemberShip api_ship;

    @JsonProperty(value = "api_slotitem")
    @JSONField(ordinal = 5)
    private List<GetShipSlotItem> api_slotitem;

    public GetShipResult(MemberShip memberShip, List<MemberKdock> kdocks) {
        this.api_id = memberShip.getMemberShipId();
        this.api_ship_id = memberShip.getShip().getShipId();
        this.api_ship = memberShip;
        this.api_kdock = kdocks;
        // TODO
    }

    public long getApi_id() {
        return api_id;
    }

    public void setApi_id(long api_id) {
        this.api_id = api_id;
    }

    public int getApi_ship_id() {
        return api_ship_id;
    }

    public void setApi_ship_id(int api_ship_id) {
        this.api_ship_id = api_ship_id;
    }

    public List<MemberKdock> getApi_kdock() {
        return api_kdock;
    }

    public void setApi_kdock(List<MemberKdock> api_kdock) {
        this.api_kdock = api_kdock;
    }

    public MemberShip getApi_ship() {
        return api_ship;
    }

    public void setApi_ship(MemberShip api_ship) {
        this.api_ship = api_ship;
    }

    public List<GetShipSlotItem> getApi_slotitem() {
        return api_slotitem;
    }

    public void setApi_slotitem(List<GetShipSlotItem> api_slotitem) {
        this.api_slotitem = api_slotitem;
    }
}
