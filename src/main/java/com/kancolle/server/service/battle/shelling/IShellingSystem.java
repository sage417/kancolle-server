package com.kancolle.server.service.battle.shelling;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;

public interface IShellingSystem<T extends IShip, E extends IShip> {

    void generateHougkeResult(T ship, BattleContext context);

    void generateDamageList(T attackShip, E defendShip, BattleContext context);
}
