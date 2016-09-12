/**
 *
 */
package com.kancolle.server.service.battle.shelling.template;

import com.google.common.collect.Lists;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.battle.SlotItemInfo;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.service.battle.FormationSystem;
import com.kancolle.server.service.battle.aerial.AerialBattleSystem;
import com.kancolle.server.service.battle.course.CourseEnum;
import com.kancolle.server.utils.CollectionsUtils;
import com.kancolle.server.utils.logic.battle.BattleContextUtils;
import com.kancolle.server.utils.logic.ship.ShipFilter;
import com.kancolle.server.utils.logic.ship.ShipUtils;
import com.kancolle.server.utils.logic.slot.SlotItemUtils;
import org.apache.commons.lang3.RandomUtils;

import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @param <A>
 * @author J.K.SAGE
 * @Date 2015年11月1日
 */
public abstract class BaseShipShellingSystem<A extends IShip, D extends IShip> extends ShellingTemplate<A, D> {

    public static final int CL_VALUE_MISS = 0;
    public static final int CL_VALUE_HIT = 1;
    public static final int CL_VALUE_CRTICAL = 2;
    /*------------暴击补正------------*/
    public static final double SHELLING_CRTICAL_AUGMENTING = 1.5d;
    /* --------------------观测CI-------------------- */
    protected static final int ATTACK_TYPE_NORMAL = 0;
    protected static final int ATTACK_TYPE_ANTISUBMARINE = 1;
    protected static final int ATTACK_TYPE_DOUBLE = 2;
    protected static final float ATTACK_TYPE_DOUBLE_FACTOR = 1.2f;
    protected static final int ATTACK_TYPE_SECONDARY = 3;
    protected static final float ATTACK_TYPE_SECONDARY_FACTOR = 1.1f;
    protected static final int ATTACK_TYPE_RADAR = 4;
    protected static final float ATTACK_TYPE_RADAR_FACTOR = 1.2f;
    /* --------------------观测CI-------------------- */
    protected static final int ATTACK_TYPE_EXPOSEARMOR = 5;
    protected static final float ATTACK_TYPE_EXPOSEARMOR_FACTOR = 1.3f;
    protected static final int ATTACK_TYPE_MAIN = 6;
    /* -------------------火力阈值------------------ */
    protected static final float ATTACK_TYPE_MAIN_FACTOR = 1.5f;
    /* -------------------火力阈值------------------ */
    /* 昼战火力阈值 */
    protected static final int HOUG_THRESHOLD = 150;
    /* 反潜火力阈值 */
    protected static final int TAISEN_THRESHOLD = 100;
    /* 夜战火力阈值 */
    protected static final int NIGHT_HOUG_THRESHOLD = 300;
    protected static final int[] CL_SINGLE_MISS = new int[]{CL_VALUE_MISS};
    protected static final int[] CL_SINGLE_HIT = new int[]{CL_VALUE_HIT};
    protected static final int[] CL_SINGLE_CRTICAL = new int[]{CL_VALUE_CRTICAL};
    /*------------伤害补正------------*/
    /*------------伤害补正------------*/
    protected static final double APAMMO_AUGMENTING = 1.08d;
    /* -----------反潜攻击方式补正-------------*/
    protected static final int DEPTH_CHARGE_AUGMENTING = 13;
    /* -----------反潜攻击方式补正-------------*/
    protected static final int AIRCRAFT_AUGMENTING = 8;
    /*------------暴击补正------------*/
    protected static final int[] DM_SINGLE_ZER0 = new int[]{0};
    protected static final int[] DM_DOUBLE_ZER0 = new int[]{0, 0};

    /* ---------命中性能--------- */
    protected static final double HIT_BASE_RADIOS = 0.07d;
    protected static final double HIT_MEMBER_RADIOS = 0.93d;
    protected static final double HIT_UNDERSEA_RADIOS = 0.93d;
    protected static final double HIT_LEVEL_AUGMENTING = 0.02d;
    protected static final double HIT_LUCK_AUGMENTING = 0.0015d;
    protected static final double HIT_SLOT_AUGMENTING = 0.01d;
    private static final double HIT_RADIOS_THRESHOLD = 0.975d;
    /* ---------命中性能--------- */
    /*-------------回避性能-------------*/
    private static final int HOUK_THRESHOLD = 40;
    private static final double HOUK_BASE_RADIOS = 0.03d;
    /*-------------回避性能-------------*/

