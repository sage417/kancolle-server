package com.kancolle.server.controller.kcsapi.form.deckport;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Range;

public class ShipChangeForm {

    @Range(min = 1L, max = 4L)
    private Integer fleet_id;

    @Min(value = -1L)
    private Long ship_id;

    @Range(min = 0L, max = 5L)
    private Integer ship_idx;

    public Integer getFleet_id() {
        return fleet_id;
    }

    public void setFleet_id(Integer fleet_id) {
        this.fleet_id = fleet_id;
    }

    public Long getShip_id() {
        return ship_id;
    }

    public void setShip_id(Long ship_id) {
        this.ship_id = ship_id;
    }

    public Integer getShip_idx() {
        return ship_idx;
    }

    public void setShip_idx(Integer ship_idx) {
        this.ship_idx = ship_idx;
    }
}
