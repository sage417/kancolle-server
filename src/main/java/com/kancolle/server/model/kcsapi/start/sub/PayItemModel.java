package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("PayItemModel")
@JsonPropertyOrder(value = {
        "api_id", "api_type", "api_name", "api_description",
        "api_item", "api_price"
})
public class PayItemModel implements Serializable {

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1)
    private int api_id;

    @JsonProperty(value = "api_type")
    @JSONField(ordinal = 2)
    private int api_type;

    @JsonProperty(value = "api_name")
    @JSONField(ordinal = 3)
    private String api_name;

    @JsonProperty(value = "api_description")
    @JSONField(ordinal = 4)
    private String api_description;

    @JsonProperty(value = "api_item")
    @JSONField(ordinal = 5)
    private JSONArray api_item;

    @JsonProperty(value = "api_price")
    @JSONField(ordinal = 6)
    private int api_price;

    public String getApi_description() {
        return api_description;
    }

    public int getApi_id() {
        return api_id;
    }

    public JSONArray getApi_item() {
        return api_item;
    }

    public String getApi_name() {
        return api_name;
    }

    public int getApi_price() {
        return api_price;
    }

    public int getApi_type() {
        return api_type;
    }

    public void setApi_description(String api_description) {
        this.api_description = api_description;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public void setApi_item(String api_item) {
        this.api_item = JSON.parseArray(api_item);
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public void setApi_price(int api_price) {
        this.api_price = api_price;
    }

    public void setApi_type(int api_type) {
        this.api_type = api_type;
    }
}
