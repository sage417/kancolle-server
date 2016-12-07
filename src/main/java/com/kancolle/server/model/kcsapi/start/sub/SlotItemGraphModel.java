package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("SlotItemGraphModel")
@JsonPropertyOrder(value = {
        "api_id", "api_sortno", "api_filename", "api_version"
})
public class SlotItemGraphModel implements Serializable {

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
    private String api_version;

    public String getApi_filename() {
        return api_filename;
    }

    public int getApi_id() {
        return api_id;
    }

    public int getApi_sortno() {
        return api_sortno;
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

    public void setApi_sortno(int api_sortno) {
        this.api_sortno = api_sortno;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }
}
