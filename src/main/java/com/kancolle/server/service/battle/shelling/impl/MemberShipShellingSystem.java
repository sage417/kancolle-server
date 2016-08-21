package com.kancolle.server.service.battle.shelling.impl;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.kancolle.server.model.kcsapi.battle.houku.KouKuResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.battle.SlotItemInfo;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.UnderSeaShip;
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
public class MemberShipShellingSystem extends BaseShipShellingSystem<MemberShip, UnderSeaShip> {

    @Autowired
    private BaseShipShellingSystem<UnderSeaShip, MemberShip> enemyShipShellingSystem;

    @Override
    public void generateHougkeResult(final MemberShip attackShip, final BattleContext context) {
        prepareContext(context);

        final List<UnderSeaShip> aliveUnderSeaSSShips = context.getAliveUnderSeaSSShips();
        final List<UnderSeaShip> aliveUnderSeaNormalShips = context.getAliveUnderSeaNormalShips();
        if (isEmpty(aliveUnderSeaSSShips) && isEmpty(aliveUnderSeaNormalShips)) {
            return;
        }
        addToAttackList(attackShip, context);

        final UnderSeaShip defendShip;
        if (isTaisenAttack(attackShip, aliveUnderSeaSSShips)) {
            generateTaiSenAttackList(context, attackShip);
            defendShip = generateDefendList(aliveUnderSeaSSShips, context);
            generateTaiSenDamageList(attackShip, defendShip, context);
            if (ShipFilter.isAlive.negate().test(defendShip)) {
                aliveUnderSeaSSShips.remove(defendShip);
            }
        } else {
            generateShellingAttackTypeList(attackShip, context);
            defendShip = generateDefendList(aliveUnderSeaNormalShips, context);
            //generateSlotItemList(attackShip, context);
            //generateCrticalList(attackShip, defendShip, context);
            generateDamageList(attackShip, defendShip, context);
            if (ShipFilter.isAlive.negate().test(defendShip)) {
                aliveUnderSeaNormalShips.remove(defendShip);
            }
        }
    }

    @Override
    protected void prepareContext(final BattleContext context) {
        super.prepareContext(context);
        context.setEnemyNormalShips(context.getAliveUnderSeaNormalShips());
        context.setEnemySSShips(context.getAliveUnderSeaSSShips());
    }

    @Override
    protected void augmentingDamage(final MemberShip attackShip, final UnderSeaShip defendShip, final int[] damages, final BattleContext context) {

    }

    @Override
    protected void callbackAfterDamage(final MemberShip attackShip, final UnderSeaShip defendShip, final int[] damages, final BattleContext context) {

    }

    @Override
    protected int[] generateOnceDamageResult(final MemberShip attackShip, final UnderSeaShip defendShip, final BattleContext context) {
        return new int[0];
    }

    @Override
    protected int[] generateTwiceDamageResult(final MemberShip attackShip, final UnderSeaShip defendShip, final BattleContext context) {
        return new int[0];
    }

    private boolean isTaisenAttack(final IShip attackShip, final List<? extends IShip> enemySSShips) {
        return !enemySSShips.isEmpty() && ShipFilter.antiSSShipFilter.test(attackShip);
    }

    @Override
    public UnderSeaShip generateDefendList(final List<UnderSeaShip> underSeaSSShips, final BattleContext context) {
        final UnderSeaShip defendShip = CollectionsUtils.randomGet(underSeaSSShips);
        final HougekiResult hougekiResult = context.getNowHougekiResult();

        final int attackType = BattleContextUtils.getCurrentAttackType(context);
        final ImmutableBiMap<Integer, IShip> shipsMap = context.getShipMap();

        final int defShipIdx = shipsMap.inverse().get(defendShip);

        final int[] defArr = attackType == ATTACK_TYPE_DOUBLE ? new int[]{defShipIdx, defShipIdx} : new int[]{defShipIdx};
        hougekiResult.getApi_df_list().add(defArr);
        return defendShip;
    }

