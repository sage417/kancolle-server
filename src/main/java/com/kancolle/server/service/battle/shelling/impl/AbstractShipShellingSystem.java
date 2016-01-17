/**
 *
 */
package com.kancolle.server.service.battle.shelling.impl;

import com.google.common.math.DoubleMath;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.battle.shelling.IShellingSystem;
import org.apache.commons.lang3.RandomUtils;

import java.math.RoundingMode;

/**
 * @author J.K.SAGE
 * @param <T>
 * @Date 2015年11月1日
 */
public abstract class AbstractShipShellingSystem<T extends AbstractShip, E extends AbstractShip> implements IShellingSystem<T, E> {

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

    protected static final int[] CL_SINGLE_MISS = new int[]{0};
    protected static final int[] CL_SINGLE_HIT = new int[]{1};
    protected static final int[] CL_SINGLE_CRTICAL = new int[]{2};
    protected static final int[] CL_DOUBLE_MISS_MISS = new int[]{0, 0};
    protected static final int[] CL_DOUBLE_MISS_HIT = new int[]{0, 1};
    protected static final int[] CL_DOUBLE_MISS_CRTICAL = new int[]{0, 2};
    protected static final int[] CL_DOUBLE_HIT_MISS = new int[]{1, 0};
    protected static final int[] CL_DOUBLE_HIT_HIT = new int[]{1, 1};
    protected static final int[] CL_DOUBLE_HIT_CRTICAL = new int[]{1, 2};
    protected static final int[] CL_DOUBLE_CRTICAL_MISS = new int[]{2, 0};
    protected static final int[] CL_DOUBLE_CRTICAL_HIT = new int[]{2, 1};
    protected static final int[] CL_DOUBLE_CRTICAL_CRTICAL = new int[]{2, 2};

    protected static final int[] DM_SINGLE_ZER0 = new int[]{0};
    protected static final int[] DM_DOUBLE_ZER0 = new int[]{0, 0};

    /**---------命中性能---------**/
    protected static final double HIT_BASE_RADIOS = 1d;
    protected static final double HIT_LEVEL_AUGMENTING = 0.02d;
    protected static final double HIT_LUCK_AUGMENTING =0.0015d;
    protected static final double HIT_SLOT_AUGMENTING = 0.01d;
    /**---------命中性能---------**/

    /*-------------回避性能-------------*/
    private static final int HOUK_THRESHOLD = 40;
    private static final double HOUK_BASE_RADIOS = 0.03d;
    /*-------------回避性能-------------*/

    protected final double houkThreshold(double shipKaihi) {
        double f = shipKaihi >= HOUK_THRESHOLD ? HOUK_THRESHOLD + shipKaihi : HOUK_THRESHOLD << 1;
        return HOUK_BASE_RADIOS + shipKaihi / f;
    }

    protected  abstract  double combineKaihiRatio(T ship, BattleContext context);

    protected abstract double combineHitRatio(T ship, BattleContext context);

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
        double hitRate = 0.05d + hitValue - houkValue;
        return RandomUtils.nextDouble(0d, 1d) <= hitRate;}

    protected final boolean isCIHit(double hitValue, double houkValue) {
        double hitRate = 0.15d + hitValue - houkValue;
        return RandomUtils.nextDouble(0d, 1d) <= hitRate;
    }

    /** 擦弹和未破防强制扣除当前血量5%~10% */
    private final int damageAugmenting(int nowHp) {
        return RandomUtils.nextInt(nowHp / 20, nowHp / 10 + 1);
    }

    /** 击沉保护 */
    private final int destoryAugmenting(int nowHp) {
        // 当前血量20%~50%浮动
        return RandomUtils.nextInt(nowHp / 5, nowHp / 2 + 1);
    }

    /* 破甲机制+保护机制*/
    protected final int damageValue(int attackValue, AbstractShip defShip, boolean destoryProtect) {
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

    public final int getShipDefendValue(AbstractShip ship) {
        int rdmValue = RandomUtils.nextInt(2, 5);
        return rdmValue * ship.getShipSoukou() / 3;
    }

    protected final void generateTaiSenAttackList(BattleContext context) {
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
}
