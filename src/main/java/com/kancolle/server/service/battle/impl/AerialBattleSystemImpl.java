package com.kancolle.server.service.battle.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kancolle.server.model.po.ship.AdapterShip;
import com.kancolle.server.model.po.slotitem.SlotItem;
import com.kancolle.server.service.battle.AerialBattleSystem;

@Service
public class AerialBattleSystemImpl implements AerialBattleSystem {

    public static final int AIR_BATTLE_BALANCE = 0;
    public static final int AIR_BATTLE_GUARANTEE = 1;
    public static final int AIR_BATTLE_ADVANTAGE = 2;
    public static final int AIR_BATTLE_DISADVANTAGE = 3;
    public static final int AIR_BATTLE_LOST = 4;

    private static final int GUARANTEE_RATE = 2;
    private static final int ADVANTAGE_RATE = 3 / 2;

    @Override
    public int getMemberDeckPortAerialPower(List<? extends AdapterShip> ships) {

        int airPow = 0;

        for (AdapterShip ship : ships) {
            for (int i = 0; i < ship.getAdapterSlotItem().size(); i++) {
                SlotItem slot = ship.getAdapterSlotItem().get(i);

                int currentEQ = ship.getAdapterCurrentEQ()[i];
                int slotType = slot.getType()[2];

                if (currentEQ > 0 && (slotType == 6 || slotType == 7 || slotType == 8 || slotType == 11))
                    airPow += slot.getTaik() * (int) Math.sqrt(currentEQ);
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
