package com.kancolle.server.service.battle.shelling;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;

import java.util.List;

public interface IShellingSystem<T extends IShip, E extends IShip> {

    void generateHougkeResult(T ship, BattleContext context);

    void generateAttackList(T ship, BattleContext context);

    E generateDefendList(List<E> ship, BattleContext context);

    void generateShellingAttackTypeList(T attackShip, BattleContext context);

    void generateSlotItemList(T ship, BattleContext context);

    void generateCrticalList(T attackShip, E defendShip, BattleContext context);

    void generateDamageList(T attackShip, E defendShip, BattleContext context);
}
