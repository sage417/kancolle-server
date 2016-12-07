package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("FurnitureGraphModel")
@JsonPropertyOrder(value = {
        "api_id", "api_type", "api_no", "api_filename",
        "api_version"
})
public class FurnitureGraphModel implements Serializable {

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1)
    private int api_id;

    @JsonProperty(value = "api_type")
    @JSONField(ordinal = 2)
    private int api_type;

    @JsonProperty(value = "api_no")
    @JSONField(ordinal = 3)
    private int api_no;

    @JsonProperty(value = "api_filename")
    @JSONField(ordinal = 4)
    private String api_filename;

    @JsonProperty(value = "api_version")
    @JSONField(ordinal = 5)
    private String api_version;

    public String getApi_filename() {
        return api_filename;
    }

    public int getApi_id() {
        return api_id;
    }

    public int getApi_no() {
        return api_no;
    }

    public int getApi_type() {
        return api_type;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_filename(String api_filename) {
        this.api_filename = api_filename;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }

    public void setApi_type(int api_type) {
        this.api_type = api_type;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }
}