    /* --------------回避阈值-------------- */
    protected final double houkThreshold(final IShip defendShip) {
        final int shipKaihi = defendShip.getShipKaihi();
        final double f = shipKaihi >= HOUK_THRESHOLD ? HOUK_THRESHOLD + shipKaihi : HOUK_THRESHOLD << 1;
        return HOUK_BASE_RADIOS + shipKaihi / f;
    }
    /* --------------回避阈值-------------- */

    protected final int[] addToCriticalList(final A attackShip, final int attackType, final D defendShip, final BattleContext context) {
        int[] criticals = attackType == ATTACK_TYPE_DOUBLE ? new int[2] : new int[1];
        double houmRatios = shipHoumRatios(attackShip, context);
        double kaihiRatios = shipKaihiRatio(defendShip, context);
        final double finalHoumRatios = hitRadiosThreshold(houmRatios - kaihiRatios);

        for (int i = 0; i < criticals.length; i++) {
            criticals[i] = RandomUtils.nextDouble(0d, 1d) < finalHoumRatios ? CL_VALUE_HIT : CL_VALUE_MISS;
        }
        context.getNowHougekiResult().getApi_cl_list().add(criticals);
        return criticals;
    }

    /* ---------------火力阈值--------------*/

    protected final int daylightHougThreshold(final double basicHoug) {
        return hougAfterThreshold(basicHoug, HOUG_THRESHOLD);
    }

    protected final int taiSenHougThreshold(final double basicHoug) {
        return hougAfterThreshold(basicHoug, TAISEN_THRESHOLD);
    }

    protected final int nightHougThreshold(final double basicHoug) {
        return hougAfterThreshold(basicHoug, NIGHT_HOUG_THRESHOLD);
    }

    private int hougAfterThreshold(final double basicHoug, final int threshold) {
        return DoubleMath.roundToInt(basicHoug > threshold ? threshold + Math.sqrt(basicHoug) : basicHoug, RoundingMode.DOWN);
    }
    /* ---------------火力阈值--------------*/

    /* ---------------炮击火力阈值前补正-------------*/
    protected final double basicAugmentBeforeThreshold(final IShip attackShip, final BattleContext context) {
        final int attackType = BattleContextUtils.getCurrentAttackType(context);

        double augmenting = 1d;

        //阵型补正
        final int formationIndex = BattleContextUtils.getMemberFormation(context);
        final double formationAugmenting = formationShellingAugmenting(formationIndex, attackType);
        augmenting += formationAugmenting;

        //航向补正
        final double courseAugmenting = courseShellingAugmenting(context);
        augmenting += courseAugmenting;

        //损伤补正
        final double damageAugmenting = damageShellingAugmenting(attackShip);
        augmenting += damageAugmenting;
        return augmenting;
    }

    protected final double courseShellingAugmenting(final BattleContext context) {
        final int courseIndex = BattleContextUtils.getBattleCourse(context);
        return CourseEnum.shelllingHougAugment(courseIndex);
    }

    protected final double damageShellingAugmenting(final IShip attackShip) {
        if (ShipUtils.isBadlyDmgStatue.test(attackShip)) {
            // TODO 雷击战补正0
            return -0.6d;
        } else if (ShipUtils.isMidDmgStatue.test(attackShip)) {
            // TODO 雷击战补正0.8
            return -0.3d;
        }
        return 0d;
    }

    protected final double formationShellingAugmenting(final int formationIndex, final int attackType) {
        if (attackType == ATTACK_TYPE_ANTISUBMARINE) {
            return FormationSystem.taiSenHougAugment(formationIndex);
        } else {
            return FormationSystem.shellingHougAugment(formationIndex);
        }
    }