    private double shipHitRatios(final MemberShip ship, final BattleContext context) {
        int nowLv = ship.getLv();
        final double levelRatios = IntMath.sqrt(--nowLv, RoundingMode.DOWN) * HIT_LEVEL_AUGMENTING;

        final int lucky = ship.getNowLuck();
        final double luckyRatios = lucky * HIT_LUCK_AUGMENTING;

        final int slotHoum = ship.getSlot().stream().mapToInt(MemberSlotItem::getHoum).sum();
        final double slotRatios = slotHoum * HIT_SLOT_AUGMENTING;

        final int shipCond = ship.getCond();
        return HIT_BASE_RADIOS + (0.93d + levelRatios + luckyRatios + slotRatios) * getHoumFormationFactor(context) * getHoumCondFactor(shipCond);
    }

    @Override
    protected double combineKaihiRatio(final MemberShip ship, final BattleContext context) {

        final int shipKaihi = ship.getShipKaihi();

        final int cond = ship.getCond();
        final double condAugmenting = getKaihiCondFactor(cond);

        final int courseIdx = BattleContextUtils.getBattleCourse(context);
        final double courseAugmenting = CourseEnum.shelllingHougAugment(courseIdx);

        return houkThreshold(shipKaihi * condAugmenting) * courseAugmenting;
    }

    @Override
    protected final double getHoumFormationFactor(final BattleContext context) {
        final int memberFormation = BattleContextUtils.getMemberFormation(context);
        final int enemyFormation = BattleContextUtils.getUnderSeaFormation(context);
        return getHoumFormationFactor(memberFormation, enemyFormation);
    }

    private double getHoumCondFactor(final int cond) {
        if (cond < MemberShip.BAD_COND) {
            return 0.5d;
        } else if (cond >= MemberShip.GOOD_COND) {
            return 1.2d;
        } else {
            return 1d;
        }
    }

