/**
 * 
 */
package com.kancolle.server.utils.logic;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.kancolle.server.model.po.ship.AdapterShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.model.po.slotitem.SlotItem;
import com.kancolle.server.utils.logic.common.LvUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年6月29日
 *
 */
public class MemberShipUtils {

    public static Function<Predicate<SlotItem>, Predicate<? super AdapterShip>> hasTargetPlane = cond -> {
        return ship -> {
            List<SlotItem> slots = ship.getAdapterSlotItem();
            for (int i = 0; i < slots.size(); i++) {
                if (ship.getAdapterCurrentEQ()[i] > 0 && cond.test(slots.get(i)))
                    return true;
            }
            return false;
        };
    };

    public static Predicate<? super AdapterShip> ssFilter = ship -> {
        int shipType = ship.getAdapterTypeId();
        return shipType == 13 || shipType == 14 || shipType == 20;
    };

    public static Predicate<? super AdapterShip> antiSSFilter = ship -> {
        int shipType = ship.getAdapterTypeId();
        if (shipType == 2 || shipType == 3 || shipType == 4)
            return true;

        if (shipType == 7 || shipType == 10)
            return hasTargetPlane.apply(item -> item.getType()[2] == 8 || item.getType()[2] == 11).test(ship);

        return false;
    };

    public static void calMemberShipPropertiesViaSlot(MemberShip memberShip) {
        Ship ship = memberShip.getShip();
        // ---------------可改造属性-------------//
        int shipHoug = ship.getHoug().getMinValue() + memberShip.getKyouka()[0];
        int shipRaig = ship.getRaig().getMinValue() + memberShip.getKyouka()[1];
        int shipTaiku = ship.getTyku().getMinValue() + memberShip.getKyouka()[2];
        int shipSouk = ship.getSouk().getMinValue() + memberShip.getKyouka()[3];
        // ---------------可改造属性-------------//

        // --------------根据等级成长属性---------------//
        int lv = memberShip.getLv();
        int shipKaihi = LvUtils.getLvValue(ship.getKaihi(), lv);
        int shipTaisen = LvUtils.getLvValue(ship.getKaihi(), lv);
        int shipSakuteki = LvUtils.getLvValue(ship.getKaihi(), lv);
        // --------------根据等级成长属性---------------//

        for (MemberSlotItem memberSlotItem : memberShip.getSlot()) {
            SlotItem slotitem = memberSlotItem.getSlotItem();
            // 装甲
            shipSouk += slotitem.getSouk();
            // 火力
            shipHoug += slotitem.getHoug();
            // 雷装
            shipRaig += slotitem.getRaig();
            // 对空
            if (slotitem.getType()[2] == 6) {
                shipTaiku += (slotitem.getTyku() * Math.round((float) Math.sqrt(memberShip.getShip().getMaxEq()[memberShip.getSlot().indexOf(memberSlotItem)])));
            } else {
                shipTaiku += slotitem.getTyku();
            }
            // 对潜
            shipTaisen += slotitem.getTais();
            // 回避
            shipKaihi += slotitem.getHouk();
            // 索敌
            shipSakuteki += slotitem.getSaku();
        }
        memberShip.getSoukou().setMinValue(shipSouk);
        memberShip.getKaryoku().setMinValue(shipHoug);
        memberShip.getRaisou().setMinValue(shipRaig);
        memberShip.getTaiku().setMinValue(shipTaiku);
        memberShip.getTaisen().setMinValue(shipTaisen);
        memberShip.getKaihi().setMinValue(shipKaihi);
        memberShip.getSakuteki().setMinValue(shipSakuteki);
        // memberShip.getLucky().setMinValue(shipLuck);
    }

    public static int[] getShipPowupMaxArray(Ship ship) {
        return new int[] { ship.getHoug().getGrowValue(), ship.getRaig().getGrowValue(), ship.getTyku().getGrowValue(), ship.getSouk().getGrowValue() };
    }
}
