/**
 * 
 */
package com.kancolle.server.controller.kcsapi.form.ship;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年7月3日
 *
 */
public class ShipPowerUpForm {

    @NotNull
    private Long api_id;

    @NotEmpty
    private List<Long> api_id_items;

    public Long getApi_id() {
        return api_id;
    }

    public void setApi_id(Long api_id) {
        this.api_id = api_id;
    }

    public List<Long> getApi_id_items() {
        return api_id_items;
    }

    public void setApi_id_items(List<Long> api_id_items) {
        this.api_id_items = api_id_items;
    }
}