    private double getKaihiCondFactor(final int cond) {
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
    public void generateShellingAttackTypeList(final MemberShip attackShip, final BattleContext context) {
        final HougekiResult nowHougekiResult = context.getNowHougekiResult();

        final KouKuResult kouKuResult = context.getBattleResult().getApi_kouku();
        //TODO cache
        final SlotItemInfo slotItemInfo = SlotItemInfo.of(attackShip);

        if (!AerialUtils.testAerialAdvence(kouKuResult) || ShipUtils.isBadlyDmg.test(attackShip) || slotItemInfo.getSearchPlaneCount() < 1) {
            generateNormalShellingAttackTypeAndSlotItemList(slotItemInfo, nowHougekiResult);
            return;
        }

        final LinkedList<Integer> at_type_list = nowHougekiResult.getApi_at_type();
        final LinkedList<Object> si_list = nowHougekiResult.getApi_si_list();

        final int mainGunCount = slotItemInfo.getMainGunCount();
        final int secondaryGunCount = slotItemInfo.getSecondaryGunCount();
        final int radarCount = slotItemInfo.getRadarCount();
        final int apAmmoCount = slotItemInfo.getAPAmmoCount();

        // 主炮CI(主主+撤甲)
        if (mainGunCount > 1 && apAmmoCount > 0) {
            at_type_list.add(ATTACK_TYPE_MAIN);
            si_list.add(slotItemInfo.getMainGunIds().subList(0, 2));
            si_list.add(slotItemInfo.getApAmmoIds().iterator().next());
            return;
        }

        // 连击(主主)
        if (mainGunCount > 1) {
            at_type_list.add(ATTACK_TYPE_DOUBLE);
            si_list.add(slotItemInfo.getMainGunIds());
            return;
        }

        // 主副CI(主副)
        if (mainGunCount > 0 && secondaryGunCount > 0) {
            at_type_list.add(ATTACK_TYPE_SECONDARY);
            si_list.add(slotItemInfo.getMainGunIds().iterator().next());
            si_list.add(slotItemInfo.getSecondaryGunIds().iterator().next());
            return;
        }

        // 电探CI(主副+电探)
        if (mainGunCount > 0 && secondaryGunCount > 0 && radarCount > 0) {
            at_type_list.add(ATTACK_TYPE_RADAR);
            si_list.add(slotItemInfo.getMainGunIds().iterator().next());
            si_list.add(slotItemInfo.getSecondaryGunIds().iterator().next());
            si_list.add(slotItemInfo.getRadarIds().iterator().next());
            return;
        }

        // 撤甲弹CI(主副+撤甲)
        if (mainGunCount > 0 && secondaryGunCount > 0 && apAmmoCount > 0) {
            at_type_list.add(ATTACK_TYPE_EXPOSEARMOR);
            si_list.add(slotItemInfo.getMainGunIds().iterator().next());
            si_list.add(slotItemInfo.getSecondaryGunIds().iterator().next());
            si_list.add(slotItemInfo.getApAmmoIds().iterator().next());
            return;
        }

        generateNormalShellingAttackTypeAndSlotItemList(slotItemInfo, nowHougekiResult);
    }

    private void generateNormalShellingAttackTypeAndSlotItemList(final SlotItemInfo info, final HougekiResult hougekiResult) {
        final LinkedList<Integer> at_type_list = hougekiResult.getApi_at_type();
        final LinkedList<Object> si_list = hougekiResult.getApi_si_list();

        at_type_list.add(ATTACK_TYPE_NORMAL);
        if (info.getMainGunCount() > 0) {
            si_list.add(info.getMainGunIds().iterator().next());
        } else if (info.getSecondaryGunCount() > 0) {
            si_list.add(info.getSecondaryGunIds().iterator().next());
        } else {
            si_list.add(Collections.singletonList(-1));
        }
    }

    @Override
    public void generateSlotItemList(final MemberShip ship, final BattleContext context) {
    }

    @Override
    public void generateCrticalList(final MemberShip attackShip, final UnderSeaShip defShip, final BattleContext context) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();
        final int attackType = BattleContextUtils.getCurrentAttackType(context);

        final int[] clArray = null;

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
    public void generateDamageList(final MemberShip attackShip, final UnderSeaShip defendShip, final BattleContext context) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();
        final int attackType = BattleContextUtils.getCurrentAttackType(context);
        int damageSum = 0;
        final int[] clArray;

        switch (attackType) {
            case ATTACK_TYPE_NORMAL:
            case ATTACK_TYPE_EXPOSEARMOR:
            case ATTACK_TYPE_MAIN:
            case ATTACK_TYPE_RADAR:
            case ATTACK_TYPE_SECONDARY:
                final boolean hit = isHit(shipHitRatios(attackShip, context), enemyShipShellingSystem.combineKaihiRatio(defendShip, context));
                if (!hit)
                    clArray = CL_SINGLE_MISS;
                else
                    clArray = RandomUtils.nextInt(0, 9) > 1 ? CL_SINGLE_HIT : CL_SINGLE_CRTICAL;
                hougekiResult.getApi_cl_list().add(clArray);

                final int hougAfterThreshold = attackValue(attackShip, defendShip, context);
                final int damageValue = damageValue(hougAfterThreshold, defendShip, false);
                hougekiResult.getApi_damage().add(new int[]{damageValue});
                damageSum += damageValue;
                break;
            case ATTACK_TYPE_DOUBLE:
                break;
            default:
                throw new IllegalArgumentException("attack type error");
        }

        final Map<MemberShip, Integer> damageSumMap = context.getDamageSum();

        Integer memberShipDamageSum = damageSumMap.getOrDefault(attackShip, 0);
        memberShipDamageSum += damageSum;
        damageSumMap.put(attackShip, memberShipDamageSum);

        int nowHp = defendShip.getNowHp() - damageSum;
        if (nowHp < 0) {
            nowHp = 0;
        }
        defendShip.setNowHp(nowHp);
    }

    private void generateTaiSenDamageList(final MemberShip attackShip, final UnderSeaShip defendShip, final BattleContext context) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();
        int damageSum = 0;
        final int[] clArray;

