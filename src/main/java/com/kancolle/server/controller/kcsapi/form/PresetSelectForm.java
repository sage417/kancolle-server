package com.kancolle.server.controller.kcsapi.form;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * Created by SAGE on 2016/12/4.
 */
public class PresetSelectForm {

    @Range(min = 1L, max = 4L)
    private int api_deck_id;

    @Min(1L)
    private int api_preset_no;

    public int getApi_deck_id() {
        return api_deck_id;
    }

    public void setApi_deck_id(int api_deck_id) {
        this.api_deck_id = api_deck_id;
    }

    public int getApi_preset_no() {
        return api_preset_no;
    }

    public void setApi_preset_no(int api_preset_no) {
        this.api_preset_no = api_preset_no;
    }
}
