package com.kancolle.server.service.battle.shelling.impl;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.google.common.primitives.Longs;
import com.kancolle.server.model.kcsapi.battle.houku.KouKuResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.battle.SlotItemInfo;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.service.battle.aerial.AerialUtils;
import com.kancolle.server.service.battle.course.CourseEnum;
import com.kancolle.server.utils.CollectionsUtils;
import com.kancolle.server.utils.logic.battle.BattleContextUtils;
import com.kancolle.server.utils.logic.ship.ShipFilter;
import com.kancolle.server.utils.logic.ship.ShipUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Iterables.getLast;
import static com.google.common.collect.Iterables.isEmpty;

@Service
public class MemberShipShellingSystem extends BaseShipShellingSystem<MemberShip, EnemyShip> {

    @Autowired
    private BaseShipShellingSystem<EnemyShip, MemberShip> enemyShipShellingSystem;

    @Override
    public void generateHougkeResult(MemberShip attackShip, BattleContext context) {
        List<EnemyShip> enemySSShips = context.getEnemySSShips();
        List<EnemyShip> enemyOtherShips = context.getEnemyOtherShips();
        if (isEmpty(enemySSShips) && isEmpty(enemyOtherShips)) {
            return;
        }
        generateAttackList(attackShip, context);

        EnemyShip defendShip;
        if (isTaisenAttack(attackShip, enemySSShips)) {
            generateTaiSenAttackList(context, attackShip);
            defendShip = generateDefendList(enemySSShips, context);
            generateTaiSenDamageList(attackShip, defendShip, context);
            if (ShipFilter.isAlive.negate().test(defendShip)) {
                enemySSShips.remove(defendShip);
            }
        } else {
            generateShellingAttackTypeList(attackShip, context);
            defendShip = generateDefendList(enemyOtherShips, context);
            //generateSlotItemList(attackShip, context);
            //generateCrticalList(attackShip, defendShip, context);
            generateDamageList(attackShip, defendShip, context);
            if (ShipFilter.isAlive.negate().test(defendShip)) {
                enemyOtherShips.remove(defendShip);
            }
        }
    }

    private boolean isTaisenAttack(IShip attackShip, List<? extends IShip> enemySSShips) {
        return !enemySSShips.isEmpty() && ShipFilter.antiSSShipFilter.test(attackShip);
    }

    @Override
    public void generateAttackList(MemberShip attackShip, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        ImmutableBiMap<Integer, IShip> shipsMap = context.getShipMap();
        hougekiResult.getApi_at_list().add(shipsMap.inverse().get(attackShip));
    }

    @Override
    public EnemyShip generateDefendList(List<EnemyShip> enemySSShips, BattleContext context) {
        EnemyShip defendShip = CollectionsUtils.randomGet(enemySSShips);
        HougekiResult hougekiResult = context.getNowHougekiResult();

        int attackType = BattleContextUtils.getCurrentAttackType(context);
        ImmutableBiMap<Integer, IShip> shipsMap = context.getShipMap();

        int defShipIdx = shipsMap.inverse().get(defendShip).intValue();

        int[] defArr = attackType == ATTACK_TYPE_DOUBLE ? new int[]{defShipIdx, defShipIdx} : new int[]{defShipIdx};
        hougekiResult.getApi_df_list().add(defArr);
        return defendShip;
    }

    /**
     * 影响舰船命中率包含：
     * 1.舰船等级
     * 2.装备
     * 3.幸运值
     * <p>
     * 不考虑阵型加成
     *
     * @param ship
     * @return
     */
    private double shipHitRatios(MemberShip ship) {
        int nowLv = ship.getLv();
        double levelRatios = IntMath.sqrt(--nowLv, RoundingMode.DOWN) * HIT_LEVEL_AUGMENTING;

        int lucky = ship.getNowLuck();
        double luckyRatios = lucky * HIT_LUCK_AUGMENTING;

        // TODO cacheValue
        int slotHoum = ship.getSlot().stream().mapToInt(MemberSlotItem::getHoum).sum();
        double slotRatios = slotHoum * HIT_SLOT_AUGMENTING;

        return HIT_BASE_RADIOS + levelRatios + luckyRatios + slotRatios;
    }

