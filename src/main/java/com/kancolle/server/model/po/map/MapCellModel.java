package com.kancolle.server.model.po.map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.dao.annotation.Column;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("MapCellModel")
@JsonPropertyOrder(value = {
        "api_map_no", "api_maparea_id", "api_mapinfo_no", "api_id",
        "api_no", "api_color_no"
})
public class MapCellModel implements Serializable {

    @JsonProperty(value = "api_map_no")
    private int api_map_no;

    @JsonProperty(value = "api_maparea_id")
    private int api_maparea_id;

    @JsonProperty(value = "api_mapinfo_no")
    private int api_mapinfo_no;

    @JsonProperty(value = "api_id")
    private int api_id;

    @JsonProperty(value = "api_no")
    private int api_no;

    @JsonProperty(value = "api_color_no")
    private int api_color_no;

    public int getApi_color_no() {
        return api_color_no;
    }

    public int getApi_id() {
        return api_id;
    }

    public int getApi_map_no() {
        return api_map_no;
    }

    public int getApi_maparea_id() {
        return api_maparea_id;
    }

    public int getApi_mapinfo_no() {
        return api_mapinfo_no;
    }

    public int getApi_no() {
        return api_no;
    }

    @Column(name = "COLOR_NO", type = int.class)
    public void setApi_color_no(int api_color_no) {
        this.api_color_no = api_color_no;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Column(name = "MAP_NO", type = int.class)
    public void setApi_map_no(int api_map_no) {
        this.api_map_no = api_map_no;
    }

    @Column(name = "MAPAREA_ID", type = int.class)
    public void setApi_maparea_id(int api_maparea_id) {
        this.api_maparea_id = api_maparea_id;
    }

    @Column(name = "MAPINFO_NO", type = int.class)
    public void setApi_mapinfo_no(int api_mapinfo_no) {
        this.api_mapinfo_no = api_mapinfo_no;
    }

    @Column(name = "NO", type = int.class)
    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapCellModel that = (MapCellModel) o;

        return api_id == that.api_id;

    }

    @Override
    public int hashCode() {
        return api_id;
    }
}
