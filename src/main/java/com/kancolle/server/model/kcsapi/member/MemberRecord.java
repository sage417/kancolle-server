package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;

public class MemberRecord {

    @JSONField(ordinal = 1)
    private long api_member_id;

    @JSONField(ordinal = 2)
    private String api_nickname;

    @JSONField(ordinal = 3)
    private String api_nickname_id;

    @JSONField(ordinal = 4)
    private String api_cmt;

    @JSONField(ordinal = 5)
    private String api_cmt_id;

    @JSONField(ordinal = 6)
    private String api_photo_url;

    @JSONField(ordinal = 7)
    private int api_level;

    @JSONField(ordinal = 8)
    private int api_rank;

    @JSONField(ordinal = 9)
    private JSONArray api_experience;

    @JSONField(ordinal = 10)
    private JSONArray api_war;

    @JSONField(ordinal = 11)
    private JSONArray api_mission;

    @JSONField(ordinal = 12)
    private JSONArray api_practice;

    @JSONField(ordinal = 13)
    private int api_friend;

    @JSONField(ordinal = 14)
    private int api_deck;

    @JSONField(ordinal = 15)
    private int api_kdoc;

    @JSONField(ordinal = 16)
    private int api_ndoc;

    @JSONField(ordinal = 17)
    private JSONArray api_ship;

    @JSONField(ordinal = 18)
    private JSONArray api_slotitem;

    @JSONField(ordinal = 19)
    private int api_furniture;

    @JSONField(ordinal = 20)
    private JSONArray api_complate;

    @JSONField(ordinal = 21)
    private int api_large_dock;

    @JSONField(ordinal = 22)
    private int api_material_max;

    public long getApi_member_id() {
        return api_member_id;
    }

    public void setApi_member_id(long api_member_id) {
        this.api_member_id = api_member_id;
    }

    public String getApi_nickname() {
        return api_nickname;
    }

    public void setApi_nickname(String api_nickname) {
        this.api_nickname = api_nickname;
    }

    public String getApi_nickname_id() {
        return api_nickname_id;
    }

    public void setApi_nickname_id(String api_nickname_id) {
        this.api_nickname_id = api_nickname_id;
    }

    public String getApi_cmt() {
        return api_cmt;
    }

    public void setApi_cmt(String api_cmt) {
        this.api_cmt = api_cmt;
    }

    public String getApi_cmt_id() {
        return api_cmt_id;
    }

    public void setApi_cmt_id(String api_cmt_id) {
        this.api_cmt_id = api_cmt_id;
    }

    public String getApi_photo_url() {
        return api_photo_url;
    }

    public void setApi_photo_url(String api_photo_url) {
        this.api_photo_url = api_photo_url;
    }

    public int getApi_level() {
        return api_level;
    }

    public void setApi_level(int api_level) {
        this.api_level = api_level;
    }

    public int getApi_rank() {
        return api_rank;
    }

    public void setApi_rank(int api_rank) {
        this.api_rank = api_rank;
    }

    public JSONArray getApi_experience() {
        return api_experience;
    }

    public void setApi_experience(String api_experience) {
        this.api_experience = JSON.parseArray(api_experience);
    }

    public JSONArray getApi_war() {
        return api_war;
    }

    public void setApi_war(String api_war) {
        this.api_war = JSON.parseArray(api_war);
    }

    public JSONArray getApi_mission() {
        return api_mission;
    }

    public void setApi_mission(String api_mission) {
        this.api_mission = JSON.parseArray(api_mission);
    }

    public JSONArray getApi_practice() {
        return api_practice;
    }

    public void setApi_practice(String api_practice) {
        this.api_practice = JSON.parseArray(api_practice);
    }

    public int getApi_friend() {
        return api_friend;
    }

    public void setApi_friend(int api_friend) {
        this.api_friend = api_friend;
    }

    public int getApi_deck() {
        return api_deck;
    }

    public void setApi_deck(int api_deck) {
        this.api_deck = api_deck;
    }

    public int getApi_kdoc() {
        return api_kdoc;
    }

    public void setApi_kdoc(int api_kdoc) {
        this.api_kdoc = api_kdoc;
    }

    public int getApi_ndoc() {
        return api_ndoc;
    }

    public void setApi_ndoc(int api_ndoc) {
        this.api_ndoc = api_ndoc;
    }

    public JSONArray getApi_ship() {
        return api_ship;
    }

    public void setApi_ship(String api_ship) {
        this.api_ship = JSON.parseArray(api_ship);
    }

    public JSONArray getApi_slotitem() {
        return api_slotitem;
    }

    public void setApi_slotitem(String api_slotitem) {
        this.api_slotitem = JSON.parseArray(api_slotitem);
    }

    public int getApi_furniture() {
        return api_furniture;
    }

    public void setApi_furniture(int api_furniture) {
        this.api_furniture = api_furniture;
    }

    public JSONArray getApi_complate() {
        return api_complate;
    }

    public void setApi_complate(String api_complate) {
        this.api_complate = JSON.parseArray(api_complate);
    }

    public int getApi_large_dock() {
        return api_large_dock;
    }

    public void setApi_large_dock(int api_large_dock) {
        this.api_large_dock = api_large_dock;
    }

    public int getApi_material_max() {
        return api_material_max;
    }

    public void setApi_material_max(int api_material_max) {
        this.api_material_max = api_material_max;
    }
}
