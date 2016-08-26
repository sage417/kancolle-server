package com.kancolle.server.model.kcsapi.start.sub;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("MapBgmModel")
@JsonPropertyOrder(value = {
        "api_id", "api_maparea_id", "api_no", "api_map_bgm",
        "api_boss_bgm"
})
public class MapBgmModel implements Serializable {

    @JsonProperty(value = "api_id")
    private int api_id;

    @JsonProperty(value = "api_maparea_id")
    private int api_maparea_id;

    @JsonProperty(value = "api_no")
    private int api_no;

    @JsonProperty(value = "api_map_bgm")
    private ImmutableList<Integer> api_map_bgm;

    @JsonProperty(value = "api_boss_bgm")
    private ImmutableList<Integer> api_boss_bgm;

    public ImmutableList<Integer> getApi_boss_bgm() {
        return api_boss_bgm;
    }

    public int getApi_id() {
        return api_id;
    }

    public ImmutableList<Integer> getApi_map_bgm() {
        return api_map_bgm;
    }

    public int getApi_maparea_id() {
        return api_maparea_id;
    }

    public int getApi_no() {
        return api_no;
    }

    public void setApi_boss_bgm(ImmutableList<Integer> api_boss_bgm) {
        this.api_boss_bgm = api_boss_bgm;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public void setApi_map_bgm(ImmutableList<Integer> api_map_bgm) {
        this.api_map_bgm = api_map_bgm;
    }

    public void setApi_maparea_id(int api_maparea_id) {
        this.api_maparea_id = api_maparea_id;
    }

    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }
}