    protected final double taisenShellingAugmenting(final IShip attackShip) {
        final List<? extends AbstractSlotItem> slots = attackShip.getSlotItems();
        final boolean hasHydrophone = slots.stream().anyMatch(slot -> SlotItemUtils.getType(slot) == AbstractSlotItem.TYPE_HYDRO_PHONE);
        final boolean hasDepthCharge = slots.stream().anyMatch(slot -> SlotItemUtils.getType(slot) == AbstractSlotItem.TYPE_DEPTH_CHARGE);
        if (hasHydrophone && hasDepthCharge) {
            return 0.15d;
        }
        return 0d;
    }
    /* ---------------炮击火力阈值前补正-------------*/

    protected final boolean isHit(final double hitValue, final double houkValue) {
        return isHit(hitValue, houkValue, 0.05d);
    }

    private boolean isHit(final double hitValue, final double houkValue, final double increaseRate) {
        double hitRate = increaseRate + hitValue - houkValue;
        hitRate = hitRadiosThreshold(hitRate);
        return RandomUtils.nextDouble(0d, 1d) <= hitRate;
    }

    private double hitRadiosThreshold(final double hitRate) {
        return hitRate > HIT_RADIOS_THRESHOLD ? HIT_RADIOS_THRESHOLD : hitRate;
    }

    /**
     * 命中项
     *
     * @param attackShip
     * @param context
     * @return
     */
    protected abstract double shipHoumRatios(final A attackShip, final BattleContext context);

    protected final double shipHoumRatios(final IShip attackShip) {
        int nowLv = attackShip.getNowLv();
        final double levelRatios = IntMath.sqrt(--nowLv, RoundingMode.DOWN) * HIT_LEVEL_AUGMENTING;

        final int lucky = attackShip.getNowLuck();
        final double luckyRatios = lucky * HIT_LUCK_AUGMENTING;

        final int slotHoum = attackShip.getSlotItems().stream().mapToInt(AbstractSlotItem::getHoum).sum();
        final double slotRatios = slotHoum * HIT_SLOT_AUGMENTING;
        return levelRatios + luckyRatios + slotRatios;
    }

    /**
     * 回避项
     *
     * @param ship
     * @return
     */
    protected abstract double shipKaihiRatio(final D ship, final BattleContext context);

    protected final double getKaihiFormationFactor(final int formation) {
        return (formation == FormationSystem.DOUBLELINE
                || formation == FormationSystem.ECHELON
                || formation == FormationSystem.LINEABREAST) ?
                1.2d : 1d;
    }

    /* 擦弹和未破防强制扣除当前血量5%~10% */
    private int damageAugmenting(final int nowHp) {
        return RandomUtils.nextInt(nowHp / 20, nowHp / 10 + 1);
    }

    /*击沉保护 */
    private int destoryAugmenting(final int nowHp) {
        // 当前血量20%~50%浮动
        return RandomUtils.nextInt(nowHp / 5, nowHp / 2 + 1);
    }

    /* 破甲机制+保护机制*/
    protected final int damageValue(final int attackValue, final IShip defShip, final boolean destoryProtect) {
        final int nowHp = defShip.getNowHp();

        int damage = attackValue - getShipDefendValue(defShip);
        if (damage < 1) {
            damage = damageAugmenting(nowHp);
        }
        if (!destoryProtect) {
            return damage;
        }
        if (damage >= nowHp) {
            return destoryAugmenting(nowHp);
        }
        return damage;
    }

    public final int getShipDefendValue(final IShip ship) {
        final int rdmValue = RandomUtils.nextInt(2, 5);
        return rdmValue * ship.getShipSoukou() / 3;
    }

