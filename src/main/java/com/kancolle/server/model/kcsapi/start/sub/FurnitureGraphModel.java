package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;

public class FurnitureGraphModel {

    @JSONField(ordinal = 1)
    private int api_id;

    @JSONField(ordinal = 2)
    private int api_type;

    @JSONField(ordinal = 3)
    private int api_no;

    @JSONField(ordinal = 4)
    private String api_filename;

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

    @Column(name = "FILENAME", type = String.class)
    public void setApi_filename(String api_filename) {
        this.api_filename = api_filename;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Column(name = "NO", type = int.class)
    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }

    @Column(name = "TYPE", type = int.class)
    public void setApi_type(int api_type) {
        this.api_type = api_type;
    }

    @Column(name = "VERSION", type = String.class)
    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }
}
