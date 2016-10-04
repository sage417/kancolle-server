/**
 *
 */
package com.kancolle.server.utils.logic;

import com.kancolle.server.model.po.common.MaxMinValue;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.model.po.slotitem.SlotItem;
import com.kancolle.server.utils.logic.common.LvUtils;

import java.util.Objects;

/**
 * @author J.K.SAGE
 * @Date 2015年6月29日
 */
public class MemberShipUtils {

    public static void calMemberShipPropertiesViaSlot(MemberShip memberShip) {
        // ---------------可改造属性-------------//
        Ship ship = memberShip.getShip();
        int shipHoug = ship.getHoug().getMinValue() + memberShip.getKyouka()[0];
        int shipRaig = ship.getRaig().getMinValue() + memberShip.getKyouka()[1];
        int shipTaiku = ship.getTyku().getMinValue() + memberShip.getKyouka()[2];
        int shipSouk = ship.getSouk().getMinValue() + memberShip.getKyouka()[3];
        // ---------------可改造属性-------------//

        // --------------根据等级成长属性---------------//
        int lv = memberShip.getLv();
        int shipKaihi = LvUtils.getLvValue(ship.getKaihi(), lv);
        int shipTaisen = LvUtils.getLvValue(ship.getTaisen(), lv);
        int shipSakuteki = LvUtils.getLvValue(ship.getSakuteki(), lv);
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
            shipTaiku += slotitem.getTyku();
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

    public static int[] getShipPowupMaxArray(final Ship ship) {
        return new int[]{growValue(ship.getHoug()), growValue(ship.getRaig()), growValue(ship.getTyku()), growValue(ship.getSouk())};
    }

    private static int growValue(final MaxMinValue value) {
        Objects.requireNonNull(value);
        return value.getMaxValue() - value.getMinValue();
    }
}
