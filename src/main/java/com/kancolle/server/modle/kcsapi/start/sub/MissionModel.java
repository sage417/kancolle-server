package com.kancolle.server.modle.kcsapi.start.sub;

public class MissionModel {

    private int api_id;

    private int api_maparea_id;

    private String api_name;

    private String api_details;

    private int api_time;

    private int api_difficulty;

    private double api_use_fuel;

    private double api_use_bull;

    private int[] api_win_item1;

    private int[] api_win_item2;

    private int api_return_flag;

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_maparea_id() {
        return api_maparea_id;
    }

    public void setApi_maparea_id(int api_maparea_id) {
        this.api_maparea_id = api_maparea_id;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getApi_details() {
        return api_details;
    }

    public void setApi_details(String api_details) {
        this.api_details = api_details;
    }

    public int getApi_time() {
        return api_time;
    }

    public void setApi_time(int api_time) {
        this.api_time = api_time;
    }

    public int getApi_difficulty() {
        return api_difficulty;
    }

    public void setApi_difficulty(int api_difficulty) {
        this.api_difficulty = api_difficulty;
    }

    public double getApi_use_fuel() {
        return api_use_fuel;
    }

    public void setApi_use_fuel(double api_use_fuel) {
        this.api_use_fuel = api_use_fuel;
    }

    public double getApi_use_bull() {
        return api_use_bull;
    }

    public void setApi_use_bull(double api_use_bull) {
        this.api_use_bull = api_use_bull;
    }

    public int[] getApi_win_item1() {
        return api_win_item1;
    }

    public void setApi_win_item1(int[] api_win_item1) {
        this.api_win_item1 = api_win_item1;
    }

    public int[] getApi_win_item2() {
        return api_win_item2;
    }

    public void setApi_win_item2(int[] api_win_item2) {
        this.api_win_item2 = api_win_item2;
    }

    public int getApi_return_flag() {
        return api_return_flag;
    }

    public void setApi_return_flag(int api_return_flag) {
        this.api_return_flag = api_return_flag;
    }
}
