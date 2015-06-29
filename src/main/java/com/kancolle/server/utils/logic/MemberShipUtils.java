/**
 * 
 */
package com.kancolle.server.utils.logic;

import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.model.po.slotitem.SlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月29日
 *
 */
public class MemberShipUtils {

    public static void calMemberShipPropertiesViaSlot(MemberShip memberShip) {
        Ship ship = memberShip.getShip();
        // ---------------可改造属性-------------//
        int shipHoug = ship.getHoug().getMinValue() + memberShip.getKyouka()[0];
        int shipRaig = ship.getRaig().getMinValue() + memberShip.getKyouka()[1];
        int shipTaiku = ship.getTyku().getMinValue() + memberShip.getKyouka()[2];
        int shipSouk = ship.getSouk().getMinValue() + memberShip.getKyouka()[3];
        int shipLuck = ship.getLuck().getMinValue() + memberShip.getKyouka()[4];
        // ---------------可改造属性-------------//

        // --------------根据等级成长属性---------------//
        int lv = memberShip.getLv();
        int shipKaihi = LVUtil.getLvValue(ship.getKaihi(), lv);
        int shipTaisen = LVUtil.getLvValue(ship.getKaihi(), lv);
        int shipSakuteki = LVUtil.getLvValue(ship.getKaihi(), lv);
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
    }
}
