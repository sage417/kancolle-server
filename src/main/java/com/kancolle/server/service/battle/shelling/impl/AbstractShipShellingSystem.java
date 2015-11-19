/**
 * 
 */
package com.kancolle.server.service.battle.shelling.impl;

import java.math.RoundingMode;

import org.apache.commons.lang3.RandomUtils;

import com.google.common.math.IntMath;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.battle.shelling.IShellingSystem;

/**
 * @author J.K.SAGE
 * @param <T>
 * @Date 2015年11月1日
 *
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

    /*-------------回避性能-------------*/
    private static final int HOUK_THRESHOLD = 40;
    /*-------------回避性能-------------*/

    protected final int houkThreshold(int shipKaihi) {
        int f;
        if (shipKaihi >= HOUK_THRESHOLD)
            f = HOUK_THRESHOLD + shipKaihi;
        else
            f = 2 * HOUK_THRESHOLD;
        return 3 + 100 * shipKaihi / f;
    }

    protected final int memberShipShellingKaihi(MemberShip ship) {
        int shipKaihi = ship.getShipKaihi();
        int cond = ship.getCond();
        if (cond < 30)
            shipKaihi = shipKaihi / 2;
        else if (cond < 40)
            shipKaihi = shipKaihi * 3 / 4;
        else if (cond > 49)
            shipKaihi = shipKaihi * 9 / 5;
        return houkThreshold(shipKaihi);
    }

    protected final int enemyShipShellingKaihi(EnemyShip ship) {
        int shipKaihi = ship.getShipKaihi();
        return houkThreshold(shipKaihi);
    }

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
        // TODO 彈著觀測射擊有命中加成
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

    protected final void generateTaiSenAttackList(BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        hougekiResult.getApi_at_type().add(ATTACK_TYPE_ANTISUBMARINE);
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
    }

    /**
     */
    protected final void BBGunSystem(MemberShip memberShip) {
    }
}
