package com.kancolle.server.service.battle.aerial;

import com.kancolle.server.model.po.ship.IShip;

import java.util.List;

public interface IAerialBattleSystem {

    int getAerialPowerStatue(int airPow, int oAirPow);

    int getMemberDeckPortAerialPower(List<? extends IShip> ships);
}
