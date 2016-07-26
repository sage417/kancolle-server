package com.kancolle.server.service.battle.shelling.template;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.service.battle.shelling.HougkeConst;

/**
 * Package: com.kancolle.server.service.battle.shelling
 * Author: mac
 * Date: 16/4/28
 */
public abstract class ShellingTemplate<T extends IShip, E extends IShip> {

    public void generateHougkeResult(T attackShip, BattleContext context) {

        E defendShip = chooseTargetShip(attackShip, context);

        int[] damages = generateDamageResult(attackShip, defendShip, context);
    }

    public abstract E chooseTargetShip(T attackShip, BattleContext context);

    public int[] generateDamageResult(T attackShip, E defendShip, BattleContext context) {
        int attackType = chooseAttackType(attackShip, defendShip);

        switch (hitCondition(attackShip, defendShip, attackType, context)) {
            case HIT:
            case SCRATCH:
            case MISS:
            default:
        }

        return new int[0];
    }

    protected abstract int chooseAttackType(T attackShip, E defendShip);

    protected abstract HougkeConst.HitType hitCondition(T attackShip, E defendShip, int attackType, BattleContext context);

    protected abstract void callbackDamage();

}
