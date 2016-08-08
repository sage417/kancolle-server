package com.kancolle.server.model.kcsapi.start.requireInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.po.furniture.MemberFurniture;
import com.kancolle.server.model.po.member.MemberKdock;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.model.po.useitem.MemberUseItem;

import java.util.List;
import java.util.Map;

/**
 * Created by J.K.SAGE on 2016/8/8 0008.
 */
@JsonPropertyOrder(value = {
        "api_basic", "api_slot_item", "api_unsetslot", "api_kdock",
        "api_useitem", "api_furniture"
})
public class RequireInfo {

    @JsonProperty(value = "api_basic")
    private Basic api_basic;

    @JsonProperty(value = "api_slot_item")
    private List<MemberSlotItem> api_slot_item;

    @JsonProperty(value = "api_unsetslot")
    private Map<String, Object> api_unsetslot;

    @JsonProperty(value = "api_kdock")
    private List<MemberKdock> api_kdock;

    @JsonProperty(value = "api_useitem")
    private List<MemberUseItem> api_useitem;

    @JsonProperty(value = "api_furniture")
    private List<MemberFurniture> api_furniture;

    public Basic getApi_basic() {
        return api_basic;
    }

    public void setApi_basic(Basic api_basic) {
        this.api_basic = api_basic;
    }

    public List<MemberSlotItem> getApi_slot_item() {
        return api_slot_item;
    }

    public void setApi_slot_item(List<MemberSlotItem> api_slot_item) {
        this.api_slot_item = api_slot_item;
    }

    public Map<String, Object> getApi_unsetslot() {
        return api_unsetslot;
    }

    public void setApi_unsetslot(Map<String, Object> api_unsetslot) {
        this.api_unsetslot = api_unsetslot;
    }

    public List<MemberKdock> getApi_kdock() {
        return api_kdock;
    }

    public void setApi_kdock(List<MemberKdock> api_kdock) {
        this.api_kdock = api_kdock;
    }

    public List<MemberUseItem> getApi_useitem() {
        return api_useitem;
    }

    public void setApi_useitem(List<MemberUseItem> api_useitem) {
        this.api_useitem = api_useitem;
    }

    public List<MemberFurniture> getApi_furniture() {
        return api_furniture;
    }

    public void setApi_furniture(List<MemberFurniture> api_furniture) {
        this.api_furniture = api_furniture;
    }
}
