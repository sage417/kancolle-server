package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.kcsapi.member.port.PortMember;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.model.po.member.MemberNdock;
import com.kancolle.server.model.po.ship.MemberShip;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder(value = {
        "api_material", "api_deck_port", "api_ndock", "api_ship",
        "api_basic", "api_log", "api_p_bgm_id", "api_parallel_quest_count"
})
public class MemberPort implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8195617907701218535L;

    @JsonProperty(value = "api_material")
    @JSONField(ordinal = 1)
    private List<MemberMeterialDto> api_material;

    @JsonProperty(value = "api_deck_port")
    @JSONField(ordinal = 2)
    private List<MemberDeckPort> api_deck_port;

    @JsonProperty(value = "api_ndock")
    @JSONField(ordinal = 3)
    private List<MemberNdock> api_ndock;

    @JsonProperty(value = "api_ship")
    @JSONField(ordinal = 4)
    private List<MemberShip> api_ship;

    @JsonProperty(value = "api_basic")
    @JSONField(ordinal = 5)
    private PortMember api_basic;

    @JsonProperty(value = "api_log")
    @JSONField(ordinal = 6)
    private List<MemberLog> api_log;

    @JsonProperty(value = "api_p_bgm_id")
    @JSONField(ordinal = 7)
    private int api_p_bgm_id;

    @JsonProperty(value = "api_parallel_quest_count")
    @JSONField(ordinal = 8)
    private int api_parallel_quest_count;

    public PortMember getApi_basic() {
        return api_basic;
    }

    public List<MemberDeckPort> getApi_deck_port() {
        return api_deck_port;
    }

    public List<MemberLog> getApi_log() {
        return api_log;
    }

    public List<MemberMeterialDto> getApi_material() {
        return api_material;
    }

    public List<MemberNdock> getApi_ndock() {
        return api_ndock;
    }

    public int getApi_p_bgm_id() {
        return api_p_bgm_id;
    }

    public int getApi_parallel_quest_count() {
        return api_parallel_quest_count;
    }

    public List<MemberShip> getApi_ship() {
        return api_ship;
    }

    public void setApi_basic(Member api_basic) {
        this.api_basic = new PortMember();
        BeanUtils.copyProperties(api_basic, this.api_basic);
        this.api_basic.setApi_large_dock(api_basic.isLargeDock() ? 1 : 0);
        this.api_p_bgm_id = api_basic.getPortBGMId();
        this.api_parallel_quest_count = api_basic.getParallelQuestCount();
    }

    public void setApi_deck_port(List<MemberDeckPort> api_deck_port) {
        this.api_deck_port = api_deck_port;
    }

    public void setApi_log(List<MemberLog> api_log) {
        this.api_log = api_log;
    }

    public void setApi_material(List<MemberMeterialDto> api_material) {
        this.api_material = api_material;
    }

    public void setApi_ndock(List<MemberNdock> api_ndock) {
        this.api_ndock = api_ndock;
    }

    public void setApi_p_bgm_id(int api_p_bgm_id) {
        throw new UnsupportedOperationException();
    }

    public void setApi_parallel_quest_count(int api_parallel_quest_count) {
        throw new UnsupportedOperationException();
    }

    public void setApi_ship(List<MemberShip> api_ship) {
        this.api_ship = api_ship;
    }
}
