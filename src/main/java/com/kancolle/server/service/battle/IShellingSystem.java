package com.kancolle.server.service.battle;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.MemberShip;

public interface IShellingSystem {

    void generateHougkeResult(MemberShip ship, BattleContext context);

    void generateHougkeResult(EnemyShip ship, BattleContext context);

}
