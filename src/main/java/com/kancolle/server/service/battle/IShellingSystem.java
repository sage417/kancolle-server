package com.kancolle.server.service.battle;

import com.google.common.collect.ImmutableBiMap;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.MemberShip;

public interface IShellingSystem {

    void generateHougkeResult(BattleSimulationResult result, int aerialState, MemberShip attackShip, ImmutableBiMap<Integer, EnemyShip> enemyOtherShipsMap);

}
