package com.kancolle.server.modle.kcsapi.start.sub;

public class ShipUpgradeModel {

    private int api_id;

    private int api_original_ship_id;

    private int api_upgrade_type;

    private int api_upgrade_level;

    private int api_drawing_count;

    private int api_sortno;

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_original_ship_id() {
        return api_original_ship_id;
    }

    public void setApi_original_ship_id(int api_original_ship_id) {
        this.api_original_ship_id = api_original_ship_id;
    }

    public int getApi_upgrade_type() {
        return api_upgrade_type;
    }

    public void setApi_upgrade_type(int api_upgrade_type) {
        this.api_upgrade_type = api_upgrade_type;
    }

    public int getApi_upgrade_level() {
        return api_upgrade_level;
    }

    public void setApi_upgrade_level(int api_upgrade_level) {
        this.api_upgrade_level = api_upgrade_level;
    }

    public int getApi_drawing_count() {
        return api_drawing_count;
    }

    public void setApi_drawing_count(int api_drawing_count) {
        this.api_drawing_count = api_drawing_count;
    }

    public int getApi_sortno() {
        return api_sortno;
    }

    public void setApi_sortno(int api_sortno) {
        this.api_sortno = api_sortno;
    }
}
