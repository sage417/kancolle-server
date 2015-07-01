package com.kancolle.server.model.kcsapi.member;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.member.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;

public class MemberPort {

    @JSONField(ordinal = 1)
    private List<MemberMeterialDto> api_material;

    @JSONField(ordinal = 2)
    private List<MemberDeckPort> api_deck_port;

    @JSONField(ordinal = 3)
    private List<MemberNdock> api_ndock;

    @JSONField(ordinal = 4)
    private List<MemberShip> api_ship;

    @JSONField(ordinal = 5)
    private MemberBasic api_basic;

    @JSONField(ordinal = 6)
    private List<MemberLog> api_log;

    @JSONField(ordinal = 7)
    private int api_p_bgm_id;

    @JSONField(ordinal = 8)
    private int api_parallel_quest_count;

    public MemberBasic getApi_basic() {
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

    public void setApi_basic(MemberBasic api_basic) {
        this.api_basic = api_basic;
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
        this.api_p_bgm_id = api_p_bgm_id;
    }

    public void setApi_parallel_quest_count(int api_parallel_quest_count) {
        this.api_parallel_quest_count = api_parallel_quest_count;
    }

    public void setApi_ship(List<MemberShip> api_ship) {
        this.api_ship = api_ship;
    }
}
