package com.kancolle.server.controller.kcsapi.form.mission;

import javax.validation.constraints.Min;

public class MissionStartForm {

    @Min(value = 2)
    private Integer api_deck_id;

    @Min(value = 1)
    private Integer api_mission_id;

    private Integer api_mission;

    public Integer getApi_deck_id() {
        return api_deck_id;
    }

    public void setApi_deck_id(Integer api_deck_id) {
        this.api_deck_id = api_deck_id;
    }

    public Integer getApi_mission_id() {
        return api_mission_id;
    }

    public void setApi_mission_id(Integer api_mission_id) {
        this.api_mission_id = api_mission_id;
    }

    public Integer getApi_mission() {
        return api_mission;
    }

    public void setApi_mission(Integer api_mission) {
        this.api_mission = api_mission;
    }
}
