package com.kancolle.server.model.kcsapi.duty;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class DutyItemGetResult {

    @JSONField(ordinal = 1)
    private int[] api_material;

    @JSONField(ordinal = 2)
    private int api_bounus_count;

    @JSONField(ordinal = 3)
    private List<DutyBouns> api_bounus;

    public DutyItemGetResult(int[] api_material, int api_bounus_count, List<DutyBouns> api_bounus) {
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

    public List<DutyBouns> getApi_bounus() {
        return api_bounus;
    }

    public void setApi_bounus(List<DutyBouns> api_bounus) {
        this.api_bounus = api_bounus;
    }
}
