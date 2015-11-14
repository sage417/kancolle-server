package com.kancolle.server.service.battle.shell.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableBiMap;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.EnemySlotItem;
import com.kancolle.server.service.battle.aerial.AerialBattleSystem;
import com.kancolle.server.utils.CollectionsUtils;
import com.kancolle.server.utils.logic.ship.ShipFilter;

@Service
public class EnmeyShipShellingSystem extends AbstractShipShellingSystem<EnemyShip, MemberShip> {

    public void generateHougkeResult(BattleSimulationResult result, int aerialState, MemberShip attackShip, ImmutableBiMap<Integer, EnemyShip> enemyOtherShipsMap) {

        HougekiResult hougekiResult = result.getApi_hougeki1();

        EnemyShip defEnemyShip = CollectionsUtils.randomGet(enemyOtherShipsMap.values().asList());
        int defShipIdx = enemyOtherShipsMap.inverse().get(defEnemyShip);

        // 制空优势以上可以发动二连，主副观测，电碳ci等特殊攻击
        if (aerialState == AerialBattleSystem.AIR_BATTLE_GUARANTEE) {

        } else if (aerialState == AerialBattleSystem.AIR_BATTLE_ADVANTAGE) {

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
    public void generateHougkeResult(EnemyShip attackShip, BattleContext context) {
        generateAttackList(attackShip, context);
        List<MemberShip> enemySSShips = context.getMemberSSShips();
        List<MemberShip> enemyOtherShips = context.getMemberOtherShips();

        if (testTaisen(attackShip, enemySSShips)) {
            generateDefendList(enemySSShips, context);
            generateAttackTypeList(ATTACK_TYPE_ANTISUBMARINE, context);
            generateSlotItemList(attackShip, context);
        } else {
            generateDefendList(enemyOtherShips, context);
            generateAttackTypeList(ATTACK_TYPE_NORMAL, context);
        }
    }

    private boolean testTaisen(AbstractShip attackShip, List<? extends AbstractShip> enemySSShips) {
        return !enemySSShips.isEmpty() && ShipFilter.antiSSShipFilter.test(attackShip);
    }

    @Override
    public void generateAttackList(EnemyShip attackShip, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        ImmutableBiMap<Integer, AbstractShip> shipsMap = context.getShipMap();
        hougekiResult.getApi_at_list().add(shipsMap.inverse().get(attackShip).intValue());
    }

    @Override
    public void generateDefendList(List<MemberShip> enemySSShips, BattleContext context) {
        AbstractShip defendShip = CollectionsUtils.randomGet(enemySSShips);
        HougekiResult hougekiResult = context.getNowHougekiResult();
        ImmutableBiMap<Integer, AbstractShip> shipsMap = context.getShipMap();
        hougekiResult.getApi_df_list().add(shipsMap.inverse().get(defendShip).intValue());
    }

    @Override
    protected int hitRatios(EnemyShip ship) {
        int lucky = ship.getNowLuck();
        int slotHoum = ship.getSlot().stream().mapToInt(EnemySlotItem::getHoum).sum();
        return 95 + slotHoum + 3 / 20 * lucky;
    }


    @Override
    public void generateAttackTypeList(EnemyShip ship, BattleContext context) {
        // TODO Auto-generated method stub

    }

    private void generateAttackTypeList(int type, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        hougekiResult.getApi_at_type().add(type);
    }

    @Override
    public void generateSlotItemList(EnemyShip ship, BattleContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void generateCrticalList(EnemyShip ship, BattleContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void generateDamageList(EnemyShip ship, BattleContext context) {
        // TODO Auto-generated method stub

    }
}
