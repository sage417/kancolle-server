package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.dao.annotation.Column;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "api_id", "api_sortno", "api_filename", "api_version",
        "api_boko_n", "api_boko_d", "api_kaisyu_n", "api_kaisyu_d",
        "api_kaizo_n", "api_kaizo_d", "api_map_n", "api_map_d",
        "api_ensyuf_n", "api_ensyuf_d", "api_ensyue_n", "api_battle_n",
        "api_battle_d", "api_weda", "api_wedb"
})
public class ShipGraphModel implements Serializable {

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1)
    private int api_id;

    @JsonProperty(value = "api_sortno")
    @JSONField(ordinal = 2)
    private int api_sortno;

    @JsonProperty(value = "api_filename")
    @JSONField(ordinal = 3)
    private String api_filename;

    @JsonProperty(value = "api_version")
    @JSONField(ordinal = 4)
    private JSONArray api_version;

    @JsonProperty(value = "api_boko_n")
    @JSONField(ordinal = 5)
    private JSONArray api_boko_n;

    @JsonProperty(value = "api_boko_d")
    @JSONField(ordinal = 6)
    private JSONArray api_boko_d;

    @JsonProperty(value = "api_kaisyu_n")
    @JSONField(ordinal = 7)
    private JSONArray api_kaisyu_n;

    @JsonProperty(value = "api_kaisyu_d")
    @JSONField(ordinal = 8)
    private JSONArray api_kaisyu_d;

    @JsonProperty(value = "api_kaizo_n")
    @JSONField(ordinal = 9)
    private JSONArray api_kaizo_n;

    @JsonProperty(value = "api_kaizo_d")
    @JSONField(ordinal = 10)
    private JSONArray api_kaizo_d;

    @JsonProperty(value = "api_map_n")
    @JSONField(ordinal = 11)
    private JSONArray api_map_n;

    @JsonProperty(value = "api_map_d")
    @JSONField(ordinal = 12)
    private JSONArray api_map_d;

    @JsonProperty(value = "api_ensyuf_n")
    @JSONField(ordinal = 13)
    private JSONArray api_ensyuf_n;

    @JsonProperty(value = "api_ensyuf_d")
    @JSONField(ordinal = 14)
    private JSONArray api_ensyuf_d;

    @JsonProperty(value = "api_ensyue_n")
    @JSONField(ordinal = 15)
    private JSONArray api_ensyue_n;

    @JsonProperty(value = "api_battle_n")
    @JSONField(ordinal = 16)
    private JSONArray api_battle_n;

    @JsonProperty(value = "api_battle_d")
    @JSONField(ordinal = 17)
    private JSONArray api_battle_d;

    @JsonProperty(value = "api_weda")
    @JSONField(ordinal = 18)
    private JSONArray api_weda;

    @JsonProperty(value = "api_wedb")
    @JSONField(ordinal = 19)
    private JSONArray api_wedb;

    public JSONArray getApi_battle_d() {
        return api_battle_d;
    }

    public JSONArray getApi_battle_n() {
        return api_battle_n;
    }

    public JSONArray getApi_boko_d() {
        return api_boko_d;
    }

    public JSONArray getApi_boko_n() {
        return api_boko_n;
    }

    public JSONArray getApi_ensyue_n() {
        return api_ensyue_n;
    }

    public JSONArray getApi_ensyuf_d() {
        return api_ensyuf_d;
    }

    public JSONArray getApi_ensyuf_n() {
        return api_ensyuf_n;
    }

    public String getApi_filename() {
        return api_filename;
    }

    public int getApi_id() {
        return api_id;
    }

    public JSONArray getApi_kaisyu_d() {
        return api_kaisyu_d;
    }

    public JSONArray getApi_kaisyu_n() {
        return api_kaisyu_n;
    }

    public JSONArray getApi_kaizo_d() {
        return api_kaizo_d;
    }

    public JSONArray getApi_kaizo_n() {
        return api_kaizo_n;
    }

    public JSONArray getApi_map_d() {
        return api_map_d;
    }

    public JSONArray getApi_map_n() {
        return api_map_n;
    }

    public int getApi_sortno() {
        return api_sortno;
    }

    public JSONArray getApi_version() {
        return api_version;
    }

    public JSONArray getApi_weda() {
        return api_weda;
    }

    public JSONArray getApi_wedb() {
        return api_wedb;
    }

    @Column(name = "BATTLE_D", type = String.class)
    public void setApi_battle_d(String api_battle_d) {
        this.api_battle_d = JSON.parseArray(api_battle_d);
    }

    @Column(name = "BATTLE_N", type = String.class)
    public void setApi_battle_n(String api_battle_n) {
        this.api_battle_n = JSON.parseArray(api_battle_n);
    }

    @Column(name = "BOKO_D", type = String.class)
    public void setApi_boko_d(String api_boko_d) {
        this.api_boko_d = JSON.parseArray(api_boko_d);
    }

    @Column(name = "BOKO_N", type = String.class)
    public void setApi_boko_n(String api_boko_n) {
        this.api_boko_n = JSON.parseArray(api_boko_n);
    }

    @Column(name = "ENSYUE_N", type = String.class)
    public void setApi_ensyue_n(String api_ensyue_n) {
        this.api_ensyue_n = JSON.parseArray(api_ensyue_n);
    }

    @Column(name = "ENSYUF_D", type = String.class)
    public void setApi_ensyuf_d(String api_ensyuf_d) {
        this.api_ensyuf_d = JSON.parseArray(api_ensyuf_d);
    }

    @Column(name = "ENSYUF_N", type = String.class)
    public void setApi_ensyuf_n(String api_ensyuf_n) {
        this.api_ensyuf_n = JSON.parseArray(api_ensyuf_n);
    }

    @Column(name = "FILENAME", type = String.class)
    public void setApi_filename(String api_filename) {
        this.api_filename = api_filename;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Column(name = "KAISYU_D", type = String.class)
    public void setApi_kaisyu_d(String api_kaisyu_d) {
        this.api_kaisyu_d = JSON.parseArray(api_kaisyu_d);
    }

    @Column(name = "KAISYU_N", type = String.class)
    public void setApi_kaisyu_n(String api_kaisyu_n) {
        this.api_kaisyu_n = JSON.parseArray(api_kaisyu_n);
    }

    @Column(name = "KAIZO_D", type = String.class)
    public void setApi_kaizo_d(String api_kaizo_d) {
        this.api_kaizo_d = JSON.parseArray(api_kaizo_d);
    }

    @Column(name = "KAIZO_N", type = String.class)
    public void setApi_kaizo_n(String api_kaizo_n) {
        this.api_kaizo_n = JSON.parseArray(api_kaizo_n);
    }

    @Column(name = "MAP_D", type = String.class)
    public void setApi_map_d(String api_map_d) {
        this.api_map_d = JSON.parseArray(api_map_d);
    }

    @Column(name = "MAP_N", type = String.class)
    public void setApi_map_n(String api_map_n) {
        this.api_map_n = JSON.parseArray(api_map_n);
    }

    @Column(name = "SORTNO", type = int.class)
    public void setApi_sortno(int api_sortno) {
        this.api_sortno = api_sortno;
    }

    @Column(name = "VERSION", type = String.class)
    public void setApi_version(String api_version) {
        this.api_version = JSON.parseArray(api_version);
    }

    @Column(name = "WEDA", type = String.class)
    public void setApi_weda(String api_weda) {
        this.api_weda = JSON.parseArray(api_weda);
    }

    @Column(name = "WEDB", type = String.class)
    public void setApi_wedb(String api_wedb) {
        this.api_wedb = JSON.parseArray(api_wedb);
    }
}
