/**
 * 
 */
package com.kancolle.server.controller.kcsapi.form.ndock;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
public class NdockStartForm {

    @Min(1L)
    private Long api_ship_id;

    @NotNull
    private int api_highspeed;

    @Range(min = 1L, max = 4L)
    private Integer api_ndock_id;

    public Long getApi_ship_id() {
        return api_ship_id;
    }

    public void setApi_ship_id(Long api_ship_id) {
        this.api_ship_id = api_ship_id;
    }

    public int getApi_highspeed() {
        return api_highspeed;
    }

    public void setApi_highspeed(int api_highspeed) {
        this.api_highspeed = api_highspeed;
    }

    public Integer getApi_ndock_id() {
        return api_ndock_id;
    }

    public void setApi_ndock_id(Integer api_ndock_id) {
        this.api_ndock_id = api_ndock_id;
    }
}
