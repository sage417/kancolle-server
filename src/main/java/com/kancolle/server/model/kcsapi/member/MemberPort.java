package com.kancolle.server.model.kcsapi.member;

import java.util.List;

public class MemberPort {

    private List<MemberMeterial> api_material;

    private List<MemberDeckPort> api_deck_port;

    private List<MemberNDock> api_ndock;

    private List<MemberShip> api_ship;

    private MemberBasic api_basic;

    private List<MemberLog> api_log;

    private int api_p_bgm_id;

    private int api_parallel_quest_count;

    public List<MemberMeterial> getApi_material() {
        return api_material;
    }

    public void setApi_material(List<MemberMeterial> api_material) {
        this.api_material = api_material;
    }

    public List<MemberDeckPort> getApi_deck_port() {
        return api_deck_port;
    }

    public void setApi_deck_port(List<MemberDeckPort> api_deck_port) {
        this.api_deck_port = api_deck_port;
    }

    public List<MemberNDock> getApi_ndock() {
        return api_ndock;
    }

    public void setApi_ndock(List<MemberNDock> api_ndock) {
        this.api_ndock = api_ndock;
    }

    public List<MemberShip> getApi_ship() {
        return api_ship;
    }

    public void setApi_ship(List<MemberShip> api_ship) {
        this.api_ship = api_ship;
    }

    public MemberBasic getApi_basic() {
        return api_basic;
    }

    public void setApi_basic(MemberBasic api_basic) {
        this.api_basic = api_basic;
    }

    public List<MemberLog> getApi_log() {
        return api_log;
    }

    public void setApi_log(List<MemberLog> api_log) {
        this.api_log = api_log;
    }

    public int getApi_p_bgm_id() {
        return api_p_bgm_id;
    }

    public void setApi_p_bgm_id(int api_p_bgm_id) {
        this.api_p_bgm_id = api_p_bgm_id;
    }

    public int getApi_parallel_quest_count() {
        return api_parallel_quest_count;
    }

    public void setApi_parallel_quest_count(int api_parallel_quest_count) {
        this.api_parallel_quest_count = api_parallel_quest_count;
    }

    @SuppressWarnings("unused")
    private static class MemberMeterial {
        public int api_member_id;
        public int api_id;
        public long api_value;
    }

    @SuppressWarnings("unused")
    private static class MemberDeckPort {
        public int api_member_id;
        public int api_id;
        public String api_name;
        public String api_name_id;
        public int[] api_mission;
        public String api_flagship;
        public int[] api_ship;
    }

    @SuppressWarnings("unused")
    private static class MemberNDock {
        public int api_member_id;
        public int api_id;
        public int api_state;
        public int api_ship_id;
        public int api_complete_time;
        public String api_complete_time_str;
        public int api_item1;
        public int api_item2;
        public int api_item3;
        public int api_item4;
    }

    @SuppressWarnings("unused")
    private static class MemberLog {
        public int api_no;
        public String api_type;
        public String api_state;
        public String api_message;
    }
}
