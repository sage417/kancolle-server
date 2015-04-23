package com.kancolle.server.model.kcsapi.start.sub;

public class FurnitureGraphModel {

    private int api_id;

    private int api_type;

    private int api_no;

    private String api_filename;

    private String api_version;

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_type() {
        return api_type;
    }

    public void setApi_type(int api_type) {
        this.api_type = api_type;
    }

    public int getApi_no() {
        return api_no;
    }

    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }

    public String getApi_filename() {
        return api_filename;
    }

    public void setApi_filename(String api_filename) {
        this.api_filename = api_filename;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }
}
