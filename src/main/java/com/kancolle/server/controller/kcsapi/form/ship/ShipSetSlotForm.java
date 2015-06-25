/**
 * 
 */
package com.kancolle.server.controller.kcsapi.form.ship;

import javax.validation.constraints.Min;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
public class ShipSetSlotForm {

    @Min(value = 0L)
    private Long api_id;

    @Min(value = -1L)
    private Long api_item_id;

    @Min(value = 0L)
    private Integer api_slot_idx;

    public Long getApi_id() {
        return api_id;
    }

    public void setApi_id(Long api_id) {
        this.api_id = api_id;
    }

    public Long getApi_item_id() {
        return api_item_id;
    }

    public void setApi_item_id(Long api_item_id) {
        this.api_item_id = api_item_id;
    }

    public Integer getApi_slot_idx() {
        return api_slot_idx;
    }

    public void setApi_slot_idx(Integer api_slot_idx) {
        this.api_slot_idx = api_slot_idx;
    }

}
