/**
 * 
 */
package com.kancolle.server.utils.logic.ship;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.slotitem.SlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年10月2日
 *
 */
public class ShipFilter {

    /** 返回是否有指定类型的飞机，并且要有搭载 */
    public static Function<Predicate<SlotItem>, Predicate<? super AbstractShip>> hasTargetPlaneFilter = cond -> {
        return ship -> ship.getSlotItems().stream().anyMatch(slotItem -> getCurrentEQ(ship, slotItem) > 0 && cond.test(slotItem));
    };

    private ShipFilter() {
    }

    /**
     * 过滤出潜艇
     */
    public static Predicate<? super AbstractShip> ssFilter = ship -> {
        int shipType = ship.getShip().getShipTypeId();
        return shipType == 13 || shipType == 14 || shipType == 20;
    };

    /**
     * 过滤出空母
     */
    public static Predicate<? super AbstractShip> carrierFilter = ship -> {
        int shipType = ship.getShip().getShipTypeId();
        return shipType == 7 || shipType == 11;
    };

    public static Predicate<? super AbstractShip> carrierHasAtackPlaneFilter = ship -> hasTargetPlaneFilter.apply(plane -> {
        int planeType = plane.getType()[2];
        return planeType == 7 || planeType == 8;
    }).test(ship);

    /**
     * 过滤出反潜船
     */
    public static Predicate<? super AbstractShip> antiSSShipFilter = ship -> {
        int shipType = ship.getShip().getShipTypeId();
        if (shipType == 2 || shipType == 3 || shipType == 4)
            return true;

        if (shipType == 7 || shipType == 10)
            return hasTargetPlaneFilter.apply(plane -> plane.getType()[2] == 8 || plane.getType()[2] == 11).test(ship);

        return false;
    };

    private static int getCurrentEQ(AbstractShip ship, SlotItem slotItem) {
        List<SlotItem> slotItems = ship.getSlotItems();
        int index = slotItems.indexOf(slotItem);
        return ship.getCurrentEQ()[index];
    }
}
