package com.kancolle.server.service.battle.shelling;

import java.util.List;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.AbstractShip;

public interface IShellingSystem<T extends AbstractShip, E extends AbstractShip> {

    void generateHougkeResult(T ship, BattleContext context);

    void generateAttackList(T ship, BattleContext context);

    E generateDefendList(List<E> ship, BattleContext context);

    void generateAttackTypeList(T ship, BattleContext context);

    void generateSlotItemList(T ship, BattleContext context);

    void generateCrticalList(T attackShip, E defendShip, BattleContext context);

    void generateDamageList(T ship, BattleContext context);
}
