package com.kancolle.server.controller.kcsapi.form;

public class ChangeFurnitureForm {

    private int api_wallpaper;

    private int api_window;

    private int api_desk;

    private int api_floor;

    private int api_wallhanging;

    private int api_shelf;

    public int getApi_desk() {
        return api_desk;
    }

    public int getApi_floor() {
        return api_floor;
    }

    public int getApi_shelf() {
        return api_shelf;
    }

    public int getApi_wallhanging() {
        return api_wallhanging;
    }

    public int getApi_wallpaper() {
        return api_wallpaper;
    }

    public int getApi_window() {
        return api_window;
    }

    public void setApi_desk(int api_desk) {
        this.api_desk = api_desk;
    }

    public void setApi_floor(int api_floor) {
        this.api_floor = api_floor;
    }

    public void setApi_shelf(int api_shelf) {
        this.api_shelf = api_shelf;
    }

    public void setApi_wallhanging(int api_wallhanging) {
        this.api_wallhanging = api_wallhanging;
    }

    public void setApi_wallpaper(int api_wallpaper) {
        this.api_wallpaper = api_wallpaper;
    }

    public void setApi_window(int api_window) {
        this.api_window = api_window;
    }
}
