package com.kancolle.server.service.battle.shelling.impl;

import com.google.common.collect.ImmutableBiMap;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.battle.SlotItemInfo;
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

    public void generateHougkeResult(final A attackShip, final BattleContext context) {
        prepareContext(context);

        D defendShip = chooseTargetShip(attackShip, context);

        if (defendShip == null) {
            return;
        }
        // 1. add idx to attack list
        addToAttackList(attackShip, context);

        defendShip = callBackAfterChooseTargetShip(attackShip, defendShip, context);

        // 2. decide attack type and slotItem
        int attackType = chooseAttackTypeAndSlotItem(attackShip, defendShip, context);

        // 3. add idx to defend list
        addToDefendList(defendShip, attackType, context);

        // 4. add critical list
        int[] criticals = addToCriticalList(attackShip, attackType, defendShip, context);

        // 5. add damage result
        int[] damages = generateDamageResult(attackShip, defendShip, attackType, criticals, context);
        callbackAfterDamage(attackShip, defendShip, damages, context);
    }

    protected abstract int[] addToCriticalList(A attackShip, int attackType, D defendShip, BattleContext context);

    protected final void addToAttackList(final A attackShip, final BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        ImmutableBiMap<Integer, IShip> shipsMap = context.getShipMap();
        hougekiResult.getApi_at_list().add(shipsMap.inverse().get(attackShip));
    }

    private final void addToDefendList(final D defendShip, final int attackType, final BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        ImmutableBiMap<Integer, IShip> shipsMap = context.getShipMap();
        int defShipIdx = shipsMap.inverse().get(defendShip);
        int[] defArr = attackType == BaseShipShellingSystem.ATTACK_TYPE_DOUBLE ? new int[]{defShipIdx, defShipIdx} : new int[]{defShipIdx};
        hougekiResult.getApi_df_list().add(defArr);
    }

    /**
     * 准备战斗上下文
     *
     * @param context
     */
    protected void prepareContext(final BattleContext context) {

    }

    /**
     * 选取攻击目标
     *
     * @param attackShip
     * @param context
     * @return
     */
    final D chooseTargetShip(final A attackShip, final BattleContext context) {
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

    protected final D callBackAfterChooseTargetShip(final A attackShip, final D defendShip, final BattleContext context) {
        // 如果是旗舰受攻击,僚舰可以为其抵挡攻击

        // 如果旗舰是潜艇,只有其他潜艇可以为其抵挡伤害

        return defendShip;
    }

    private int[] generateDamageResult(A attackShip, D defendShip, int attackType, int[] criticals, BattleContext context) {
        int[] damages = attackTwice(attackType) ?
                generateTwiceDamageResult(attackShip, defendShip, context) :
                generateOnceDamageResult(attackShip, defendShip, context);

        augmentingDamage(attackShip, defendShip, damages, context);

        return damages;
    }

    protected int chooseAttackTypeAndSlotItem(final A attackShip, final D defendShip, final BattleContext context) {
        if (ShipFilter.ssFilter.test(defendShip)) {
            return BaseShipShellingSystem.ATTACK_TYPE_ANTISUBMARINE;
        }

        SlotItemInfo slotItemInfo = SlotItemInfo.of(attackShip);



        return BaseShipShellingSystem.ATTACK_TYPE_NORMAL;
    }

    private boolean attackTwice(int attackType) {
        return attackType == BaseShipShellingSystem.ATTACK_TYPE_DOUBLE;
    }

    protected abstract int[] generateOnceDamageResult(A attackShip, D defendShip, BattleContext context);

    protected abstract int[] generateTwiceDamageResult(A attackShip, D defendShip, BattleContext context);

    protected abstract void augmentingDamage(A attackShip, D defendShip, int[] damages, BattleContext context);

    protected abstract void callbackAfterDamage(A attackShip, D defendShip, int[] damages, BattleContext context);
}
