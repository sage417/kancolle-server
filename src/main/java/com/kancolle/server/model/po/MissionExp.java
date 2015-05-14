package com.kancolle.server.model.po;

import com.kancolle.server.dao.annotation.Column;

public class MissionExp {

    private int exp;

    private int shipExp;

    public int getExp() {
        return exp;
    }

    public int getShipExp() {
        return shipExp;
    }

    @Column(name = "EXP", type = int.class)
    public void setExp(int exp) {
        this.exp = exp;
    }

    @Column(name = "SHIP_EXP", type = int.class)
    public void setShipExp(int shipExp) {
        this.shipExp = shipExp;
    }
}
