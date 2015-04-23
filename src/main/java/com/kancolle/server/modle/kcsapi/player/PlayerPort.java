package com.kancolle.server.modle.kcsapi.player;

import java.util.List;

public class PlayerPort {

    private List<PlayerMeterial> api_material;

    private List<PlayerDeckPort> api_deck_port;

    private List<PlayerNDock> api_ndock;

    private List<PlayerShip> api_ship;

    private PlayerBasic api_basic;

    private List<PlayerLog> api_log;

    private int api_p_bgm_id;

    private int api_parallel_quest_count;

    public List<PlayerMeterial> getApi_material() {
        return api_material;
    }

    public void setApi_material(List<PlayerMeterial> api_material) {
        this.api_material = api_material;
    }

    public List<PlayerDeckPort> getApi_deck_port() {
        return api_deck_port;
    }

    public void setApi_deck_port(List<PlayerDeckPort> api_deck_port) {
        this.api_deck_port = api_deck_port;
    }

    public List<PlayerNDock> getApi_ndock() {
        return api_ndock;
    }

    public void setApi_ndock(List<PlayerNDock> api_ndock) {
        this.api_ndock = api_ndock;
    }

    public List<PlayerShip> getApi_ship() {
        return api_ship;
    }

    public void setApi_ship(List<PlayerShip> api_ship) {
        this.api_ship = api_ship;
    }

    public PlayerBasic getApi_basic() {
        return api_basic;
    }

    public void setApi_basic(PlayerBasic api_basic) {
        this.api_basic = api_basic;
    }

    public List<PlayerLog> getApi_log() {
        return api_log;
    }

    public void setApi_log(List<PlayerLog> api_log) {
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
    private static class PlayerMeterial {
        public int api_member_id;
        public int api_id;
        public long api_value;
    }

    @SuppressWarnings("unused")
    private static class PlayerDeckPort {
        public int api_member_id;
        public int api_id;
        public String api_name;
        public String api_name_id;
        public int[] api_mission;
        public String api_flagship;
        public int[] api_ship;
    }

    @SuppressWarnings("unused")
    private static class PlayerNDock {
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
    private static class PlayerLog {
        public int api_no;
        public String api_type;
        public String api_state;
        public String api_message;
    }
}
