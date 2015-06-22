package com.kancolle.server.controller.kcsapi.form.mission;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MissionStartForm {

    @NotNull
    @Min(value = 2)
    private int api_deck_id;

    @NotNull
    @Min(value = 1)
    private int api_mission_id;

    private int api_mission;

    public int getApi_deck_id() {
        return api_deck_id;
    }

    public int getApi_mission() {
        return api_mission;
    }

    public int getApi_mission_id() {
        return api_mission_id;
    }

    public void setApi_deck_id(int api_deck_id) {
        this.api_deck_id = api_deck_id;
    }

    public void setApi_mission(int api_mission) {
        this.api_mission = api_mission;
    }

    public void setApi_mission_id(int api_mission_id) {
        this.api_mission_id = api_mission_id;
    }
}
