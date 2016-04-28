package com.kancolle.server.service.battle.shelling.template;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.utils.CollectionsUtils;
import com.kancolle.server.utils.logic.battle.BattleContextUtils;

import java.util.List;

/**
 * Created by J.K.SAGE on 2016/4/28 0028.
 */
public class HoukigeShellingTemplate<T extends IShip, E extends IShip> extends ShellingTemplate<T, E> {

    private static final int ATTACK_TYPE_ANTISUBMARINE = 1;

    @Override
    protected int getAttackType(T attackShip, BattleContext context) {
        return 0;
    }

    @Override
    protected E getDefendShip(BattleContext context) {

        int attackType = BattleContextUtils.getCurrentAttackType(context);

        List<E> enemyDefendShips = attackType == ATTACK_TYPE_ANTISUBMARINE
                ? (List<E>) context.getNowSSShips()
                : (List<E>) context.getNowOtherShips();

        return CollectionsUtils.randomGet(enemyDefendShips);
    }

    @Override
    protected boolean getIsHit(T attackShip, E defendShip, BattleContext context) {
        return true;
    }
}