    protected final void generateTaiSenAttackList(final BattleContext context, final IShip ship) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();
        hougekiResult.getApi_at_type().add(ATTACK_TYPE_ANTISUBMARINE);
    }

    @Override
    protected D callBackAfterChooseTargetShip(final A attackShip, final D defendShip, final BattleContext context) {
        // 如果是旗舰受攻击,

        D coverShip = getCoverShip(defendShip, context);

        return coverShip == null ? defendShip : coverShip;
    }

    private D getCoverShip(final D defendShip, final BattleContext context) {

        if (!BattleContextUtils.isFlagShip(defendShip, context)) {
            return null;
        }

        int currentFormation = context.getApply().getCurrentFormation(context);
        double coverRate = FormationSystem.shellingCoverAugment(currentFormation);

        if (RandomUtils.nextDouble(0d,1d)> coverRate) {
            return null;
        }

        List<D> ships = context.getApply().getEnemyShips(context);

        Predicate<IShip> filter = ShipFilter.ssFilter.test(defendShip) ? ShipFilter.ssFilter : ShipFilter.ssFilter.negate();

        List<D> coverShips = ships.stream().filter(filter).filter(ShipUtils.isTinyDmg.negate()).collect(Collectors.toList());

        return CollectionsUtils.randomGet(coverShips);
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
    protected final int chooseAttackTypeAndSlotItem(final A attackShip, final D defendShip, final BattleContext context) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();

        final LinkedList<Integer> at_type_list = hougekiResult.getApi_at_type();
        final LinkedList<Object> si_list = hougekiResult.getApi_si_list();

        int attackType = ATTACK_TYPE_NORMAL;
        final List<Integer> si = Lists.newArrayListWithCapacity(4);

        do {
            if (ShipFilter.ssFilter.test(defendShip)) {
                attackType = ATTACK_TYPE_ANTISUBMARINE;
                break;
            }

            final int aerialState = context.getApply().getCurrentAerialState(context);
            // TODO cache slotItem info
            final SlotItemInfo slotItemInfo = SlotItemInfo.of(attackShip);
            if (canObservationShootingDecideByAerialState(aerialState) && ShipUtils.isBadlyDmg.test(defendShip) && canObservationShootingDecideBySlotItem(slotItemInfo)) {

                final int mainGunCount = slotItemInfo.getMainGunCount();
                final int secondaryGunCount = slotItemInfo.getSecondaryGunCount();
                final int radarCount = slotItemInfo.getRadarCount();
                final int apAmmoCount = slotItemInfo.getAPAmmoCount();

                // 主炮CI(主主+撤甲)
                if (mainGunCount > 1 && apAmmoCount > 0 && doObservationShooting(attackShip, ATTACK_TYPE_MAIN, aerialState, context)) {
                    attackType = ATTACK_TYPE_MAIN;
                    si.addAll(slotItemInfo.getMainGuns().stream().map(AbstractSlotItem::getSlotItemId).limit(2L).collect(Collectors.toList()));
                    si.add(slotItemInfo.getApAmmos().stream().map(AbstractSlotItem::getSlotItemId).limit(1L).iterator().next());
                    break;
                }

                // 主副CI(主副)
                if (mainGunCount > 0 && secondaryGunCount > 0 && doObservationShooting(attackShip, ATTACK_TYPE_SECONDARY, aerialState, context)) {
                    attackType = ATTACK_TYPE_SECONDARY;
                    si.add(slotItemInfo.getMainGuns().stream().map(AbstractSlotItem::getSlotItemId).limit(1L).iterator().next());
                    si.add(slotItemInfo.getSecondaryGuns().stream().map(AbstractSlotItem::getSlotItemId).limit(1L).iterator().next());
                    break;
                }

                // 电探CI(主副+电探)
                if (mainGunCount > 0 && secondaryGunCount > 0 && radarCount > 0 && doObservationShooting(attackShip, ATTACK_TYPE_RADAR, aerialState, context)) {
                    attackType = ATTACK_TYPE_RADAR;
                    si.add(slotItemInfo.getMainGuns().stream().map(AbstractSlotItem::getSlotItemId).limit(1L).iterator().next());
                    si.add(slotItemInfo.getSecondaryGuns().stream().map(AbstractSlotItem::getSlotItemId).limit(1L).iterator().next());
                    si.add(slotItemInfo.getRadars().stream().map(AbstractSlotItem::getSlotItemId).limit(1L).iterator().next());
                    break;
                }

                // 撤甲弹CI(主副+撤甲)
                if (mainGunCount > 0 && secondaryGunCount > 0 && apAmmoCount > 0 && doObservationShooting(attackShip, ATTACK_TYPE_EXPOSEARMOR, aerialState, context)) {
                    attackType = ATTACK_TYPE_EXPOSEARMOR;
                    si.add(slotItemInfo.getMainGuns().stream().map(AbstractSlotItem::getSlotItemId).limit(1L).iterator().next());
                    si.add(slotItemInfo.getSecondaryGuns().stream().map(AbstractSlotItem::getSlotItemId).limit(1L).iterator().next());
                    si.add(slotItemInfo.getApAmmos().stream().map(AbstractSlotItem::getSlotItemId).limit(1L).iterator().next());
                    break;
                }

                // 连击(主主)
                if (mainGunCount > 1 && doObservationShooting(attackShip, ATTACK_TYPE_DOUBLE, aerialState, context)) {
                    attackType = ATTACK_TYPE_DOUBLE;
                    si.addAll(slotItemInfo.getMainGuns().stream().map(AbstractSlotItem::getSlotItemId).limit(2L).collect(Collectors.toList()));
                    break;
                }

                // 普通攻击
                if (mainGunCount > 0) {
                    si_list.add(slotItemInfo.getMainGuns().stream().map(AbstractSlotItem::getSlotItemId).limit(1L).iterator().next());
                } else if (secondaryGunCount > 0) {
                    si_list.add(slotItemInfo.getSecondaryGuns().stream().map(AbstractSlotItem::getSlotItemId).limit(1L).iterator().next());
                } else {
                    si_list.add(Collections.singletonList(-1));
                }
            }
        } while (false);

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
        double attackTypeAugmenting;

        switch (attackType) {
            case ATTACK_TYPE_DOUBLE:
                attackTypeAugmenting = 0.4d;
                break;
            case ATTACK_TYPE_MAIN:
                attackTypeAugmenting = 0.3d;
                break;
            case ATTACK_TYPE_SECONDARY:
                attackTypeAugmenting = 0.3d;
                break;
            case ATTACK_TYPE_RADAR:
                attackTypeAugmenting = 0.3d;
                break;
            case ATTACK_TYPE_EXPOSEARMOR:
                attackTypeAugmenting = 0.3d;
                break;
            default:
                attackTypeAugmenting = 0d;
        }

        boolean isFlagShip = BattleContextUtils.isFlagShip(attackShip, context);
        if (isFlagShip) {
            attackTypeAugmenting += 0.1d;
        }

        if (aerialState == AerialBattleSystem.AIR_BATTLE_GUARANTEE) {
            attackTypeAugmenting += 0.1d;
        }

        final int sakutekiSum = getCurrentSakutekiSum(context);
        attackTypeAugmenting += sakutekiSum / 10000d;

        return RandomUtils.nextDouble(0d, 1d) < attackTypeAugmenting;
    }

    /**
     * 輕巡適型／輕量砲
     * <p>
     * 適型砲補正 = 2 x √該艦所裝備的連裝砲數量 + √該艦所裝備的單裝砲數量
     * <p>
     * 適用此補正的連裝砲： 15.2cm連装砲改 15.2cm連装砲 14cm連装砲
     * <p>
     * 適用此補正的單裝砲： 15.2cm単装砲 14cm単装砲
     * <p>
     * 補正適用艦種： 輕巡、雷巡、練巡
     * <p>
     * 補正適用場合： 晝戰砲擊戰 夜戰通常攻擊 夜戰CI 夜戰二連
     * <p>
     * 支援艦隊不受補正影響
     */
    protected final int cLGunAugmenting(final IShip ship) {
        return 0;
    }

    /**
     * 阵型命中项补正
     *
     * @param attackFormation
     * @param defendFormation
     * @return
     */
    protected final double getHoumFormationFactor(final int attackFormation, final int defendFormation) {
        return (defendFormation != FormationSystem.LINEAHEAD &&
                (attackFormation == FormationSystem.DOUBLELINE ||
                        attackFormation == FormationSystem.ECHELON ||
                        attackFormation == FormationSystem.LINEABREAST)) ?
                1.2d : 1d;
    }

    /**
     * 阵型命中项补正
     *
     * @param context
     * @return
     */
    protected abstract double getHoumFormationFactor(final BattleContext context);

    protected final void BBGunSystem(final MemberShip memberShip) {
    }

    protected abstract int getCurrentSakutekiSum(final BattleContext context);

}
