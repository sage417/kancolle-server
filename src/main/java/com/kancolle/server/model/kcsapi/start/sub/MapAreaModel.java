package com.kancolle.server.model.kcsapi.start.sub;

import com.kancolle.server.mapper.annotation.Column;

public class MapAreaModel {

    private int api_id;

    private String api_name;

    private int api_type;

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

    public int getApi_type() {
        return api_type;
    }

    @Column(name = "TYPE", type = int.class)
    public void setApi_type(int api_type) {
        this.api_type = api_type;
    }
}
