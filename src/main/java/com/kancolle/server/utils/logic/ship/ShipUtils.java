package com.kancolle.server.utils.logic.ship;

import java.math.RoundingMode;
import java.util.List;
import java.util.function.Predicate;

import com.google.common.math.IntMath;
import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;

public abstract class ShipUtils {

    public static final Predicate<AbstractShip> isTinyDmg = ship -> {
        int maxHp = ship.getMaxHp();
        int damageValue = maxHp - ship.getNowHp();
        return damageValue >= maxHp / 5;
    };

    public static final Predicate<AbstractShip> isMidDmg = ship -> {
        int maxHp = ship.getMaxHp();
        int damageValue = maxHp - ship.getNowHp();
        return damageValue >= maxHp / 2;
    };

    public static final Predicate<AbstractShip> isBadlyDmg = ship -> {
        int maxHp = ship.getMaxHp();
        int damageValue = maxHp - ship.getNowHp();
        return damageValue >= maxHp * 2 / 3;
    };

    public static final Predicate<AbstractShip> isTinyDmgStatue = ship -> isTinyDmg.and(isMidDmg.negate()).test(ship);

    public static final Predicate<AbstractShip> isMidDmgStatue = ship -> isMidDmg.and(isBadlyDmg.negate()).test(ship);

    public static final Predicate<AbstractShip> isBadlyDmgStatue = isBadlyDmg;

    public static final int getTaiSenValue(AbstractShip ship) {
        int shipTaiSen = IntMath.sqrt(ship.getShipTaiSen(), RoundingMode.DOWN);
        List<? extends AbstractSlotItem> slots = ship.getSlotItems();
        int slotTaiSen = slots.stream().mapToInt(AbstractSlotItem::getTaiSen).sum();
        return shipTaiSen + slotTaiSen;
    }
}
