/**
 * 
 */
package com.kancolle.server.service.battle.shell.impl;

import java.math.RoundingMode;

import org.apache.commons.lang3.RandomUtils;

import com.google.common.math.IntMath;
import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.battle.shell.IShellingSystem;
import com.kancolle.server.utils.logic.ship.ShipFilter;

/**
 * @author J.K.SAGE
 * @param <T>
 * @Date 2015年11月1日
 *
 */
public abstract class AbstractShipShellingSystem<T extends AbstractShip, E extends AbstractShip> implements IShellingSystem<T, E> {

    protected static final int ATTACK_TYPE_NORMAL = 0;

    protected static final int ATTACK_TYPE_ANTISUBMARINE = 1;

    protected static final int ATTACK_TYPE_DOUBLE = 2;

    protected static final int ATTACK_TYPE_SECONDARY = 3;

    protected static final int ATTACK_TYPE_RADAR = 4;

    protected static final int ATTACK_TYPE_EXPOSEARMOR = 5;

    protected static final int ATTACK_TYPE_MAIN = 6;

    /* 昼战火力阈值 */
    protected static final int HOUG_THRESHOLD = 150;

    protected static final int NIGHT_HOUG_THRESHOLD = 300;

    protected static final int[] CL_SINGLE_MISS = new int[] { 0 };
    protected static final int[] CL_SINGLE_HIT = new int[] { 1 };
    protected static final int[] CL_SINGLE_CRTICAL = new int[] { 2 };
    protected static final int[] CL_DOUBLE_MISS_MISS = new int[] { 0, 0 };
    protected static final int[] CL_DOUBLE_MISS_HIT = new int[] { 0, 1 };
    protected static final int[] CL_DOUBLE_MISS_CRTICAL = new int[] { 0, 2 };
    protected static final int[] CL_DOUBLE_HIT_MISS = new int[] { 1, 0 };
    protected static final int[] CL_DOUBLE_HIT_HIT = new int[] { 1, 1 };
    protected static final int[] CL_DOUBLE_HIT_CRTICAL = new int[] { 1, 2 };
    protected static final int[] CL_DOUBLE_CRTICAL_MISS = new int[] { 2, 0 };
    protected static final int[] CL_DOUBLE_CRTICAL_HIT = new int[] { 2, 1 };
    protected static final int[] CL_DOUBLE_CRTICAL_CRTICAL = new int[] { 2, 2 };

    protected abstract int hitRatios(T ship);

    protected final int daylightHougThreshold(int houg) {
        return getHougThreshold(houg, HOUG_THRESHOLD);
    }

    protected final int nightHougThreshold(int houg) {
        return getHougThreshold(houg, NIGHT_HOUG_THRESHOLD);
    }

    private int getHougThreshold(int houg, int threshold) {
        return houg > threshold ? threshold + IntMath.sqrt(houg - threshold, RoundingMode.DOWN) : houg;
    }

    protected final boolean isHit(int hitValue, int houkValue) {
        int hitRate = 5 + hitValue - houkValue;
        return RandomUtils.nextInt(0, 99) <= hitRate;
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

        int damage = attackValue - defShip.getShipDefendValue();
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

    /**
     * 輕巡適型／輕量砲
     * 
     * 適型砲補正 = 2 x √該艦所裝備的連裝砲數量 + √該艦所裝備的單裝砲數量
     * 
     * 適用此補正的連裝砲： 15.2cm連装砲改 15.2cm連装砲 14cm連装砲
     * 
     * 適用此補正的單裝砲： 15.2cm単装砲 14cm単装砲
     * 
     * 補正適用艦種： 輕巡、雷巡、練巡
     * 
     * 補正適用場合： 晝戰砲擊戰 夜戰通常攻擊 夜戰CI 夜戰二連
     * 
     * 支援艦隊不受補正影響
     */
    protected final void CLGunSystem() {
        if (true) {
            return;
        }

    }

    /**
     */
    protected final void BBGunSystem(MemberShip memberShip) {
        if (ShipFilter.BBShipFilter.negate().test(memberShip)) {
            return;
        }
    }
}
