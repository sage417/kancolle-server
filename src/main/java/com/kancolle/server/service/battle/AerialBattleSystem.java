package com.kancolle.server.service.battle;

import java.util.List;

import com.kancolle.server.model.po.ship.AbstractShip;

public interface AerialBattleSystem {

    int getAerialPowerStatue(int airPow, int oAirPow);

    int getMemberDeckPortAerialPower(List<? extends AbstractShip> ships);
}
