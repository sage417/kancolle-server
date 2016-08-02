package com.kancolle.server.service.battle.shelling.impl;

import com.google.common.collect.ImmutableBiMap;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
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
        // 1. add idx to attack list
        addToAttackList(attackShip, context);
        defendShip = callbackAfterChooseTargetShip(attackShip, defendShip, context);

        // 2. decide attack type
        int attackType = chooseAttackType(attackShip, defendShip, context);

        int[] damages = generateDamageResult(attackShip, defendShip, context);
        callbackAfterDamage(attackShip, defendShip, damages, context);
    }

    protected void addToAttackList(A attackShip, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        ImmutableBiMap<Integer, IShip> shipsMap = context.getShipMap();
        hougekiResult.getApi_at_list().add(shipsMap.inverse().get(attackShip));
    }

    /**
     * 准备战斗上下文
     *
     * @param context
     */
    protected abstract void prepareContext(BattleContext context);

    /**
     * 选取攻击目标
     * @param attackShip
     * @param context
     * @return
     */
    protected D chooseTargetShip(A attackShip, BattleContext context) {
        List<? extends IShip> attackableShips = null;
        final List<? extends IShip> enemySSShips = context.getEnemySSShips();
        if (ShipFilter.antiSSShipFilter.test(attackShip) && !isEmpty(enemySSShips)) {
            attackableShips = enemySSShips;
        }
        final List<? extends IShip> enemyNormalShips = context.getEnemyNormalShips();
        if (!isEmpty(enemyNormalShips)) {
            attackableShips = enemyNormalShips;
        }
        return attackableShips == null ? null : (D) CollectionsUtils.randomGet(attackableShips);
    }

    protected abstract D callbackAfterChooseTargetShip(A attackShip, D defendShip, BattleContext context);

    private int[] generateDamageResult(A attackShip, D defendShip, BattleContext context) {
        int attackType = chooseAttackType(attackShip, defendShip, context);

        int[] damages = attackTwice(attackType) ?
                generateTwiceDamageResult(attackShip, defendShip, context) :
                generateOnceDamageResult(attackShip, defendShip, context);

        augmentingDamage(attackShip, defendShip, damages, context);

        return damages;
    }

    protected int chooseAttackType(A attackShip, D defendShip, BattleContext context) {
        return 0;
    }

    private boolean attackTwice(int attackType) {
        return attackType == BaseShipShellingSystem.ATTACK_TYPE_DOUBLE;
    }

    protected abstract int[] generateOnceDamageResult(A attackShip, D defendShip, BattleContext context);

    protected abstract int[] generateTwiceDamageResult(A attackShip, D defendShip, BattleContext context);

    protected abstract void augmentingDamage(A attackShip, D defendShip, int[] damages, BattleContext context);

    protected abstract void callbackAfterDamage(A attackShip, D defendShip, int[] damages, BattleContext context);
}
