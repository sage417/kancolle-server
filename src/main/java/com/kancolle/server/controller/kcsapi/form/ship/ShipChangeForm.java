package com.kancolle.server.controller.kcsapi.form.ship;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Range;

public class ShipChangeForm {

    @Range(min = 1L, max = 4L)
    private int fleet_id;

    @Min(value = -1L)
    private long ship_id;

    @Range(min = 0L, max = 5L)
    private int ship_idx;

    public int getFleet_id() {
        return fleet_id;
    }

    public void setFleet_id(int fleet_id) {
        this.fleet_id = fleet_id;
    }

    public long getShip_id() {
        return ship_id;
    }

    public void setShip_id(long ship_id) {
        this.ship_id = ship_id;
    }

    public int getShip_idx() {
        return ship_idx;
    }

    public void setShip_idx(int ship_idx) {
        this.ship_idx = ship_idx;
    }
}