    @Override
    protected double combineKaihiRatio(MemberShip ship, BattleContext context) {

        int shipKaihi = ship.getShipKaihi();

        int cond = ship.getCond();
        double condAugmenting = kaihiCondAugmenting(cond);

        int courseIdx = BattleContextUtils.getBattleCourse(context);
        double courseAugmenting = CourseEnum.shelllingHougAugment(courseIdx);

        return houkThreshold(shipKaihi * condAugmenting) * courseAugmenting;
    }

    @Override
    protected final double combineHitRatio(MemberShip ship, BattleContext context) {
        int shipCond = ship.getCond();

        double shipHitRadio = shipHitRatios(ship);
        double condAugmenting = hitCondAugmenting(shipCond);

        return shipHitRadio * condAugmenting;
    }

    private double hitCondAugmenting(int cond) {
        if (cond < MemberShip.BAD_COND) {
            return 0.5d;
        } else if (cond < MemberShip.WARN_COND) {
            return 0.75d;
        } else {
            return 1d;
        }
    }

    private double kaihiCondAugmenting(int cond) {
        if (cond < MemberShip.BAD_COND) {
            return 0.5d;
        } else if (cond < MemberShip.WARN_COND) {
            return 0.75d;
        } else if (cond < MemberShip.GOOD_COND) {
            return 1d;
        } else {
            return 1.8d;
        }
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
    public void generateShellingAttackTypeList(MemberShip attackShip, BattleContext context) {
        HougekiResult nowHougekiResult = context.getNowHougekiResult();

        KouKuResult kouKuResult = context.getBattleResult().getApi_kouku();
        //TODO cache
        SlotItemInfo slotItemInfo = SlotItemInfo.of(attackShip);

        if (!AerialUtils.testAerialAdvence(kouKuResult) || ShipUtils.isBadlyDmg.test(attackShip) || slotItemInfo.getSearchPlaneCount() < 1) {
            generateNormalShellingAttackTypeAndSlotItemList(slotItemInfo, nowHougekiResult);
            return;
        }

        LinkedList<Integer> at_type_list = nowHougekiResult.getApi_at_type();
        LinkedList<Object> si_list = nowHougekiResult.getApi_si_list();

        int mainGunCount = slotItemInfo.getMainGunCount();
        int secondaryGunCount = slotItemInfo.getSecondaryGunCount();
        int radarCount = slotItemInfo.getRadarCount();
        int apAmmoCount = slotItemInfo.getAPAmmoCount();

        // 主炮CI(主主+撤甲)
        if (mainGunCount > 1 && apAmmoCount > 0) {
            at_type_list.add(ATTACK_TYPE_MAIN);
            si_list.add(slotItemInfo.getMainGunIds()[0]);
            si_list.add(slotItemInfo.getMainGunIds()[1]);
            si_list.add(slotItemInfo.getApAmmoIds()[0]);
            return;
        }

        // 连击(主主)
        if (mainGunCount > 1) {
            at_type_list.add(ATTACK_TYPE_DOUBLE);
            si_list.add(Longs.asList(slotItemInfo.getMainGunIds()));
            return;
        }

        // 主副CI(主副)
        if (mainGunCount > 0 && secondaryGunCount > 0) {
            at_type_list.add(ATTACK_TYPE_SECONDARY);
            si_list.add(slotItemInfo.getMainGunIds()[0]);
            si_list.add(slotItemInfo.getSecondaryGunIds()[0]);
            return;
        }

        // 电探CI(主副+电探)
        if (mainGunCount > 0 && secondaryGunCount > 0 && radarCount > 0) {
            at_type_list.add(ATTACK_TYPE_RADAR);
            si_list.add(slotItemInfo.getMainGunIds()[0]);
            si_list.add(slotItemInfo.getSecondaryGunIds()[0]);
            si_list.add(slotItemInfo.getRadarIds()[0]);
            return;
        }

        // 撤甲弹CI(主副+撤甲)
        if (mainGunCount > 0 && secondaryGunCount > 0 && apAmmoCount > 0) {
            at_type_list.add(ATTACK_TYPE_EXPOSEARMOR);
            si_list.add(slotItemInfo.getMainGunIds()[0]);
            si_list.add(slotItemInfo.getSecondaryGunIds()[0]);
            si_list.add(slotItemInfo.getApAmmoIds()[0]);
            return;
        }

        generateNormalShellingAttackTypeAndSlotItemList(slotItemInfo, nowHougekiResult);
    }

    private void generateNormalShellingAttackTypeAndSlotItemList(SlotItemInfo info, HougekiResult hougekiResult) {
        LinkedList<Integer> at_type_list = hougekiResult.getApi_at_type();
        LinkedList<Object> si_list = hougekiResult.getApi_si_list();

        at_type_list.add(ATTACK_TYPE_NORMAL);
        if (info.getMainGunCount() > 0) {
            si_list.add(info.getMainGunIds()[0]);
        } else if (info.getSecondaryGunCount() > 0) {
            si_list.add(info.getSecondaryGunIds()[0]);
        } else {
            si_list.add(Collections.singletonList(-1));
        }
    }

    @Override
    public void generateSlotItemList(MemberShip ship, BattleContext context) {
    }

    @Override
    public void generateCrticalList(MemberShip attackShip, EnemyShip defShip, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        int attackType = BattleContextUtils.getCurrentAttackType(context);

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
        int attackType = BattleContextUtils.getCurrentAttackType(context);
        int damageSum = 0;
        int[] clArray;

        switch (attackType) {
            case ATTACK_TYPE_NORMAL:
            case ATTACK_TYPE_EXPOSEARMOR:
            case ATTACK_TYPE_MAIN:
            case ATTACK_TYPE_RADAR:
            case ATTACK_TYPE_SECONDARY:
                boolean hit = isHit(combineHitRatio(attackShip, context), enemyShipShellingSystem.combineKaihiRatio(defendShip, context));
                if (!hit)
                    clArray = CL_SINGLE_MISS;
                else
                    clArray = RandomUtils.nextInt(0, 9) > 1 ? CL_SINGLE_HIT : CL_SINGLE_CRTICAL;
                hougekiResult.getApi_cl_list().add(clArray);

                int hougAfterThreshold = attackValue(attackShip, defendShip, context);
                int damageValue = damageValue(hougAfterThreshold, defendShip, false);
                hougekiResult.getApi_damage().add(new int[]{damageValue});
                damageSum += damageValue;
                break;
            case ATTACK_TYPE_DOUBLE:
                break;
            default:
                throw new IllegalArgumentException("attack type error");
        }

        Map<MemberShip, Integer> damageSumMap = context.getDamageSum();

        Integer memberShipDamageSum = damageSumMap.getOrDefault(attackShip, Integer.valueOf(0));
        memberShipDamageSum += damageSum;
        damageSumMap.put(attackShip, memberShipDamageSum);

        int nowHp = defendShip.getNowHp() - damageSum;
        if (nowHp < 0) {
            nowHp = 0;
        }
        defendShip.setNowHp(nowHp);
    }

    private void generateTaiSenDamageList(MemberShip attackShip, EnemyShip defendShip, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        int damageSum = 0;
        int[] clArray;

        boolean hit = isHit(combineHitRatio(attackShip, context), enemyShipShellingSystem.combineKaihiRatio(defendShip, context));
        if (!hit) {
            clArray = CL_SINGLE_MISS;
        } else {
            clArray = RandomUtils.nextInt(0, 9) > 1 ? CL_SINGLE_HIT : CL_SINGLE_CRTICAL;
        }
        hougekiResult.getApi_cl_list().add(clArray);

        int hougAfterThreshold = taiSenValue(attackShip, defendShip, context);
        int damageValue = damageValue(hougAfterThreshold, defendShip, false);
        hougekiResult.getApi_damage().add(new int[]{damageValue});
        damageSum += damageValue;

        Map<MemberShip, Integer> damageSumMap = context.getDamageSum();

        Integer memberShipDamageSum = damageSumMap.getOrDefault(attackShip, Integer.valueOf(0));
        memberShipDamageSum += damageSum;
        damageSumMap.put(attackShip, memberShipDamageSum);

        int nowHp = defendShip.getNowHp() - damageSum;
        if (nowHp < 0) {
            nowHp = 0;
        }
        defendShip.setNowHp(nowHp);

    }

    /**
     * 基本攻擊力計算：
     * <p>
     * 非航母攻擊：（對地攻擊除外）
     * 基本攻撃力 = 火力 + 改修補正(砲擊) + 聯合艦隊補正(註) + 5
     * <p>
     * 航母攻擊：
     * 基本攻撃力 = [55 +(火力 + [爆装×1.3]+ 雷装 + 改修補正(砲擊) + 聯合艦隊補正(註) )×1.5]
     * <p>
     * 以上公式中中括號需要向下取整
     * <p>
     * 只要航母能攻擊（不罰站），攻撃力便跟搭載數無關
     * 即使某一格攻擊機數目為0，也不會影響砲擊戰攻擊力（該艦攻艦爆的爆裝和雷裝仍會被計算）
     * <p>
     * 註：
     * 對於陸上敵人，計算傷害時雷裝無效（即一律當成0雷裝來計算）
     *
     * @param ship
     * @return
     */
    private int shellingBaiscHoug(IShip ship) {
        //TODO 联合舰队基本攻击力补正
        //TODO 修改装备攻击力补正
        int shipHoug = ship.getShipKaryoku();

        if (ShipFilter.carrierFilter.test(ship)) {
            int raiSou = ship.getShipRaisou();
            int baKu = ShipUtils.getBakuValue(ship);
            return (shipHoug + raiSou + baKu * 13 / 10) * 3 / 2 + 55;
        } else {
            return shipHoug + 5;
        }
    }

    /**
     * 基本攻撃力 = 2 x √(艦娘婐裝對潛) + 裝備對潛 × 1.5 + 改修補正(對潛) + 攻撃模式補正
     * 攻撃模式補正：艦載機對潛系數為8，深水炸彈對潛系數為13
     * <p>
     * 以下類型的裝備在計算反潛攻擊力時其對潛值將會看成是裝備對潛來計算：
     * 水聽、爆雷、水上爆撃機、艦上攻撃機、艦上爆撃機、對潜哨戒機、カ号観測機
     * <p>
     * 以下類型的裝備對反潛攻擊力沒有任何影響：
     * 水上偵察機、電探、小口徑主砲
     *
     * @param ship
     * @return
     */
    private int taiSenBasicHoug(IShip ship, int attackAugmenting) {
        //TODO 联合舰队基本攻击力补正
        //TODO 修改装备攻击路补正
        int shipTaisen = 2 * IntMath.sqrt(ship.getShipTaiSen(), RoundingMode.CEILING);
        int slotTaisen = DoubleMath.roundToInt(1.5d * ship.getSlotItems().stream().mapToInt(AbstractSlotItem::getTaiSen).sum(), RoundingMode.CEILING);
        return shipTaisen + slotTaisen + attackAugmenting;
    }

    private int attackValue(MemberShip attackShip, EnemyShip defendShip, BattleContext context) {
        int hougBeforeThreshold = daylightHougThreshold(augmentingBeforeThreshold(attackShip, context));
        double augmentingAfterThreshold = augmentingAfterThreshold(attackShip, context);
        return DoubleMath.roundToInt(hougBeforeThreshold * augmentingAfterThreshold, RoundingMode.DOWN);
    }

    private int taiSenValue(MemberShip attackShip, EnemyShip defendShip, BattleContext context) {
        int hougBeforeThreshold = taiSenHougThreshold(taiSenAugmentingBeforeThreshold(attackShip, context));
        double augmentingAfterThreshold = augmentingAfterThreshold(attackShip, context);
        return DoubleMath.roundToInt(hougBeforeThreshold * augmentingAfterThreshold, RoundingMode.DOWN);
    }

    /**
     * 阈值前攻击力 =  [ { 基本攻撃力 × 閾值前補正 + 輕巡適型砲補正 } ]
     * <p>
     * {　}里的部份所計算出來的數值會受到閾值影響，具體閾值為：
     * 晝戰（不計算反潛戰）：150
     * 反潛戰：100
     * 夜戰（非反潛戰）：300
     * <p>
     * 若果{　}里的數值超出了當前閾值，則按以下算式處理：
     * 補正後攻撃力（{　}部份） = 閾值 + √(補正前攻撃力 - 閾值)
     * 括號 [ ] 內的數值需要向下取整
     * <p>
     * 阈值前补正包括：
     * 1.阵型补正
     * 2.航向补正
     * 3.损伤补正
     * 4.反潜相乘补正
     * <p>
     * 这里补正数值属于炮击战
     */
    public double augmentingBeforeThreshold(IShip attackShip, BattleContext context) {
        int basicHoug = shellingBaiscHoug(attackShip);
        double augmenting = basicAugmentBeforeThreshold(attackShip, context);

        if (augmenting < 0d) {
            augmenting = 0d;
        }

        // 阈值前攻击力 = 基本攻击力 * 阈值前攻击补正参数 + 轻巡炮攻击力补正
        return augmenting * basicHoug + cLGunAugmenting(attackShip);
    }

    public double taiSenAugmentingBeforeThreshold(IShip attackShip, BattleContext context) {
        int taisenBasicHoug = taiSenBasicHoug(attackShip, DEPTH_CHARGE_AUGMENTING);
        double augmenting = basicAugmentBeforeThreshold(attackShip, context);

        //反潜套补正
        double taisenAugmenting = taisenShellingAugmenting(attackShip);
        augmenting += taisenAugmenting;

        if (augmenting < 0d) {
            augmenting = 0d;
        }

        return augmenting * taisenBasicHoug;
    }

    /**
     * 阈值后补正 = 徹甲彈特效補正×暴擊補正×彈着觀測射撃補正
     *
     * @return
     */
    public double augmentingAfterThreshold(MemberShip attackShip, BattleContext context) {
        double augmenting = 1d;

        HougekiResult hougekiResult = context.getNowHougekiResult();
        int attackType = BattleContextUtils.getCurrentAttackType(context);

        SlotItemInfo slotInfo = SlotItemInfo.of(attackShip);
        if (slotInfo.getAPAmmoCount() > 0) {
            augmenting *= APAMMO_AUGMENTING;
        }

        //暴擊補正
        int[] clArray = (int[]) getLast(hougekiResult.getApi_cl_list());
        // TODO double attack
        boolean isCrtical = clArray[0] == CL_VALUE_CRTICAL;
        if (isCrtical) {
            augmenting *= SHELLING_CRTICAL_AUGMENTING;
        }

        //TODO 熟練艦載機暴擊額外補正
        //彈着觀測射撃補正
        switch (attackType) {
            case ATTACK_TYPE_MAIN:
                augmenting *= 1.5d;
                break;
            case ATTACK_TYPE_EXPOSEARMOR:
                augmenting *= 1.3d;
                break;
            case ATTACK_TYPE_DOUBLE:
            case ATTACK_TYPE_RADAR:
                augmenting *= 1.2d;
                break;
            case ATTACK_TYPE_SECONDARY:
                augmenting *= 1.1d;
                break;
            case ATTACK_TYPE_NORMAL:
            default:
        }

        return augmenting;
    }
}
