package com.kancolle.server.utils.logic.ship;

import com.google.common.math.IntMath;
import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.utils.logic.slot.SlotItemUtils;

import java.math.RoundingMode;
import java.util.List;
import java.util.function.Predicate;

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

    public static int getTaiSenValue(AbstractShip ship) {
        int shipTaiSen = IntMath.sqrt(ship.getShipTaiSen(), RoundingMode.DOWN);
        List<? extends AbstractSlotItem> slots = ship.getSlotItems();
        int slotTaiSen = slots.stream().mapToInt(AbstractSlotItem::getTaiSen).sum();
        return shipTaiSen + slotTaiSen;
    }

    public static int getBakuValue(AbstractShip ship) {
        List<? extends AbstractSlotItem> items = ship.getSlotItems();
        return items.stream().mapToInt(AbstractSlotItem::getBaku).sum();
    }

    public static int getSearchPlaneIndex(AbstractShip ship) {
        List<? extends AbstractSlotItem> slotItems = ship.getSlotItems();
        for (int i = 0; i < slotItems.size(); i++) {
            AbstractSlotItem slotItem = slotItems.get(i);
            if (SlotItemUtils.isSearchPlane(slotItem) && ship.getCurrentEQ()[i] > 0)
                return i;
        }
        return -1;
    }

    public static int getShipSearchValue(AbstractShip ship) {
        int ex_sakuteki = 0;
        for (AbstractSlotItem slotItem : ship.getSlotItems()) {
            int slotItem_saku = slotItem.getSaku();
            ex_sakuteki += SlotItemUtils.isSearchPlane(slotItem) ? 2 * slotItem_saku : slotItem_saku;
        }
        return (IntMath.sqrt(ship.getShipSakuteki(), RoundingMode.DOWN) + ex_sakuteki);
    }
}
