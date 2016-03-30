/**
 *
 */
package com.kancolle.server.service.battle.shelling.impl;

import com.google.common.math.DoubleMath;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.battle.shelling.IShellingSystem;
import org.apache.commons.lang3.RandomUtils;

import java.math.RoundingMode;
import java.util.List;

/**
 * @param <S>
 * @author J.K.SAGE
 * @Date 2015年11月1日
 */
public class BaseShipShellingSystem<S extends IShip, E extends IShip> implements IShellingSystem<S, E> {

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

    /* 昼战火力阈值 */
    protected static final int HOUG_THRESHOLD = 150;
    /* 夜战火力阈值 */
    protected static final int NIGHT_HOUG_THRESHOLD = 300;

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


    /*------------暴击补正------------*/
    public static final double SHELLING_CRTICAL_AUGMENTING = 1.5d;
    /*------------暴击补正------------*/

    protected static final int[] DM_SINGLE_ZER0 = new int[]{0};
    protected static final int[] DM_DOUBLE_ZER0 = new int[]{0, 0};

    /**
     * ---------命中性能---------
     **/
    protected static final double HIT_BASE_RADIOS = 1d;
    private static final double HIT_RADIOS_THRESHOLD = 0.975d;
    protected static final double HIT_LEVEL_AUGMENTING = 0.02d;
    protected static final double HIT_LUCK_AUGMENTING = 0.0015d;
    protected static final double HIT_SLOT_AUGMENTING = 0.01d;
    /**
     * ---------命中性能---------
     **/

    /*-------------回避性能-------------*/
    private static final int HOUK_THRESHOLD = 40;
    private static final double HOUK_BASE_RADIOS = 0.03d;
    /*-------------回避性能-------------*/

    protected final double houkThreshold(double shipKaihi) {
        double f = shipKaihi >= HOUK_THRESHOLD ? HOUK_THRESHOLD + shipKaihi : HOUK_THRESHOLD << 1;
        return HOUK_BASE_RADIOS + shipKaihi / f;
    }

    protected double combineKaihiRatio(S ship, BattleContext context) {
        throw new UnsupportedOperationException();
    }

    protected double combineHitRatio(S ship, BattleContext context) {
        throw new UnsupportedOperationException();
    }

    protected final int daylightHougThreshold(double basicHoug) {
        return hougAfterThreshold(basicHoug, HOUG_THRESHOLD);
    }

    protected final int nightHougThreshold(double basicHoug) {
        return hougAfterThreshold(basicHoug, NIGHT_HOUG_THRESHOLD);
    }

    private int hougAfterThreshold(double basicHoug, int threshold) {
        return DoubleMath.roundToInt(basicHoug > threshold ? threshold + Math.sqrt(basicHoug) : basicHoug, RoundingMode.DOWN);
    }

    protected final boolean isHit(double hitValue, double houkValue) {
        return isHit(hitValue, houkValue, 0.05d);
    }

    protected final boolean isCIHit(double hitValue, double houkValue) {
        return isHit(hitValue, houkValue, 0.15d);
    }

    private boolean isHit(double hitValue, double houkValue, double increaseRate) {
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

    /**
     * 擦弹和未破防强制扣除当前血量5%~10%
     */
    private int damageAugmenting(int nowHp) {
        return RandomUtils.nextInt(nowHp / 20, nowHp / 10 + 1);
    }

    /**
     * 击沉保护
     */
    private int destoryAugmenting(int nowHp) {
        // 当前血量20%~50%浮动
        return RandomUtils.nextInt(nowHp / 5, nowHp / 2 + 1);
    }

    /* 破甲机制+保护机制*/
    protected final int damageValue(int attackValue, IShip defShip, boolean destoryProtect) {
        int nowHp = defShip.getNowHp();

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

    public final int getShipDefendValue(IShip ship) {
        int rdmValue = RandomUtils.nextInt(2, 5);
        return rdmValue * ship.getShipSoukou() / 3;
    }

    protected final void generateTaiSenAttackList(BattleContext context, IShip ship) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
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
    protected final int cLGunAugmenting() {
        return 0;
    }

    /**
     */
    protected final void BBGunSystem(MemberShip memberShip) {
    }

    @Override
    public void generateHougkeResult(S ship, BattleContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateAttackList(S ship, BattleContext context) {
        throw new UnsupportedOperationException();

    }

    @Override
    public E generateDefendList(List<E> ship, BattleContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateShellingAttackTypeList(S attackShip, BattleContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateSlotItemList(S ship, BattleContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateCrticalList(S attackShip, E defendShip, BattleContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateDamageList(S attackShip, E defendShip, BattleContext context) {
        throw new UnsupportedOperationException();
    }
}
