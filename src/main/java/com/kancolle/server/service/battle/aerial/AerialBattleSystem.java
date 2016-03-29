package com.kancolle.server.service.battle.aerial;

import com.google.common.math.IntMath;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.utils.logic.slot.SlotItemUtils;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.List;

@Service
public class AerialBattleSystem implements IAerialBattleSystem {

    public static final int AIR_BATTLE_BALANCE = 0;
    public static final int AIR_BATTLE_GUARANTEE = 1;
    public static final int AIR_BATTLE_ADVANTAGE = 2;
    public static final int AIR_BATTLE_DISADVANTAGE = 3;
    public static final int AIR_BATTLE_LOST = 4;

    private static final int GUARANTEE_RATE = 2;
    private static final int ADVANTAGE_RATE = 3 / 2;

    @Override
    public int getMemberDeckPortAerialPower(List<? extends IShip> ships) {

        int airPow = 0;

        for (IShip ship : ships) {
            for (int i = 0; i < ship.getSlotItems().size(); i++) {
                AbstractSlotItem slot = ship.getSlotItems().get(i);

                int currentEQ = ship.getCurrentEQ()[i];
                int slotType = SlotItemUtils.getType(slot);

                if (currentEQ > 0 && (slotType == 6 || slotType == 7 || slotType == 8 || slotType == 11))
                    airPow += slot.getTaik() * IntMath.sqrt(currentEQ, RoundingMode.DOWN);
            }
        }
        return airPow;
    }

    @Override
    public int getAerialPowerStatue(int airPow, int oAirPow) {
        if (airPow == 0 && oAirPow == 0) {
            return AIR_BATTLE_BALANCE;
        }

        if (airPow > GUARANTEE_RATE * oAirPow) {
            return AIR_BATTLE_GUARANTEE;
        } else if (airPow > ADVANTAGE_RATE * oAirPow) {
            return AIR_BATTLE_ADVANTAGE;
        } else if (ADVANTAGE_RATE * airPow > oAirPow) {
            return AIR_BATTLE_BALANCE;
        } else if (GUARANTEE_RATE * airPow > oAirPow) {
            return AIR_BATTLE_DISADVANTAGE;
        } else {
            return AIR_BATTLE_LOST;
        }
    }
}
