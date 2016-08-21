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

    public void generateHougkeResult(final A attackShip, final BattleContext context, final int aerialState) {
        prepareContext(context);

        D defendShip = chooseTargetShip(attackShip, context);

        if (defendShip == null) {
            return;
        }
        // 1. add idx to attack list
        addToAttackList(attackShip, context);

        defendShip = callBackAfterChooseTargetShip(attackShip, defendShip, context);

        // 2. decide attack type and slotItem
        final int attackType = chooseAttackTypeAndSlotItem(attackShip, defendShip, aerialState, context);

        // 3. add idx to defend list
        addToDefendList(defendShip, attackType, context);

        // 4. add critical list
        final int[] criticals = addToCriticalList(attackShip, attackType, defendShip, context);

        // 5. add damage result
        final int[] damages = generateDamageResult(attackShip, defendShip, attackType, criticals, context);
        callbackAfterDamage(attackShip, defendShip, damages, context);
    }

    /**
     * 选取攻击目标
     *
     * @param attackShip
     * @param context
     * @return
     */
    protected final D chooseTargetShip(final A attackShip, final BattleContext context) {
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

    protected final void addToAttackList(final A attackShip, final BattleContext context) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();
        final ImmutableBiMap<Integer, IShip> shipsMap = context.getShipMap();
        hougekiResult.getApi_at_list().add(shipsMap.inverse().get(attackShip));
    }

    private D callBackAfterChooseTargetShip(final A attackShip, final D defendShip, final BattleContext context) {
        // 如果是旗舰受攻击,僚舰可以为其抵挡攻击

        // 如果旗舰是潜艇,只有其他潜艇可以为其抵挡伤害

        return defendShip;
    }

    /**
     * Step 2
     * Decide attackType and add to sl list
     *
     * @param attackShip
     * @param defendShip
     * @param aerialState
     * @param context
     * @return
     */
    protected abstract int chooseAttackTypeAndSlotItem(A attackShip, D defendShip, int aerialState, BattleContext context);

    private int[] addToCriticalList(final A attackShip, final int attackType, final D defendShip, final BattleContext context) {
        return null;
    }

    protected final void addToDefendList(final D defendShip, final int attackType, final BattleContext context) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();
        final ImmutableBiMap<Integer, IShip> shipsMap = context.getShipMap();
        final int defShipIdx = shipsMap.inverse().get(defendShip);
        final int[] defArr = attackType == BaseShipShellingSystem.ATTACK_TYPE_DOUBLE ? new int[]{defShipIdx, defShipIdx} : new int[]{defShipIdx};
        hougekiResult.getApi_df_list().add(defArr);
    }

    /**
     * 准备战斗上下文
     *
     * @param context
     */
    protected void prepareContext(final BattleContext context) {

    }

    private int[] generateDamageResult(final A attackShip, final D defendShip, final int attackType, final int[] criticals, final BattleContext context) {
        final int[] damages = attackTwice(attackType) ?
                generateTwiceDamageResult(attackShip, defendShip, context) :
                generateOnceDamageResult(attackShip, defendShip, context);

        augmentingDamage(attackShip, defendShip, damages, context);

        return damages;
    }

    private boolean attackTwice(final int attackType) {
        return attackType == BaseShipShellingSystem.ATTACK_TYPE_DOUBLE;
    }

    protected abstract int[] generateOnceDamageResult(A attackShip, D defendShip, BattleContext context);

    protected abstract int[] generateTwiceDamageResult(A attackShip, D defendShip, BattleContext context);

    protected abstract void augmentingDamage(A attackShip, D defendShip, int[] damages, BattleContext context);

    protected abstract void callbackAfterDamage(A attackShip, D defendShip, int[] damages, BattleContext context);
}
