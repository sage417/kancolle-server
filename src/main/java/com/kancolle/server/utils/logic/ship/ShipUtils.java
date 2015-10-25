package com.kancolle.server.utils.logic.ship;

import java.util.function.Predicate;

import com.kancolle.server.model.po.ship.AbstractShip;

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
}
