/**
 * 
 */
package com.kancolle.server.controller.kcsapi.form.ship;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
public class ShipChargeForm {

    @Range(min = 1L, max = 3L)
    private int api_kind;

    @NotEmpty
    private List<Long> api_id_items;

    @NotNull
    private Integer api_onslot;

    public int getApi_kind() {
        return api_kind;
    }

    public void setApi_kind(int api_kind) {
        this.api_kind = api_kind;
    }

    public List<Long> getApi_id_items() {
        return api_id_items;
    }

    public void setApi_id_items(List<Long> api_id_items) {
        this.api_id_items = api_id_items;
    }

    public Integer getApi_onslot() {
        return api_onslot;
    }

    public void setApi_onslot(Integer api_onslot) {
        this.api_onslot = api_onslot;
    }
}
