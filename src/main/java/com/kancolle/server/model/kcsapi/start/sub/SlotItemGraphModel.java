package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.mapper.annotation.Column;

public class SlotItemGraphModel {

    @JSONField(ordinal = 1)
    private int api_id;

    @JSONField(ordinal = 2)
    private int api_sortno;

    @JSONField(ordinal = 3)
    private String api_filename;

    @JSONField(ordinal = 4)
    private String api_version;

    public int getApi_id() {
        return api_id;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_sortno() {
        return api_sortno;
    }

    @Column(name = "SORTNO", type = int.class)
    public void setApi_sortno(int api_sortno) {
        this.api_sortno = api_sortno;
    }

    public String getApi_filename() {
        return api_filename;
    }

    @Column(name = "FILENAME", type = String.class)
    public void setApi_filename(String api_filename) {
        this.api_filename = api_filename;
    }

    public String getApi_version() {
        return api_version;
    }

    @Column(name = "VERSION", type = String.class)
    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }
}
