package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;

public class ItemShopModel {

    @JSONField(ordinal = 1)
    private JSONArray api_cabinet_1;

    @JSONField(ordinal = 2)
    private JSONArray api_cabinet_2;

    public JSONArray getApi_cabinet_1() {
        return api_cabinet_1;
    }

    public void setApi_cabinet_1(JSONArray api_cabinet_1) {
        this.api_cabinet_1 = api_cabinet_1;
    }

    public JSONArray getApi_cabinet_2() {
        return api_cabinet_2;
    }

    public void setApi_cabinet_2(JSONArray api_cabinet_2) {
        this.api_cabinet_2 = api_cabinet_2;
    }
}
