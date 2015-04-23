package com.kancolle.server.modle.kcsapi.start.sub;

public class SlotItemGraphModel {

    private int api_data;

    private int api_sortno;

    private String filename;

    private String api_version;

    public int getApi_data() {
        return api_data;
    }

    public void setApi_data(int api_data) {
        this.api_data = api_data;
    }

    public int getApi_sortno() {
        return api_sortno;
    }

    public void setApi_sortno(int api_sortno) {
        this.api_sortno = api_sortno;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }
}
