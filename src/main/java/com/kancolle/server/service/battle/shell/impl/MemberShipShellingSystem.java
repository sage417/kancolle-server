package com.kancolle.server.service.battle.shell.impl;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.math.IntMath;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.service.battle.impl.AerialBattleSystemImpl;
import com.kancolle.server.utils.CollectionsUtils;
import com.kancolle.server.utils.logic.ship.ShipFilter;

@Service
public class MemberShipShellingSystem extends AbstractShipShellingSystem<MemberShip, EnemyShip> {

    public void generateHougkeResult(BattleSimulationResult result, int aerialState, MemberShip attackShip, ImmutableBiMap<Integer, EnemyShip> enemyOtherShipsMap) {

        HougekiResult hougekiResult = result.getApi_hougeki1();

        EnemyShip defEnemyShip = CollectionsUtils.randomGet(enemyOtherShipsMap.values().asList());
        int defShipIdx = enemyOtherShipsMap.inverse().get(defEnemyShip);

        // 制空优势以上可以发动二连，主副观测，电碳ci等特殊攻击
        if (aerialState == AerialBattleSystemImpl.AIR_BATTLE_GUARANTEE) {

        } else if (aerialState == AerialBattleSystemImpl.AIR_BATTLE_ADVANTAGE) {

        }
        int attackType = 0;
        hougekiResult.getApi_at_type().add(attackType);
        int[] damages = ArrayUtils.EMPTY_INT_ARRAY;

        int shipHougke = attackShip.getKaryoku().getMinValue();

        if (attackType == ATTACK_TYPE_DOUBLE) {
            int fdamage = shipHougke * 6 / 5;
            int sdamage = shipHougke * 6 / 5;
            // 2次1.2倍率伤害
            damages = new int[] { fdamage, sdamage };
            hougekiResult.getApi_df_list().add(new int[] { defShipIdx, defShipIdx });
        } else {
            hougekiResult.getApi_df_list().add(new int[] { defShipIdx });
        }

        switch (attackType) {
        case ATTACK_TYPE_NORMAL:
            damages = new int[] { shipHougke };
            break;
        case ATTACK_TYPE_SECONDARY:
            damages = new int[] { shipHougke * 11 / 10 };
            break;
        case ATTACK_TYPE_RADAR:
            damages = new int[] { shipHougke * 6 / 5 };
            break;
        case ATTACK_TYPE_EXPOSEARMOR:
            damages = new int[] { shipHougke * 7 / 5 };
            break;
        case ATTACK_TYPE_MAIN:
            damages = new int[] { shipHougke * 3 / 2 };
            break;
        default:
            break;
        }

        if (RandomUtils.nextDouble(0, 1) < 0.15d) {
            damages = Arrays.stream(damages).map(damage -> damage * 3 / 2).toArray();
        }

        hougekiResult.getApi_damage().add(damages);
        hougekiResult.getApi_si_list().add(new int[] { 1, 2 });
        hougekiResult.getApi_cl_list().add(new int[] { 1, 1 });

    }

    @Override
    public void generateHougkeResult(MemberShip attackShip, BattleContext context) {
        generateAttackList(attackShip, context);
        List<EnemyShip> enemySSShips = context.getEnemySSShips();
        List<EnemyShip> enemyOtherShips = context.getEnemyOtherShips();

        if (testTaisen(attackShip, enemySSShips)) {
            generateAttackTypeList(ATTACK_TYPE_ANTISUBMARINE, context);
            generateDefendList(enemySSShips, context);
        } else {
            generateAttackTypeList(ATTACK_TYPE_NORMAL, context);
            generateDefendList(enemyOtherShips, context);
            //TODO
            context.getNowHougekiResult().getApi_si_list().add(new int[] { 1, 2 });
            context.getNowHougekiResult().getApi_cl_list().add(new int[] { 1, 1 });
            generateDamageList(attackShip, context);
        }
    }

    private boolean testTaisen(AbstractShip attackShip, List<? extends AbstractShip> enemySSShips) {
        return !enemySSShips.isEmpty() && ShipFilter.antiSSShipFilter.test(attackShip);
    }

    @Override
    public void generateAttackList(MemberShip attackShip, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        ImmutableBiMap<Integer, AbstractShip> shipsMap = context.getShipMap();
        hougekiResult.getApi_at_list().add(shipsMap.inverse().get(attackShip).intValue());
    }

    @Override
    public void generateDefendList(List<EnemyShip> enemySSShips, BattleContext context) {
        EnemyShip defendShip = CollectionsUtils.randomGet(enemySSShips);
        HougekiResult hougekiResult = context.getNowHougekiResult();

        int attackType = hougekiResult.getApi_at_type().getLast().intValue();
        ImmutableBiMap<Integer, AbstractShip> shipsMap = context.getShipMap();

        int defShipIdx = shipsMap.inverse().get(defendShip).intValue();

        int[] defArr = attackType == ATTACK_TYPE_DOUBLE ? new int[] { defShipIdx, defShipIdx } : new int[] { defShipIdx };
        hougekiResult.getApi_df_list().add(defArr);
    }

    @Override
    protected int hitRatios(MemberShip ship) {
        int nowLv = ship.getLv();
        int lucky = ship.getNowLuck();
        int slotHoum = ship.getSlot().stream().mapToInt(MemberSlotItem::getHoum).sum();
        int hitValue = 95 + 2 * IntMath.sqrt(nowLv - 1, RoundingMode.DOWN) + slotHoum + 3 * lucky / 20;
        int cond = ship.getCond();
        if (cond < 30) {
            return hitValue / 2;
        } else if (cond < 40) {
            return hitValue * 3 / 4;
        }
        return hitValue;
    }

    @Override
    public void generateAttackTypeList(MemberShip ship, BattleContext context) {
        // TODO Auto-generated method stub

    }

    private void generateAttackTypeList(int type, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        hougekiResult.getApi_at_type().add(type);
    }

    @Override
    public void generateSlotItemList(MemberShip ship, BattleContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void generateCrticalList(MemberShip ship, BattleContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void generateDamageList(MemberShip attackShip, BattleContext context) {
        int attackValue = daylightHougThreshold(attackShip.getShipKaryoku());
        Integer defShipKey = context.getNowHougekiResult().getApi_at_list().getLast();
        AbstractShip defShip = context.getShipMap().get(defShipKey);
        int damageValue = damageValue(attackValue, defShip, false);
        //TODO double 
        context.getNowHougekiResult().getApi_damage().add(new int[] { damageValue });
    }
}
