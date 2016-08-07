package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.primitives.Ints;
import com.kancolle.server.dao.annotation.Column;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "api_id", "api_maparea_id", "api_no", "api_name",
        "api_level", "api_opetext", "api_infotext", "api_item",
        "api_max_maphp", "api_required_defeat_count", "api_sally_flag"
})
@JSONType(serialzeFeatures = SerializerFeature.WriteMapNullValue)
public class MapInfoModel implements Serializable {

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1)
    private int api_id;

    @JsonProperty(value = "api_maparea_id")
    @JSONField(ordinal = 2)
    private int api_maparea_id;

    @JsonProperty(value = "api_no")
    @JSONField(ordinal = 3)
    private int api_no;

    @JsonProperty(value = "api_name")
    @JSONField(ordinal = 4)
    private String api_name;

    @JsonProperty(value = "api_level")
    @JSONField(ordinal = 5)
    private int api_level;

    @JsonProperty(value = "api_opetext")
    @JSONField(ordinal = 6)
    private String api_opetext;

    @JsonProperty(value = "api_infotext")
    @JSONField(ordinal = 7)
    private String api_infotext;

    @JsonProperty(value = "api_item")
    @JSONField(ordinal = 8)
    private int[] api_item;

    @JsonProperty(value = "api_max_maphp")
    @JSONField(ordinal = 9, serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Integer api_max_maphp;

    @JsonProperty(value = "api_required_defeat_count")
    @JSONField(ordinal = 10, serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Integer api_required_defeat_count;

    @JsonProperty(value = "api_sally_flag")
    @JSONField(ordinal = 11)
    private JSONArray api_sally_flag;

    public int getApi_id() {
        return api_id;
    }

    public String getApi_infotext() {
        return api_infotext;
    }

    public int[] getApi_item() {
        return api_item;
    }

    public int getApi_level() {
        return api_level;
    }

    public int getApi_maparea_id() {
        return api_maparea_id;
    }

    public Integer getApi_max_maphp() {
        return api_max_maphp;
    }

    public String getApi_name() {
        return api_name;
    }

    public int getApi_no() {
        return api_no;
    }

    public String getApi_opetext() {
        return api_opetext;
    }

    public Integer getApi_required_defeat_count() {
        return api_required_defeat_count;
    }

    public JSONArray getApi_sally_flag() {
        return api_sally_flag;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Column(name = "INFOTEXT", type = String.class)
    public void setApi_infotext(String api_infotext) {
        this.api_infotext = api_infotext;
    }

    @Column(name = "ITEM", type = String.class)
    public void setApi_item(String api_item) {
        this.api_item = Ints.toArray(JSON.parseArray(api_item, Integer.class));
    }

    public void setApi_item(int[] api_item) {
        this.api_item = api_item;
    }

    @Column(name = "LEVEL", type = int.class)
    public void setApi_level(int api_level) {
        this.api_level = api_level;
    }

    @Column(name = "MAPAREA_ID", type = int.class)
    public void setApi_maparea_id(int api_maparea_id) {
        this.api_maparea_id = api_maparea_id;
    }

    @Column(name = "MAX_MAPHP", type = int.class)
    public void setApi_max_maphp(Integer api_max_maphp) {
        if (api_max_maphp > 0)
            this.api_max_maphp = api_max_maphp;
    }

    @Column(name = "NAME", type = String.class)
    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    @Column(name = "NO", type = int.class)
    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }

    @Column(name = "OPETEXT", type = String.class)
    public void setApi_opetext(String api_opetext) {
        this.api_opetext = api_opetext;
    }

    @Column(name = "REQUIRED_DEFEAT_COUNT", type = int.class)
    public void setApi_required_defeat_count(Integer api_required_defeat_count) {
        if (api_required_defeat_count > 0)
            this.api_required_defeat_count = api_required_defeat_count;
    }

    @Column(name = "SALLY_FLAG", type = String.class)
    public void setApi_sally_flag(String api_sally_flag) {
        this.api_sally_flag = JSON.parseArray(api_sally_flag);
    }
}
