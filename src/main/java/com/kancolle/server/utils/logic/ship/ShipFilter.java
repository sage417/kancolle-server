/**
 * 
 */
package com.kancolle.server.utils.logic.ship;

import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.utils.logic.slot.SlotItemUtils;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author J.K.SAGE
 * @Date 2015年10月2日
 *
 */
public abstract class ShipFilter {

    public static Predicate<IShip> isAlive = s -> s.getNowHp() > 0;

    /** 返回是否有指定类型的飞机，并且要有搭载 */
    public static Function<Predicate<AbstractSlotItem>, Predicate<IShip>> hasTargetPlaneFilter = cond ->
         ship -> ship.getSlotItems().stream().anyMatch(slotItem -> getCurrentEQ(ship, slotItem) > 0 && cond.test(slotItem));

    /**
     * 过滤出潜艇
     */
    public static Predicate<IShip> ssFilter = ship -> {
        int shipType = ship.getShip().getShipTypeId();
        return shipType == 13 || shipType == 14 || shipType == 20;
    };

    /**
     * 过滤出空母
     */
    public static Predicate<IShip> carrierFilter = ship -> {
        int shipType = ship.getShip().getShipTypeId();
        return shipType == 7 || shipType == 11;
    };

    public static Predicate<IShip> attackableCarrierFilter = hasTargetPlaneFilter.apply(plane -> {
        int planeType = SlotItemUtils.getType(plane);
        return planeType == 7 || planeType == 8;
    });

    /**
     * 过滤出反潜船
     */
    public static Predicate<IShip> antiSSShipFilter = ship -> {
        int shipType = ship.getShip().getShipTypeId();
        if (shipType == 2 || shipType == 3 || shipType == 4)
            return true;

        if (shipType == 7 || shipType == 10)
            return hasTargetPlaneFilter.apply(plane -> SlotItemUtils.getType(plane) == 8 || SlotItemUtils.getType(plane) == 11).test(ship);

        return false;
    };

    public static Predicate<IShip> BBShipFilter = ship -> {
        int shipType = ship.getShip().getShipTypeId();
        return shipType == 8 || shipType == 10;
    };

    private static int getCurrentEQ(IShip ship, AbstractSlotItem slotItem) {
        List<? extends AbstractSlotItem> slotItems = ship.getSlotItems();
        int index = slotItems.indexOf(slotItem);
        return ship.getCurrentEQ()[index];
    }

    public static <T extends IShip> List<T> getTargetShips(List<T> ships, Predicate<IShip> shipFilter) {
        return ships.stream().filter(shipFilter).collect(Collectors.toList());
    }
}
