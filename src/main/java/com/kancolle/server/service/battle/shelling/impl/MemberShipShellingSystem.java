package com.kancolle.server.service.battle.shelling.impl;

import static com.google.common.collect.Iterables.getLast;

import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.math.IntMath;
import com.google.common.primitives.Longs;
import com.kancolle.server.model.kcsapi.battle.houku.KouKuResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.battle.SlotItemInfo;
import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.service.battle.aerial.AerialUtils;
import com.kancolle.server.utils.CollectionsUtils;
import com.kancolle.server.utils.logic.ship.ShipFilter;
import com.kancolle.server.utils.logic.ship.ShipUtils;

@Service
public class MemberShipShellingSystem extends AbstractShipShellingSystem<MemberShip, EnemyShip> {

    @Override
    public void generateHougkeResult(MemberShip attackShip, BattleContext context) {
        generateAttackList(attackShip, context);
        List<EnemyShip> enemySSShips = context.getEnemySSShips();
        List<EnemyShip> enemyOtherShips = context.getEnemyOtherShips();

        if (isTaisenAttack(attackShip, enemySSShips)) {
            generateShellingAttackTypeList(attackShip, context);
            EnemyShip defShip = generateDefendList(enemySSShips, context);
        } else {
            generateShellingAttackTypeList(attackShip, context);
            EnemyShip defendShip = generateDefendList(enemyOtherShips, context);
            //generateSlotItemList(attackShip, context);
            //generateCrticalList(attackShip, defendShip, context);
            generateDamageList(attackShip, defendShip, context);
        }
    }

    private boolean isTaisenAttack(AbstractShip attackShip, List<? extends AbstractShip> enemySSShips) {
        return !enemySSShips.isEmpty() && ShipFilter.antiSSShipFilter.test(attackShip);
    }

    @Override
    public void generateAttackList(MemberShip attackShip, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        ImmutableBiMap<Integer, AbstractShip> shipsMap = context.getShipMap();
        hougekiResult.getApi_at_list().add(shipsMap.inverse().get(attackShip).intValue());
    }

