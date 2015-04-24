package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.mapper.annotation.Column;
import com.kancolle.server.mapper.annotation.Table;

@Table("t_bgm")
public class BgmModel {

    @JSONField(ordinal = 1)
    private int api_id;

    @JSONField(ordinal = 2)
    private String api_name;

    public int getApi_id() {
        return api_id;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public String getApi_name() {
        return api_name;
    }

    @Column(name = "NAME", type = String.class)
    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }
}
