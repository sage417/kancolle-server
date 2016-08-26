package com.kancolle.server.controller.kcsapi.form.deckport;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

public class ShipChangeForm {

    @Range(min = 1L, max = 4L)
    private Integer api_id;

    @Min(value = -2L)
    private Long api_ship_id;

    @Range(min = -1L, max = 5L)
    private Integer api_ship_idx;

    public Integer getApi_id() {
        return api_id;
    }

    public void setApi_id(Integer api_id) {
        this.api_id = api_id;
    }

    public Long getApi_ship_id() {
        return api_ship_id;
    }

    public void setApi_ship_id(Long api_ship_id) {
        this.api_ship_id = api_ship_id;
    }

    public Integer getApi_ship_idx() {
        return api_ship_idx;
    }

    public void setApi_ship_idx(Integer api_ship_idx) {
        this.api_ship_idx = api_ship_idx;
    }
}