        final boolean hit = isHit(shipHitRatios(attackShip, context), enemyShipShellingSystem.combineKaihiRatio(defendShip, context));
        if (!hit) {
            clArray = CL_SINGLE_MISS;
        } else {
            clArray = RandomUtils.nextInt(0, 9) > 1 ? CL_SINGLE_HIT : CL_SINGLE_CRTICAL;
        }
        hougekiResult.getApi_cl_list().add(clArray);

        final int hougAfterThreshold = taiSenValue(attackShip, defendShip, context);
        final int damageValue = damageValue(hougAfterThreshold, defendShip, false);
        hougekiResult.getApi_damage().add(new int[]{damageValue});
        damageSum += damageValue;

        final Map<MemberShip, Integer> damageSumMap = context.getDamageSum();

        Integer memberShipDamageSum = damageSumMap.getOrDefault(attackShip, 0);
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
    private int shellingBaiscHoug(final IShip ship) {
        //TODO 联合舰队基本攻击力补正
        //TODO 修改装备攻击力补正
        final int shipHoug = ship.getShipKaryoku();

        if (ShipFilter.carrierFilter.test(ship)) {
            final int raiSou = ship.getShipRaisou();
            final int baKu = ShipUtils.getBakuValue(ship);
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
    private int taiSenBasicHoug(final IShip ship, final int attackAugmenting) {
        //TODO 联合舰队基本攻击力补正
        //TODO 修改装备攻击路补正
        final int shipTaisen = 2 * IntMath.sqrt(ship.getShipTaiSen(), RoundingMode.CEILING);
        final int slotTaisen = DoubleMath.roundToInt(1.5d * ship.getSlotItems().stream().mapToInt(AbstractSlotItem::getTaiSen).sum(), RoundingMode.CEILING);
        return shipTaisen + slotTaisen + attackAugmenting;
    }

    private int attackValue(final MemberShip attackShip, final UnderSeaShip defendShip, final BattleContext context) {
        final int hougBeforeThreshold = daylightHougThreshold(augmentingBeforeThreshold(attackShip, context));
        final double augmentingAfterThreshold = augmentingAfterThreshold(attackShip, context);
        return DoubleMath.roundToInt(hougBeforeThreshold * augmentingAfterThreshold, RoundingMode.DOWN);
    }

    private int taiSenValue(final MemberShip attackShip, final UnderSeaShip defendShip, final BattleContext context) {
        final int hougBeforeThreshold = taiSenHougThreshold(taiSenAugmentingBeforeThreshold(attackShip, context));
        final double augmentingAfterThreshold = augmentingAfterThreshold(attackShip, context);
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
    public double augmentingBeforeThreshold(final IShip attackShip, final BattleContext context) {
        final int basicHoug = shellingBaiscHoug(attackShip);
        double augmenting = basicAugmentBeforeThreshold(attackShip, context);

        if (augmenting < 0d) {
            augmenting = 0d;
        }

        // 阈值前攻击力 = 基本攻击力 * 阈值前攻击补正参数 + 轻巡炮攻击力补正
        return augmenting * basicHoug + cLGunAugmenting(attackShip);
    }

    public double taiSenAugmentingBeforeThreshold(final IShip attackShip, final BattleContext context) {
        final int taisenBasicHoug = taiSenBasicHoug(attackShip, DEPTH_CHARGE_AUGMENTING);
        double augmenting = basicAugmentBeforeThreshold(attackShip, context);

        //反潜套补正
        final double taisenAugmenting = taisenShellingAugmenting(attackShip);
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
    public double augmentingAfterThreshold(final MemberShip attackShip, final BattleContext context) {
        double augmenting = 1d;

        final HougekiResult hougekiResult = context.getNowHougekiResult();
        final int attackType = BattleContextUtils.getCurrentAttackType(context);

        final SlotItemInfo slotInfo = SlotItemInfo.of(attackShip);
        if (slotInfo.getAPAmmoCount() > 0) {
            augmenting *= APAMMO_AUGMENTING;
        }

        //暴擊補正
        final int[] clArray = (int[]) getLast(hougekiResult.getApi_cl_list());
        // TODO double attack
        final boolean isCrtical = clArray[0] == CL_VALUE_CRTICAL;
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
