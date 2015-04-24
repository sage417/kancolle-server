package com.kancolle.server.model.kcsapi.start.sub;

import java.util.List;

public class ItemShopModel {

    private List<Integer> api_cabinet_1;

    private List<Integer> api_cabinet_2;

    public ItemShopModel(List<Integer> api_cabinet_1, List<Integer> api_cabinet_2) {
        this.api_cabinet_1 = api_cabinet_1;
        this.api_cabinet_2 = api_cabinet_2;
    }

    public List<Integer> getApi_cabinet_1() {
        return api_cabinet_1;
    }

    public void setApi_cabinet_1(List<Integer> api_cabinet_1) {
        this.api_cabinet_1 = api_cabinet_1;
    }

    public List<Integer> getApi_cabinet_2() {
        return api_cabinet_2;
    }

    public void setApi_cabinet_2(List<Integer> api_cabinet_2) {
        this.api_cabinet_2 = api_cabinet_2;
    }
}
