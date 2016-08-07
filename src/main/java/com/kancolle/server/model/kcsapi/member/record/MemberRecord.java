package com.kancolle.server.model.kcsapi.member.record;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


@JsonPropertyOrder(value = {
        "api_member_id", "api_nickname", "api_nickname_id", "api_cmt",
        "api_cmt_id", "api_photo_url", "api_level", "api_rank",
        "api_experience", "api_war", "api_mission", "api_practice",
        "api_friend", "api_deck", "api_kdoc", "api_ndoc",
        "api_ship", "api_slotitem", "api_furniture", "api_complate",
        "api_large_dock", "api_material_max"
})
public class MemberRecord {

    @JsonProperty(value = "api_member_id")
    @JSONField(ordinal = 1)
    private long api_member_id;

    @JsonProperty(value = "api_nickname")
    @JSONField(ordinal = 2)
    private String api_nickname;

    @JsonProperty(value = "api_nickname_id")
    @JSONField(ordinal = 3)
    private String api_nickname_id;

    @JsonProperty(value = "api_cmt")
    @JSONField(ordinal = 4)
    private String api_cmt;

    @JsonProperty(value = "api_cmt_id")
    @JSONField(ordinal = 5)
    private String api_cmt_id;

    @JsonProperty(value = "api_photo_url")
    @JSONField(ordinal = 6)
    private String api_photo_url;

    @JsonProperty(value = "api_level")
    @JSONField(ordinal = 7)
    private int api_level;

    @JsonProperty(value = "api_rank")
    @JSONField(ordinal = 8)
    private int api_rank;

    @JsonProperty(value = "api_experience")
    @JSONField(ordinal = 9)
    private List<Long> api_experience;

    @JsonProperty(value = "api_war")
    @JSONField(ordinal = 10)
    private MemberRecordFight api_war;

    @JsonProperty(value = "api_mission")
    @JSONField(ordinal = 11)
    private MemberRecordMission api_mission;

    @JsonProperty(value = "api_practice")
    @JSONField(ordinal = 12)
    private MemberRecordPractise api_practice;

    @JsonProperty(value = "api_friend")
    @JSONField(ordinal = 13)
    private int api_friend;

    @JsonProperty(value = "api_deck")
    @JSONField(ordinal = 14)
    private int api_deck;

    @JsonProperty(value = "api_kdoc")
    @JSONField(ordinal = 15)
    private int api_kdoc;

    @JsonProperty(value = "api_ndoc")
    @JSONField(ordinal = 16)
    private int api_ndoc;

    @JsonProperty(value = "api_ship")
    @JSONField(ordinal = 17)
    private List<Integer> api_ship;

    @JsonProperty(value = "api_slotitem")
    @JSONField(ordinal = 18)
    private List<Integer> api_slotitem;

    @JsonProperty(value = "api_furniture")
    @JSONField(ordinal = 19)
    private int api_furniture;

    @JsonProperty(value = "api_complate")
    @JSONField(ordinal = 20)
    private List<String> api_complate;

    @JsonProperty(value = "api_large_dock")
    @JSONField(ordinal = 21)
    private int api_large_dock;

    @JsonProperty(value = "api_material_max")
    @JSONField(ordinal = 22)
    private int api_material_max;

    public MemberRecord() {
        this.api_photo_url = StringUtils.EMPTY;
        this.api_complate = Lists.newArrayList("0.0", "0.0");
    }

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

    public List<Long> getApi_experience() {
        return api_experience;
    }

    public void setApi_experience(List<Long> api_experience) {
        this.api_experience = api_experience;
    }

    public MemberRecordFight getApi_war() {
        return api_war;
    }

    public void setApi_war(MemberRecordFight api_war) {
        this.api_war = api_war;
    }

    public MemberRecordMission getApi_mission() {
        return api_mission;
    }

    public void setApi_mission(MemberRecordMission api_mission) {
        this.api_mission = api_mission;
    }

    public MemberRecordPractise getApi_practice() {
        return api_practice;
    }

    public void setApi_practice(MemberRecordPractise api_practice) {
        this.api_practice = api_practice;
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

    public List<Integer> getApi_ship() {
        return api_ship;
    }

    public void setApi_ship(List<Integer> api_ship) {
        this.api_ship = api_ship;
    }

    public List<Integer> getApi_slotitem() {
        return api_slotitem;
    }

    public void setApi_slotitem(List<Integer> api_slotitem) {
        this.api_slotitem = api_slotitem;
    }

    public int getApi_furniture() {
        return api_furniture;
    }

    public void setApi_furniture(int api_furniture) {
        this.api_furniture = api_furniture;
    }

    public List<String> getApi_complate() {
        return api_complate;
    }

    public void setApi_complate(List<String> api_complate) {
        this.api_complate = api_complate;
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
