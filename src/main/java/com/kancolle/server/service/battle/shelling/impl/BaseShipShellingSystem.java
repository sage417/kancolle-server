/**
 *
 */
package com.kancolle.server.service.battle.shelling.impl;

import com.google.common.math.DoubleMath;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.service.battle.FormationSystem;
import com.kancolle.server.service.battle.course.CourseEnum;
import com.kancolle.server.service.battle.shelling.IShellingSystem;
import com.kancolle.server.utils.logic.battle.BattleContextUtils;
import com.kancolle.server.utils.logic.ship.ShipUtils;
import com.kancolle.server.utils.logic.slot.SlotItemUtils;
import org.apache.commons.lang3.RandomUtils;

import java.math.RoundingMode;
import java.util.List;

/**
 * @param <A>
 * @author J.K.SAGE
 * @Date 2015年11月1日
 */
public abstract class BaseShipShellingSystem<A extends IShip, D extends IShip> extends ShellingTemplate<A, D> implements IShellingSystem<A, D> {

    /* --------------------观测CI-------------------- */
    protected static final int ATTACK_TYPE_NORMAL = 0;

    protected static final int ATTACK_TYPE_ANTISUBMARINE = 1;

    protected static final int ATTACK_TYPE_DOUBLE = 2;
    protected static final float ATTACK_TYPE_DOUBLE_FACTOR = 1.2f;

    protected static final int ATTACK_TYPE_SECONDARY = 3;
    protected static final float ATTACK_TYPE_SECONDARY_FACTOR = 1.1f;

    protected static final int ATTACK_TYPE_RADAR = 4;
    protected static final float ATTACK_TYPE_RADAR_FACTOR = 1.2f;

    protected static final int ATTACK_TYPE_EXPOSEARMOR = 5;
    protected static final float ATTACK_TYPE_EXPOSEARMOR_FACTOR = 1.3f;

    protected static final int ATTACK_TYPE_MAIN = 6;
    protected static final float ATTACK_TYPE_MAIN_FACTOR = 1.5f;
    /* --------------------观测CI-------------------- */

    /* -------------------火力阈值------------------ */
    /* 昼战火力阈值 */
    protected static final int HOUG_THRESHOLD = 150;
    /* 反潜火力阈值 */
    protected static final int TAISEN_THRESHOLD = 100;
    /* 夜战火力阈值 */
    protected static final int NIGHT_HOUG_THRESHOLD = 300;
    /* -------------------火力阈值------------------ */

    public static final int CL_VALUE_MISS = 0;
    public static final int CL_VALUE_HIT = 1;
    public static final int CL_VALUE_CRTICAL = 2;

    protected static final int[] CL_SINGLE_MISS = new int[]{CL_VALUE_MISS};
    protected static final int[] CL_SINGLE_HIT = new int[]{CL_VALUE_HIT};
    protected static final int[] CL_SINGLE_CRTICAL = new int[]{CL_VALUE_CRTICAL};
    protected static final int[] CL_DOUBLE_MISS_MISS = new int[]{CL_VALUE_MISS, CL_VALUE_MISS};
    protected static final int[] CL_DOUBLE_MISS_HIT = new int[]{CL_VALUE_MISS, CL_VALUE_HIT};
    protected static final int[] CL_DOUBLE_MISS_CRTICAL = new int[]{CL_VALUE_MISS, CL_VALUE_CRTICAL};
    protected static final int[] CL_DOUBLE_HIT_MISS = new int[]{CL_VALUE_HIT, CL_VALUE_MISS};
    protected static final int[] CL_DOUBLE_HIT_HIT = new int[]{CL_VALUE_HIT, CL_VALUE_HIT};
    protected static final int[] CL_DOUBLE_HIT_CRTICAL = new int[]{CL_VALUE_HIT, CL_VALUE_CRTICAL};
    protected static final int[] CL_DOUBLE_CRTICAL_MISS = new int[]{CL_VALUE_CRTICAL, CL_VALUE_MISS};
    protected static final int[] CL_DOUBLE_CRTICAL_HIT = new int[]{CL_VALUE_CRTICAL, CL_VALUE_HIT};
    protected static final int[] CL_DOUBLE_CRTICAL_CRTICAL = new int[]{CL_VALUE_CRTICAL, CL_VALUE_CRTICAL};

    /*------------伤害补正------------*/
    protected static final double APAMMO_AUGMENTING = 1.08d;
    /*------------伤害补正------------*/

    /* -----------反潜攻击方式补正-------------*/
    protected static final int DEPTH_CHARGE_AUGMENTING = 13;
    protected static final int AIRCRAFT_AUGMENTING = 8;
    /* -----------反潜攻击方式补正-------------*/


    /*------------暴击补正------------*/
    public static final double SHELLING_CRTICAL_AUGMENTING = 1.5d;
    /*------------暴击补正------------*/

    protected static final int[] DM_SINGLE_ZER0 = new int[]{0};
    protected static final int[] DM_DOUBLE_ZER0 = new int[]{0, 0};

    /* ---------命中性能--------- */
    protected static final double HIT_BASE_RADIOS = 1d;
    private static final double HIT_RADIOS_THRESHOLD = 0.975d;
    protected static final double HIT_LEVEL_AUGMENTING = 0.02d;
    protected static final double HIT_LUCK_AUGMENTING = 0.0015d;
    protected static final double HIT_SLOT_AUGMENTING = 0.01d;
    /* ---------命中性能--------- */

    /*-------------回避性能-------------*/
    private static final int HOUK_THRESHOLD = 40;
    private static final double HOUK_BASE_RADIOS = 0.03d;
    /*-------------回避性能-------------*/

    /* --------------回避阈值-------------- */
    protected final double houkThreshold(final double shipKaihi) {
        final double f = shipKaihi >= HOUK_THRESHOLD ? HOUK_THRESHOLD + shipKaihi : HOUK_THRESHOLD << 1;
        return HOUK_BASE_RADIOS + shipKaihi / f;
    }
    /* --------------回避阈值-------------- */

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
            return FormationSystem.shelllingHougAugment(formationIndex);
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

    protected final boolean isCIHit(final double hitValue, final double houkValue) {
        return isHit(hitValue, houkValue, 0.15d);
    }

    private boolean isHit(final double hitValue, final double houkValue, final double increaseRate) {
        double hitRate = increaseRate + hitValue - houkValue;
        hitRate = hitRadiosThreshold(hitRate);
        return RandomUtils.nextDouble(0d, 1d) <= hitRate;
    }

    private double hitRadiosThreshold(double hitRate) {
        if (hitRate > HIT_RADIOS_THRESHOLD) {
            hitRate = HIT_RADIOS_THRESHOLD;
        }
        return hitRate;
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

    protected final void BBGunSystem(final MemberShip memberShip) {
    }

    @Override
    public void generateHougkeResult(final A ship, final BattleContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public D generateDefendList(final List<D> ship, final BattleContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public abstract void generateShellingAttackTypeList(A attackShip, BattleContext context);

    @Override
    public void generateSlotItemList(final A ship, final BattleContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateCrticalList(final A attackShip, final D defendShip, final BattleContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateDamageList(final A attackShip, final D defendShip, final BattleContext context) {
        throw new UnsupportedOperationException();
    }

    /* -----------------联合舰队补正-----------------*/
    protected double combineKaihiRatio(final A ship, final BattleContext context) {
        throw new UnsupportedOperationException();
    }

    protected double combineHitRatio(final A ship, final BattleContext context) {
        throw new UnsupportedOperationException();
    }

    /* -----------------联合舰队补正-----------------*/
}
