package com.kancolle.server.service.battle.shell;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.AbstractShip;

public interface IShellingSystem<T extends AbstractShip> {

    default void generateHougkeResult(T ship, BattleContext context) {
        generateAttackList(ship, context);
        generateDefendList(ship, context);
        generateAttackTypeList(ship, context);
        generateSlotItemList(ship, context);
        generateCrticalList(ship, context);
        generateDamageList(ship, context);
    }

    void generateAttackList(T ship, BattleContext context);

    void generateDefendList(T ship, BattleContext context);

    void generateAttackTypeList(T ship, BattleContext context);

    void generateSlotItemList(T ship, BattleContext context);

    void generateCrticalList(T ship, BattleContext context);

    void generateDamageList(T ship, BattleContext context);
}
