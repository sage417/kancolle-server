package com.kancolle.server.service.battle.impl;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.EnemySlotItem;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.service.battle.IShellingSystem;
import com.kancolle.server.utils.logic.ship.ShipFilter;

@Service
public class ShellingSystem implements IShellingSystem {
    private static final int ATTACK_TYPE_NORMAL = 0;
    private static final int ATTACK_TYPE_ANTISUBMARINE = 1;
    private static final int ATTACK_TYPE_DOUBLE = 2;
    private static final int ATTACK_TYPE_SECONDARY = 3;
    private static final int ATTACK_TYPE_RADAR = 4;
    private static final int ATTACK_TYPE_EXPOSEARMOR = 5;
    private static final int ATTACK_TYPE_MAIN = 6;

    @Override
    public void generateHougkeResult(MemberShip attackShip, int defShipIdx, HougekiResult result, int aerialState) {

        // 制空优势以上可以发动二连，主副观测，电碳ci等特殊攻击
        if (aerialState == AerialBattleSystemImpl.AIR_BATTLE_GUARANTEE) {

        } else if (aerialState == AerialBattleSystemImpl.AIR_BATTLE_ADVANTAGE) {

        }
        int attackType = 0;
        result.getApi_at_type().add(attackType);
        int[] damages = ArrayUtils.EMPTY_INT_ARRAY;

        int shipHougke = attackShip.getKaryoku().getMinValue();

        if (attackType == ATTACK_TYPE_DOUBLE) {
            int fdamage = shipHougke * 6 / 5;
            int sdamage = shipHougke * 6 / 5;
            // 2次1.2倍率伤害
            result.getApi_df_list().add(new int[] { defShipIdx, defShipIdx });
            damages = new int[] { fdamage, sdamage };
        } else {
            result.getApi_df_list().add(new int[] { defShipIdx });
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

        result.getApi_damage().add(damages);
        result.getApi_si_list().add(new int[] { 1, 2 });
        result.getApi_cl_list().add(new int[] { 1, 1 });
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
        int lucky = ship.getLucky().getMinValue();
        int slotHoum = ship.getSlot().stream().mapToInt(MemberSlotItem::getHoum).sum();
        int hitValue = 95 + 2 * (int) Math.sqrt(nowLv - 1) + slotHoum + 3 * lucky / 20;
        int cond = ship.getCond();
        if (cond < 30) {
            return hitValue / 2;
        } else if (cond < 40) {
            return hitValue * 3 / 4;
        }
        return hitValue;
    }

    private int hitValue(EnemyShip ship) {
        int lucky = ship.getShip().getLuck().getMinValue();
        int slotHoum = ship.getSlot().stream().mapToInt(EnemySlotItem::getHoum).sum();
        return 95 + slotHoum + 3 / 20 * lucky;
    }
}
