package com.kancolle.server.controller.kcsapi.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * Created by SAGE on 16/8/27.
 */
public class PresetDeckRegisterFrom {

    @Min(1L)
    private int api_preset_no;

    @Range(min = 1L, max = 4L)
    private int api_deck_id;

    @NotEmpty
    private String api_name;

    private String api_name_id;

    public int getApi_preset_no() {
        return api_preset_no;
    }

    public void setApi_preset_no(int api_preset_no) {
        this.api_preset_no = api_preset_no;
    }

    public int getApi_deck_id() {
        return api_deck_id;
    }

    public void setApi_deck_id(int api_deck_id) {
        this.api_deck_id = api_deck_id;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getApi_name_id() {
        return api_name_id;
    }

    public void setApi_name_id(String api_name_id) {
        this.api_name_id = api_name_id;
    }
}