    @Override
    public EnemyShip generateDefendList(List<EnemyShip> enemySSShips, BattleContext context) {
        EnemyShip defendShip = CollectionsUtils.randomGet(enemySSShips);
        HougekiResult hougekiResult = context.getNowHougekiResult();

        int attackType = hougekiResult.getApi_at_type().getLast().intValue();
        ImmutableBiMap<Integer, AbstractShip> shipsMap = context.getShipMap();

        int defShipIdx = shipsMap.inverse().get(defendShip).intValue();

        int[] defArr = attackType == ATTACK_TYPE_DOUBLE ? new int[] { defShipIdx, defShipIdx } : new int[] { defShipIdx };
        hougekiResult.getApi_df_list().add(defArr);
        return defendShip;
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
    public void generateAttackTypeList(MemberShip attackShip, BattleContext context) {
        List<EnemyShip> enemySSShips = context.getEnemySSShips();
        if (isTaisenAttack(attackShip, enemySSShips))
            generateTaiSenAttackList(context);
        else
            generateShellingAttackTypeList(attackShip, context);
    }

    /*
     * 彈著觀測射擊：
     * 
     * 發動條件：
     * 1.必須有觸發航空戰，且我方取得制空優勢或制空權確保下才會有機會發動
     * 2.艦娘須裝備上水偵或水爆，且水偵或水爆數量必須大於1才有機會發動
     * 3.大破艦娘不能發動彈著觀測射擊
     * 4.滿足上述發動配置的裝備數量皆可發動，當裝備滿足複數類型的特殊攻擊時，會機率性的發動其中一樣
     * 若彈著觀測射擊未發動成功，則會進行通常砲擊
     * 
     */
    private void generateShellingAttackTypeList(MemberShip attackShip, BattleContext context) {
        LinkedList<Integer> at_type_list = context.getNowHougekiResult().getApi_at_type();
        LinkedList<Object> si_list = context.getNowHougekiResult().getApi_si_list();

        KouKuResult kouKuResult = context.getBattleResult().getApi_kouku();
        SlotItemInfo info = SlotItemInfo.of(attackShip);

        if (!AerialUtils.testAerialAdvence(kouKuResult) || ShipUtils.isBadlyDmg.test(attackShip) || info.getSearchPlaneCount() == 0) {
            generateNormalShellingAttackTypeAndSlotItemList(info, context);
            return;
        }

        int mainGunCount = info.getMainGunCount();
        int secondaryGunCount = info.getSecondaryGunCount();

        // 连击(主主)
        if (mainGunCount > 1) {
            at_type_list.add(ATTACK_TYPE_DOUBLE);
            si_list.add(Longs.asList(info.getMainGunId()).subList(0, 2));
            return;
        }

        // 主副CI(主副)
        if (mainGunCount > 0 && secondaryGunCount > 0) {
            at_type_list.add(ATTACK_TYPE_SECONDARY);
            si_list.add(Longs.asList(info.getMainGunId()[0], info.getSecondaryGunId()[0]));
            return;
        }

        // 电探CI(主副+电探)
        if (mainGunCount == 1 && secondaryGunCount == 1 && info.getRadarCount() == 1) {
            at_type_list.add(ATTACK_TYPE_RADAR);
            si_list.add(Longs.asList(info.getMainGunId()[0], info.getSecondaryGunId()[0], info.getRadarId()[0]));
            return;
        }

        // 撤甲弹CI(主副+撤甲)
        if (mainGunCount == 1 && secondaryGunCount == 1 && info.getAPAmmoCount() == 1) {
            at_type_list.add(ATTACK_TYPE_EXPOSEARMOR);
            si_list.add(Longs.asList(info.getMainGunId()[0], info.getSecondaryGunId()[0], info.getApAmmoId()[0]));
            return;
        }

        // 主炮CI(主主+撤甲)
        if (mainGunCount == 2 && info.getAPAmmoCount() == 1) {
            at_type_list.add(ATTACK_TYPE_MAIN);
            si_list.add(Longs.asList(info.getMainGunId()[0], info.getMainGunId()[1], info.getApAmmoId()[0]));
            return;
        }

        generateNormalShellingAttackTypeAndSlotItemList(info, context);
    }

    private void generateNormalShellingAttackTypeAndSlotItemList(SlotItemInfo info, BattleContext context) {
        LinkedList<Integer> at_type_list = context.getNowHougekiResult().getApi_at_type();
        LinkedList<Object> si_list = context.getNowHougekiResult().getApi_si_list();

        at_type_list.add(ATTACK_TYPE_NORMAL);
        if (info.getMainGunCount() > 0) {
            si_list.add(info.getMainGunId()[0]);
            return;
        }
        if (info.getSecondaryGunCount() > 0) {
            si_list.add(info.getSecondaryGunId()[0]);
            return;
        }
        si_list.add(Collections.singleton(-1));
    }

    @Override
    public void generateSlotItemList(MemberShip ship, BattleContext context) {
    }

    @Override
    public void generateCrticalList(MemberShip attackShip, EnemyShip defShip, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        int attackType = getLast(hougekiResult.getApi_at_type()).intValue();

        int[] clArray = null;

        switch (attackType) {
        case ATTACK_TYPE_NORMAL:

            break;
        case ATTACK_TYPE_DOUBLE:
            break;
        case ATTACK_TYPE_EXPOSEARMOR:
            break;
        case ATTACK_TYPE_MAIN:
            break;
        case ATTACK_TYPE_RADAR:
            break;
        case ATTACK_TYPE_SECONDARY:
            break;
        default:
            throw new IllegalArgumentException("attack type error");
        }
        hougekiResult.getApi_cl_list().add(clArray);
    }

    @Override
    public void generateDamageList(MemberShip attackShip, EnemyShip defendShip, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        int attackType = getLast(hougekiResult.getApi_at_type()).intValue();

        int[] clArray;

        switch (attackType) {
        case ATTACK_TYPE_NORMAL:
            boolean hit = isHit(hitRatios(attackShip), enemyShipShellingKaihi(defendShip));
            if (!hit)
                clArray = CL_SINGLE_MISS;
            else
                clArray = RandomUtils.nextInt(0, 9) > 1 ? CL_SINGLE_HIT : CL_SINGLE_CRTICAL;
            hougekiResult.getApi_cl_list().add(clArray);

            int attackValue = daylightHougThreshold(attackShip.getShipKaryoku());
            int damageValue = damageValue(attackValue, defendShip, false);
            context.getNowHougekiResult().getApi_damage().add(new int[] { damageValue });
            break;
        case ATTACK_TYPE_DOUBLE:
            break;
        case ATTACK_TYPE_EXPOSEARMOR:
            boolean ci_hit = isCIHit(hitRatios(attackShip), enemyShipShellingKaihi(defendShip));
            break;
        case ATTACK_TYPE_MAIN:
            break;
        case ATTACK_TYPE_RADAR:
            break;
        case ATTACK_TYPE_SECONDARY:
            break;
        default:
            throw new IllegalArgumentException("attack type error");
        }
    }
}
