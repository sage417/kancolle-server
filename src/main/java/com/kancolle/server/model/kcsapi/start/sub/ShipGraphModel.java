package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("ShipGraphModel")
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
    private ImmutableList<String> api_version;

    @JsonProperty(value = "api_boko_n")
    @JSONField(ordinal = 5)
    private ImmutableList<Integer> api_boko_n;

    @JsonProperty(value = "api_boko_d")
    @JSONField(ordinal = 6)
    private ImmutableList<Integer> api_boko_d;

    @JsonProperty(value = "api_kaisyu_n")
    @JSONField(ordinal = 7)
    private ImmutableList<Integer> api_kaisyu_n;

    @JsonProperty(value = "api_kaisyu_d")
    @JSONField(ordinal = 8)
    private ImmutableList<Integer> api_kaisyu_d;

    @JsonProperty(value = "api_kaizo_n")
    @JSONField(ordinal = 9)
    private ImmutableList<Integer> api_kaizo_n;

    @JsonProperty(value = "api_kaizo_d")
    @JSONField(ordinal = 10)
    private ImmutableList<Integer> api_kaizo_d;

    @JsonProperty(value = "api_map_n")
    @JSONField(ordinal = 11)
    private ImmutableList<Integer> api_map_n;

    @JsonProperty(value = "api_map_d")
    @JSONField(ordinal = 12)
    private ImmutableList<Integer> api_map_d;

    @JsonProperty(value = "api_ensyuf_n")
    @JSONField(ordinal = 13)
    private ImmutableList<Integer> api_ensyuf_n;

    @JsonProperty(value = "api_ensyuf_d")
    @JSONField(ordinal = 14)
    private ImmutableList<Integer> api_ensyuf_d;

    @JsonProperty(value = "api_ensyue_n")
    @JSONField(ordinal = 15)
    private ImmutableList<Integer> api_ensyue_n;

    @JsonProperty(value = "api_battle_n")
    @JSONField(ordinal = 16)
    private ImmutableList<Integer> api_battle_n;

    @JsonProperty(value = "api_battle_d")
    @JSONField(ordinal = 17)
    private ImmutableList<Integer> api_battle_d;

    @JsonProperty(value = "api_weda")
    @JSONField(ordinal = 18)
    private ImmutableList<Integer> api_weda;

    @JsonProperty(value = "api_wedb")
    @JSONField(ordinal = 19)
    private ImmutableList<Integer> api_wedb;

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_sortno() {
        return api_sortno;
    }

    public void setApi_sortno(int api_sortno) {
        this.api_sortno = api_sortno;
    }

    public String getApi_filename() {
        return api_filename;
    }

    public void setApi_filename(String api_filename) {
        this.api_filename = api_filename;
    }

    public ImmutableList<String> getApi_version() {
        return api_version;
    }

    public void setApi_version(ImmutableList<String> api_version) {
        this.api_version = api_version;
    }

    public ImmutableList<Integer> getApi_boko_n() {
        return api_boko_n;
    }

    public void setApi_boko_n(ImmutableList<Integer> api_boko_n) {
        this.api_boko_n = api_boko_n;
    }

    public ImmutableList<Integer> getApi_boko_d() {
        return api_boko_d;
    }

    public void setApi_boko_d(ImmutableList<Integer> api_boko_d) {
        this.api_boko_d = api_boko_d;
    }

    public ImmutableList<Integer> getApi_kaisyu_n() {
        return api_kaisyu_n;
    }

    public void setApi_kaisyu_n(ImmutableList<Integer> api_kaisyu_n) {
        this.api_kaisyu_n = api_kaisyu_n;
    }

    public ImmutableList<Integer> getApi_kaisyu_d() {
        return api_kaisyu_d;
    }

    public void setApi_kaisyu_d(ImmutableList<Integer> api_kaisyu_d) {
        this.api_kaisyu_d = api_kaisyu_d;
    }

    public ImmutableList<Integer> getApi_kaizo_n() {
        return api_kaizo_n;
    }

    public void setApi_kaizo_n(ImmutableList<Integer> api_kaizo_n) {
        this.api_kaizo_n = api_kaizo_n;
    }

    public ImmutableList<Integer> getApi_kaizo_d() {
        return api_kaizo_d;
    }

    public void setApi_kaizo_d(ImmutableList<Integer> api_kaizo_d) {
        this.api_kaizo_d = api_kaizo_d;
    }

    public ImmutableList<Integer> getApi_map_n() {
        return api_map_n;
    }

    public void setApi_map_n(ImmutableList<Integer> api_map_n) {
        this.api_map_n = api_map_n;
    }

    public ImmutableList<Integer> getApi_map_d() {
        return api_map_d;
    }

    public void setApi_map_d(ImmutableList<Integer> api_map_d) {
        this.api_map_d = api_map_d;
    }

    public ImmutableList<Integer> getApi_ensyuf_n() {
        return api_ensyuf_n;
    }

    public void setApi_ensyuf_n(ImmutableList<Integer> api_ensyuf_n) {
        this.api_ensyuf_n = api_ensyuf_n;
    }

    public ImmutableList<Integer> getApi_ensyuf_d() {
        return api_ensyuf_d;
    }

    public void setApi_ensyuf_d(ImmutableList<Integer> api_ensyuf_d) {
        this.api_ensyuf_d = api_ensyuf_d;
    }

    public ImmutableList<Integer> getApi_ensyue_n() {
        return api_ensyue_n;
    }

    public void setApi_ensyue_n(ImmutableList<Integer> api_ensyue_n) {
        this.api_ensyue_n = api_ensyue_n;
    }

    public ImmutableList<Integer> getApi_battle_n() {
        return api_battle_n;
    }

    public void setApi_battle_n(ImmutableList<Integer> api_battle_n) {
        this.api_battle_n = api_battle_n;
    }

    public ImmutableList<Integer> getApi_battle_d() {
        return api_battle_d;
    }

    public void setApi_battle_d(ImmutableList<Integer> api_battle_d) {
        this.api_battle_d = api_battle_d;
    }

    public ImmutableList<Integer> getApi_weda() {
        return api_weda;
    }

    public void setApi_weda(ImmutableList<Integer> api_weda) {
        this.api_weda = api_weda;
    }

    public ImmutableList<Integer> getApi_wedb() {
        return api_wedb;
    }

    public void setApi_wedb(ImmutableList<Integer> api_wedb) {
        this.api_wedb = api_wedb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShipGraphModel that = (ShipGraphModel) o;

        return api_id == that.api_id;

    }

    @Override
    public int hashCode() {
        return api_id;
    }
}
