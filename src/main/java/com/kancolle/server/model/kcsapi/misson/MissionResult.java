package com.kancolle.server.model.kcsapi.misson;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.po.mission.Mission;

@JsonPropertyOrder(value = {
        "api_ship_id", "api_clear_result", "api_get_exp", "api_member_lv",
        "api_member_exp", "api_get_ship_exp", "api_get_exp_lvup", "api_maparea_name",
        "api_detail", "api_quest_name", "api_quest_level", "api_get_material",
        "api_useitem_flag", "api_get_item1", "api_get_item2",
})
public class MissionResult {

    public static final int RESULT_FAILED = 0;

    public static final int RESULT_SUCCESS = 1;

    public static final int RESULT_GREAT_SUCCESS = 2;

    @JsonProperty(value = "api_ship_id")
    @JSONField(ordinal = 1)
    private long[] api_ship_id;

    @JsonProperty(value = "api_clear_result")
    @JSONField(ordinal = 2)
    private int api_clear_result;

    @JsonProperty(value = "api_get_exp")
    @JSONField(ordinal = 3)
    private int api_get_exp;

    @JsonProperty(value = "api_member_lv")
    @JSONField(ordinal = 4)
    private int api_member_lv;

    @JsonProperty(value = "api_member_exp")
    @JSONField(ordinal = 5)
    private long api_member_exp;

    @JsonProperty(value = "api_get_ship_exp")
    @JSONField(ordinal = 6)
    private int[] api_get_ship_exp;

    @JsonProperty(value = "api_get_exp_lvup")
    @JSONField(ordinal = 7)
    private long[][] api_get_exp_lvup;

    @JsonProperty(value = "api_maparea_name")
    @JSONField(ordinal = 8)
    private String api_maparea_name;

    @JsonProperty(value = "api_detail")
    @JSONField(ordinal = 9)
    private String api_detail;

    @JsonProperty(value = "api_quest_name")
    @JSONField(ordinal = 10)
    private String api_quest_name;

    @JsonProperty(value = "api_quest_level")
    @JSONField(ordinal = 11)
    private int api_quest_level;

    @JsonProperty(value = "api_get_material")
    @JSONField(ordinal = 12)
    private int[] api_get_material;

    @JsonProperty(value = "api_useitem_flag")
    @JSONField(ordinal = 13)
    private int[] api_useitem_flag;

    @JsonProperty(value = "api_get_item1")
    @JSONField(ordinal = 14)
    private GetUseItem api_get_item1;

    @JsonProperty(value = "api_get_item2")
    @JSONField(ordinal = 15)
    private GetUseItem api_get_item2;

    public MissionResult(Mission mission) {
        this.api_quest_name = mission.getName();
        this.api_quest_level = mission.getDifficulty();
        this.api_detail = mission.getDetails();
        this.api_maparea_name = mission.getMaparea().getName();
    }

    public int getApi_clear_result() {
        return api_clear_result;
    }

    public String getApi_detail() {
        return api_detail;
    }

    public int getApi_get_exp() {
        return api_get_exp;
    }

    public long[][] getApi_get_exp_lvup() {
        return api_get_exp_lvup;
    }

    public int[] getApi_get_material() {
        return api_get_material;
    }

    public int[] getApi_get_ship_exp() {
        return api_get_ship_exp;
    }

    public String getApi_maparea_name() {
        return api_maparea_name;
    }

    public long getApi_member_exp() {
        return api_member_exp;
    }

    public int getApi_member_lv() {
        return api_member_lv;
    }

    public int getApi_quest_level() {
        return api_quest_level;
    }

    public String getApi_quest_name() {
        return api_quest_name;
    }

    public long[] getApi_ship_id() {
        return api_ship_id;
    }

    public int[] getApi_useitem_flag() {
        return api_useitem_flag;
    }

    public void setApi_clear_result(int api_clear_result) {
        this.api_clear_result = api_clear_result;
    }

    public void setApi_detail(String api_detail) {
        this.api_detail = api_detail;
    }

    public void setApi_get_exp(int api_get_exp) {
        this.api_get_exp = api_get_exp;
    }

    public void setApi_get_exp_lvup(long[][] api_get_exp_lvup) {
        this.api_get_exp_lvup = api_get_exp_lvup;
    }

    public void setApi_get_material(int[] api_get_material) {
        this.api_get_material = api_get_material;
    }

    public void setApi_get_ship_exp(int[] api_get_ship_exp) {
        this.api_get_ship_exp = api_get_ship_exp;
    }

    public void setApi_maparea_name(String api_maparea_name) {
        this.api_maparea_name = api_maparea_name;
    }

    public void setApi_member_exp(long api_member_exp) {
        this.api_member_exp = api_member_exp;
    }

    public void setApi_member_lv(int api_member_lv) {
        this.api_member_lv = api_member_lv;
    }

    public void setApi_quest_level(int api_quest_level) {
        this.api_quest_level = api_quest_level;
    }

    public void setApi_quest_name(String api_quest_name) {
        this.api_quest_name = api_quest_name;
    }

    public void setApi_ship_id(long[] api_ship_id) {
        this.api_ship_id = api_ship_id;
    }

    public void setApi_useitem_flag(int[] api_useitem_flag) {
        this.api_useitem_flag = api_useitem_flag;
    }

    public GetUseItem getApi_get_item1() {
        return api_get_item1;
    }

    public void setApi_get_item1(GetUseItem api_get_item1) {
        this.api_get_item1 = api_get_item1;
    }

    public GetUseItem getApi_get_item2() {
        return api_get_item2;
    }

    public void setApi_get_item2(GetUseItem api_get_item2) {
        this.api_get_item2 = api_get_item2;
    }
}
