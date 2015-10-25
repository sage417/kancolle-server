package com.kancolle.server.service.battle.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.math.IntMath;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.EnemySlotItem;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.service.battle.IShellingSystem;
import com.kancolle.server.utils.logic.ship.ShipFilter;

@Service
public class ShellingSystem implements IShellingSystem {
    public static final int ATTACK_TYPE_NORMAL = 0;
    public static final int ATTACK_TYPE_ANTISUBMARINE = 1;
    public static final int ATTACK_TYPE_DOUBLE = 2;
    public static final int ATTACK_TYPE_SECONDARY = 3;
    public static final int ATTACK_TYPE_RADAR = 4;
    public static final int ATTACK_TYPE_EXPOSEARMOR = 5;
    public static final int ATTACK_TYPE_MAIN = 6;

    @Override
    public void generateHougkeResult(BattleSimulationResult result, int aerialState, MemberShip attackShip, ImmutableBiMap<Integer, EnemyShip> enemyOtherShipsMap) {

        HougekiResult hougekiResult = result.getApi_hougeki1();

        EnemyShip defEnemyShip = enemyOtherShipsMap.values().asList().get(RandomUtils.nextInt(0, enemyOtherShipsMap.size()));
        int defShipIdx = enemyOtherShipsMap.inverse().get(defEnemyShip);

        // 制空优势以上可以发动二连，主副观测，电碳ci等特殊攻击
        if (aerialState == AerialBattleSystemImpl.AIR_BATTLE_GUARANTEE) {

        } else if (aerialState == AerialBattleSystemImpl.AIR_BATTLE_ADVANTAGE) {

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
    private void CLGunSystem() {
        if (true) {
            return;
        }

    }

    /**
     */
    private void BBGunSystem(MemberShip memberShip) {
        if (ShipFilter.BBShipFilter.negate().test(memberShip)) {
            return;
        }
    }

    private boolean isHit(int hitValue, int houkValue) {
        int hitRate = 5 + hitValue - houkValue;
        return RandomUtils.nextInt(0, 99) <= hitRate;
    }

    private int houkValue(EnemyShip ship) {
        return houkValue(ship.getShip().getKaihi().getMinValue());
    }

    private int houkValue(MemberShip ship) {
        int houkValue = houkValue(ship.getKaihi().getMinValue());
        int cond = ship.getCond();
        if (cond < 30)
            return houkValue / 2;
        else if (cond < 40)
            return houkValue * 3 / 4;
        else if (cond < 50)
            return houkValue;
        return houkValue * 9 / 5;
    }

    private int houkValue(int shipKaihi) {
        int f;
        if (shipKaihi >= 40)
            f = 40 + shipKaihi;
        else
            f = 2 * 40;
        return 3 + 100 * shipKaihi / f;
    }

    private int hitValue(MemberShip ship) {
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

    private int hitValue(EnemyShip ship) {
        int lucky = ship.getNowLuck();
        int slotHoum = ship.getSlot().stream().mapToInt(EnemySlotItem::getHoum).sum();
        return 95 + slotHoum + 3 / 20 * lucky;
    }

    /**
     * 防御力公式，只和护甲有关
     * 
     * @param ship
     * @return
     */
    private int defValue(AbstractShip ship) {
        int soukou = ship.getNowSoukou();
        int rdmValue = RandomUtils.nextInt(2, 5);
        return rdmValue * soukou / 3;
    }

    private int hurtValue(int attackValue, int defValue) {
        int hurtValue = attackValue - defValue;
        // 没有破防强制扣除5%~10%
        if (hurtValue < 1) {
            hurtValue = 1;
        }
        return hurtValue;
    }

    /** 擦弹和未破防强制扣除当前血量5%~10% */
    private int damageAugmenting(AbstractShip defShip) {
        int nowHp = defShip.getNowHp();
        return RandomUtils.nextInt(nowHp / 20, nowHp / 10 + 1);
    }

    /** 击沉保护 */
    private int destoryAugmenting(MemberShip ship) {
        int nowHp = ship.getNowHp();

        // 当前血量20%~50%浮动
        return RandomUtils.nextInt(nowHp / 5, nowHp / 2 + 1);
    }
}
