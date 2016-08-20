package com.kancolle.server.service.battle.shelling.impl;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Lists;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.battle.SlotItemInfo;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.service.battle.aerial.AerialBattleSystem;
import com.kancolle.server.utils.CollectionsUtils;
import com.kancolle.server.utils.logic.ship.ShipFilter;
import com.kancolle.server.utils.logic.ship.ShipUtils;

import java.util.LinkedList;
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
    private D chooseTargetShip(final A attackShip, final BattleContext context) {
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
     * 彈著觀測射擊：
     * <p>
     * 發動條件：
     * 1.必須有觸發航空戰，且我方取得制空優勢或制空權確保下才會有機會發動
     * 2.艦娘須裝備上水偵或水爆，且水偵或水爆數量必須大於1才有機會發動
     * 3.大破艦娘不能發動彈著觀測射擊
     * 4.滿足上述發動配置的裝備數量皆可發動，當裝備滿足複數類型的特殊攻擊時，會機率性的發動其中一樣
     * 若彈著觀測射擊未發動成功，則會進行通常砲擊
     */
    private int chooseAttackTypeAndSlotItem(final A attackShip, final D defendShip, final int aerialState, final BattleContext context) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();

        final LinkedList<Integer> at_type_list = hougekiResult.getApi_at_type();
        final LinkedList<Object> si_list = hougekiResult.getApi_si_list();

        int attackType = BaseShipShellingSystem.ATTACK_TYPE_NORMAL;
        final List<Integer> si = Lists.newArrayListWithCapacity(4);

        if (ShipFilter.ssFilter.test(defendShip)) {
            attackType = BaseShipShellingSystem.ATTACK_TYPE_ANTISUBMARINE;
        } else {
            // TODO cache slotItem info
            final SlotItemInfo slotItemInfo = SlotItemInfo.of(attackShip);
            if (canObservationShootingDecideByAerialState(aerialState) && ShipUtils.isBadlyDmg.test(defendShip) && canObservationShootingDecideBySlotItem(slotItemInfo)) {

                final int mainGunCount = slotItemInfo.getMainGunCount();
                final int secondaryGunCount = slotItemInfo.getSecondaryGunCount();
                final int radarCount = slotItemInfo.getRadarCount();
                final int apAmmoCount = slotItemInfo.getAPAmmoCount();

                // 连击(主主)
                if (mainGunCount > 1 && doObservationShooting(attackShip, BaseShipShellingSystem.ATTACK_TYPE_DOUBLE, aerialState, context)) {
                    attackType = BaseShipShellingSystem.ATTACK_TYPE_DOUBLE;
                    si.addAll(slotItemInfo.getMainGunIds().subList(0, 2));
                }


                // 主炮CI(主主+撤甲)
                if (mainGunCount > 1 && apAmmoCount > 0 && doObservationShooting(attackShip, BaseShipShellingSystem.ATTACK_TYPE_MAIN, aerialState, context)) {
                    attackType = BaseShipShellingSystem.ATTACK_TYPE_MAIN;
                    si.addAll(slotItemInfo.getMainGunIds().subList(0, 2));
                    si.add(slotItemInfo.getApAmmoIds().iterator().next());
                }

                // 主副CI(主副)
                if (mainGunCount > 0 && secondaryGunCount > 0 && doObservationShooting(attackShip, BaseShipShellingSystem.ATTACK_TYPE_SECONDARY, aerialState, context)) {
                    attackType = BaseShipShellingSystem.ATTACK_TYPE_SECONDARY;
                    si.add(slotItemInfo.getMainGunIds().iterator().next());
                    si.add(slotItemInfo.getSecondaryGunIds().iterator().next());
                }

                // 电探CI(主副+电探)
                if (mainGunCount > 0 && secondaryGunCount > 0 && radarCount > 0 && doObservationShooting(attackShip, BaseShipShellingSystem.ATTACK_TYPE_RADAR, aerialState, context)) {
                    attackType = BaseShipShellingSystem.ATTACK_TYPE_RADAR;
                    si.add(slotItemInfo.getMainGunIds().iterator().next());
                    si.add(slotItemInfo.getSecondaryGunIds().iterator().next());
                    si.add(slotItemInfo.getRadarIds().iterator().next());
                }

                // 撤甲弹CI(主副+撤甲)
                if (mainGunCount > 0 && secondaryGunCount > 0 && apAmmoCount > 0 && doObservationShooting(attackShip, BaseShipShellingSystem.ATTACK_TYPE_EXPOSEARMOR, aerialState, context)) {
                    attackType = BaseShipShellingSystem.ATTACK_TYPE_EXPOSEARMOR;
                    si.add(slotItemInfo.getMainGunIds().iterator().next());
                    si.add(slotItemInfo.getSecondaryGunIds().iterator().next());
                    si.add(slotItemInfo.getApAmmoIds().iterator().next());
                }
            }
        }

        // 普通攻击
        if (attackType == BaseShipShellingSystem.ATTACK_TYPE_NORMAL && !attackShip.getSlotItems().isEmpty()) {
            si.add(attackShip.getSlotItems().iterator().next().getSlotItemId());
        }

        at_type_list.add(attackType);
        si_list.add(si);
        return attackType;
    }

    private boolean canObservationShootingDecideByAerialState(final int aerialState) {
        switch (aerialState) {
            case AerialBattleSystem.AIR_BATTLE_GUARANTEE:
            case AerialBattleSystem.AIR_BATTLE_ADVANTAGE:
                return true;
            default:
                return false;
        }
    }

    private boolean canObservationShootingDecideBySlotItem(final SlotItemInfo slotItemInfo) {
        return slotItemInfo.getSearchPlaneCount() > 0;
    }

    private boolean doObservationShooting(final A attackShip, final int attackType, final int aerialState, final BattleContext context) {
        double attackTypeAugmenting = 0d;
        switch (attackType) {
            case BaseShipShellingSystem.ATTACK_TYPE_DOUBLE:
                attackTypeAugmenting = 0.3d;
            default:
                attackTypeAugmenting = 0d;
        }
        return false;
    }

    protected abstract int[] addToCriticalList(A attackShip, int attackType, D defendShip, BattleContext context);

    private void addToDefendList(final D defendShip, final int attackType, final BattleContext context) {
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
