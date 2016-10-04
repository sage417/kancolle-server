package com.kancolle.server.controller.kcsapi.form.ship;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * Created by SAGE on 2016/10/4.
 */
public class ExChangeSlotForm {

    @Min(value = 1L)
    private Long api_id;

    @Range(max = 3L)
    private int api_src_idx;

    @Range(max = 3L)
    private int api_dst_idx;

    public Long getApi_id() {
        return api_id;
    }

    public void setApi_id(Long api_id) {
        this.api_id = api_id;
    }

    public int getApi_dst_idx() {
        return api_dst_idx;
    }

    public void setApi_dst_idx(int api_dst_idx) {
        this.api_dst_idx = api_dst_idx;
    }

    public int getApi_src_idx() {
        return api_src_idx;
    }

    public void setApi_src_idx(int api_src_idx) {
        this.api_src_idx = api_src_idx;
    }
}
