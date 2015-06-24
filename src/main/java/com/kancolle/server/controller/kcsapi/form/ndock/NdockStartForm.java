/**
 * 
 */
package com.kancolle.server.controller.kcsapi.form.ndock;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
public class NdockStartForm {

    @Min(1L)
    private long api_ship_id;

    @NotNull
    private Integer api_highspeed;

    @Range(min = 1L, max = 4L)
    private int api_ndock_id;

    public long getApi_ship_id() {
        return api_ship_id;
    }

    public void setApi_ship_id(long api_ship_id) {
        this.api_ship_id = api_ship_id;
    }

    public Integer getApi_highspeed() {
        return api_highspeed;
    }

    public void setApi_highspeed(Integer api_highspeed) {
        this.api_highspeed = api_highspeed;
    }

    public int getApi_ndock_id() {
        return api_ndock_id;
    }

    public void setApi_ndock_id(int api_ndock_id) {
        this.api_ndock_id = api_ndock_id;
    }
}