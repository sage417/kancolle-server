package com.kancolle.server.service.battle.aerial;

import java.util.List;

import com.kancolle.server.model.po.ship.AbstractShip;

public interface IAerialBattleSystem {

    int getAerialPowerStatue(int airPow, int oAirPow);

    int getMemberDeckPortAerialPower(List<? extends AbstractShip> ships);
}
