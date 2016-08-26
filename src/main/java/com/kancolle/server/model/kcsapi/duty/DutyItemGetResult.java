package com.kancolle.server.model.kcsapi.duty;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder(value = {
        "api_material", "api_bounus_count", "api_bounus"
})
public class DutyItemGetResult {

    @JsonProperty(value = "api_material")
    @JSONField(ordinal = 1)
    private int[] api_material;

    @JsonProperty(value = "api_bounus_count")
    @JSONField(ordinal = 2)
    private int api_bounus_count;

    @JsonProperty(value = "api_bounus")
    @JSONField(ordinal = 3)
    private List<DutyBonusResult> api_bounus;

    public DutyItemGetResult(int[] api_material, int api_bounus_count, List<DutyBonusResult> api_bounus) {
        super();
        this.api_material = api_material;
        this.api_bounus_count = api_bounus_count;
        this.api_bounus = api_bounus;
    }

    public int[] getApi_material() {
        return api_material;
    }

    public void setApi_material(int[] api_material) {
        this.api_material = api_material;
    }

    public int getApi_bounus_count() {
        return api_bounus_count;
    }

    public void setApi_bounus_count(int api_bounus_count) {
        this.api_bounus_count = api_bounus_count;
    }

    public List<DutyBonusResult> getApi_bounus() {
        return api_bounus;
    }

    public void setApi_bounus(List<DutyBonusResult> api_bounus) {
        this.api_bounus = api_bounus;
    }
}
