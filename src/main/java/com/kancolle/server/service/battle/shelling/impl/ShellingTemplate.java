package com.kancolle.server.service.battle.shelling.impl;

import com.google.common.collect.ImmutableBiMap;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.utils.CollectionsUtils;
import com.kancolle.server.utils.logic.ship.ShipFilter;

import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Iterables.isEmpty;

/**
 * Package: com.kancolle.server.service.battle.shelling
 * Author: mac
 * Date: 16/4/28
 */
public abstract class ShellingTemplate<A extends IShip, D extends IShip> {

    public final void generateHougkeResult(final A attackShip, final BattleContext context) {
        prepareContext(context);

        D defendShip = chooseTargetShip(attackShip, context);

        if (defendShip == null) {
            return;
        }
        // 1. add idx to attack list
        addToAttackList(attackShip, context);

        // 2.
        defendShip = callBackAfterChooseTargetShip(attackShip, defendShip, context);

        // 3. decide attack type and slotItem
        final int attackType = chooseAttackTypeAndSlotItem(attackShip, defendShip, context);

        // 4. add idx to defend list
        addToDefendList(defendShip, attackType, context);

        // 5. add critical list
        final int[] criticals = addToCriticalList(attackShip, attackType, defendShip, context);

        // 6. add damage result
        final int[] damages = generateDamageResult(attackShip, defendShip, attackType, criticals, context);

        // 7. correct damage
        final int[] actualDamages = generateActualDamage(defendShip, damages, context);

        // 8. add to damage list
        addToDamageList(actualDamages, context);

        callbackAfterDamage(attackShip, defendShip, actualDamages, damages, context);
    }

    /**
     * Step 0 prepare battle context
     *
     * @param context
     */
    protected void prepareContext(final BattleContext context) {

    }

    protected final void addToAttackList(final A attackShip, final BattleContext context) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();
        final ImmutableBiMap<Integer, IShip> shipsMap = context.getShipMap();
        hougekiResult.getApi_at_list().add(shipsMap.inverse().get(attackShip));
    }

    /**
     * Step 2 choose attack target
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

    private D callBackAfterChooseTargetShip(final A attackShip, final D defendShip, final BattleContext context) {
        // 如果是旗舰受攻击,僚舰可以为其抵挡攻击

        // 如果旗舰是潜艇,只有其他潜艇可以为其抵挡伤害

        return defendShip;
    }

    /**
     * Step 3
     * Decide attackType and add to sl list
     *
     * @param attackShip
     * @param defendShip
     * @param context
     * @return
     */
    protected abstract int chooseAttackTypeAndSlotItem(A attackShip, D defendShip, BattleContext context);

    /**
     * Step 4
     *
     * @param defendShip
     * @param attackType
     * @param context
     */
    protected final void addToDefendList(final D defendShip, final int attackType, final BattleContext context) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();
        final ImmutableBiMap<Integer, IShip> shipsMap = context.getShipMap();
        final int defShipIdx = shipsMap.inverse().get(defendShip);
        final int[] defArr = attackType == BaseShipShellingSystem.ATTACK_TYPE_DOUBLE ? new int[]{defShipIdx, defShipIdx} : new int[]{defShipIdx};
        hougekiResult.getApi_df_list().add(defArr);
    }

    /**
     * Step 5
     *
     * @param attackShip
     * @param attackType
     * @param defendShip
     * @param context
     * @return
     */
    protected abstract int[] addToCriticalList(final A attackShip, final int attackType, final D defendShip, final BattleContext context);

    /**
     * Step 6
     *
     * @param attackShip
     * @param defendShip
     * @param attackType
     * @param criticals
     * @param context
     * @return
     */
    protected abstract int[] generateDamageResult(final A attackShip, final D defendShip, final int attackType, final int[] criticals, final BattleContext context);

    protected abstract int[] generateActualDamage(D defendShip, int[] damages, BattleContext context);

    private void addToDamageList(int[] damages, BattleContext context) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();
        hougekiResult.getApi_damage().add(damages);
    }

    protected void callbackAfterDamage(A attackShip, D defendShip, int[] actualDamages, int[] damages, BattleContext context) {
        int actualDamageSum = Arrays.stream(actualDamages).sum();
        defendShip.setNowHp(defendShip.getNowHp() - actualDamageSum);
        if (ShipFilter.isAlive.negate().test(defendShip)) {
            defendShip.setNowHp(0);
            context.getEnemyNormalShips().remove(defendShip);
            context.getEnemySSShips().remove(defendShip);
        }
    }
}
