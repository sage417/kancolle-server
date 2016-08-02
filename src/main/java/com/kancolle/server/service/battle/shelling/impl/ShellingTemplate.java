package com.kancolle.server.service.battle.shelling.impl;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.utils.CollectionsUtils;
import com.kancolle.server.utils.logic.ship.ShipFilter;

import java.util.List;

import static com.google.common.collect.Iterables.isEmpty;

/**
 * Package: com.kancolle.server.service.battle.shelling
 * Author: mac
 * Date: 16/4/28
 */
public abstract class ShellingTemplate<A extends IShip, D extends IShip> {

    public void generateHougkeResult(A attackShip, BattleContext context) {
        prepareContext(context);

        D defendShip = chooseTargetShip(attackShip, context);

        if (defendShip == null) {
            return;
        }
        defendShip = callbackAfterChooseTargetShip(attackShip, defendShip, context);

        int[] damages = generateDamageResult(attackShip, defendShip, context);
        callbackAfterDamage(attackShip, defendShip, damages, context);
    }

    /**
     * 准备战斗上下文
     *
     * @param context
     */
    protected abstract void prepareContext(BattleContext context);

    protected D chooseTargetShip(A attackShip, BattleContext context) {
        final List<D> enemySSShips = (List<D>) context.getEnemySSShips();
        if (ShipFilter.antiSSShipFilter.test(attackShip) && !isEmpty(enemySSShips)) {
            return CollectionsUtils.randomGet(enemySSShips);
        }
        final List<D> enemyNormalShips = (List<D>) context.getEnemyNormalShips();
        if (!isEmpty(enemyNormalShips)) {
            return CollectionsUtils.randomGet(enemyNormalShips);
        }
        return null;
    }

    protected abstract D callbackAfterChooseTargetShip(A attackShip, D defendShip, BattleContext context);

    private int[] generateDamageResult(A attackShip, D defendShip, BattleContext context) {
        int attackType = chooseAttackType(attackShip, defendShip);

        int[] damages = attackTwice(attackType) ?
                generateTwiceDamageResult(attackShip, defendShip, context) :
                generateOnceDamageResult(attackShip, defendShip, context);

        augmentingDamage(attackShip, defendShip, damages, context);

        return damages;
    }

    protected abstract int chooseAttackType(A attackShip, D defendShip);

    private boolean attackTwice(int attackType) {
        return attackType == BaseShipShellingSystem.ATTACK_TYPE_DOUBLE;
    }

    protected abstract int[] generateOnceDamageResult(A attackShip, D defendShip, BattleContext context);

    protected abstract int[] generateTwiceDamageResult(A attackShip, D defendShip, BattleContext context);

    protected abstract void augmentingDamage(A attackShip, D defendShip, int[] damages, BattleContext context);

    protected abstract void callbackAfterDamage(A attackShip, D defendShip, int[] damages, BattleContext context);
}
