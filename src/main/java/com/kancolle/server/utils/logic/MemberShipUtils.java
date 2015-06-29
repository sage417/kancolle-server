/**
 * 
 */
package com.kancolle.server.utils.logic;

import com.kancolle.server.model.po.common.MaxMinValue;
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
        int shipKaihi = LVUtil.returnLvValue(ship.getKaihi(), lv);
        int shipTaisen = LVUtil.returnLvValue(ship.getKaihi(), lv);
        int shipSakuteki = LVUtil.returnLvValue(ship.getKaihi(), lv);
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

    @Deprecated
    public static void calMemberShipPropertiesViaSlot(MemberShip memberShip, MemberSlotItem memberSlotItem, boolean add) {
        SlotItem slotitem = memberSlotItem.getSlotItem();
        // 装甲
        int slotSouk = slotitem.getSouk();
        if (slotSouk != 0) {
            MaxMinValue souku = memberShip.getSoukou();
            int newSouku = add ? souku.getMinValue() + slotSouk : souku.getMinValue() - slotSouk;
            souku.setMinValue(newSouku);
            memberShip.setSoukou(souku);
        }
        // 火力
        int slotHoug = slotitem.getHoug();
        if (slotHoug != 0) {
            MaxMinValue karyoku = memberShip.getKaryoku();
            int newKaryoku = add ? karyoku.getMinValue() + slotHoug : karyoku.getMinValue() - slotHoug;
            karyoku.setMinValue(newKaryoku);
            memberShip.setKaryoku(karyoku);
        }
        // 雷装
        int slotRaig = slotitem.getRaig();
        if (slotRaig != 0) {
            MaxMinValue raisou = memberShip.getRaisou();
            int newRaisou = add ? raisou.getMinValue() + slotRaig : raisou.getMinValue() - slotRaig;
            raisou.setMinValue(newRaisou);
            memberShip.setRaisou(raisou);
        }
        // 对空
        int slotTyku = slotitem.getTyku();
        if (slotTyku != 0) {
            MaxMinValue taiku = memberShip.getTaiku();
            int newTaiku = 0;
            if (slotitem.getType()[2] == 6) {
                int changeValue = (slotTyku * Math.round((float) Math.sqrt(memberShip.getShip().getMaxEq()[memberShip.getSlot().indexOf(memberSlotItem)])));
                newTaiku = add ? taiku.getMinValue() + changeValue : taiku.getMinValue() - changeValue;
            } else {
                newTaiku = add ? taiku.getMinValue() + slotTyku : taiku.getMinValue() - slotTyku;
            }
            taiku.setMinValue(newTaiku);
            memberShip.setTaiku(taiku);
        }
        // 对潜
        int slotTais = slotitem.getTais();
        if (slotTais != 0) {
            MaxMinValue taisen = memberShip.getTaisen();
            int newTaisen = add ? taisen.getMinValue() + slotTais : taisen.getMinValue() - slotTais;
            taisen.setMinValue(newTaisen);
            memberShip.setTaisen(taisen);
        }
        // 回避
        int slotHouk = slotitem.getHouk();
        if (slotHouk != 0) {
            MaxMinValue kaihi = memberShip.getKaihi();
            int newKaihi = add ? kaihi.getMinValue() + slotHouk : kaihi.getMinValue() - slotHouk;
            kaihi.setMinValue(newKaihi);
            memberShip.setKaihi(kaihi);
        }
        // 索敌
        int slotSaku = slotitem.getSaku();
        if (slotSaku != 0) {
            MaxMinValue sakuteki = memberShip.getSakuteki();
            int newSakuteki = add ? sakuteki.getMinValue() + slotSaku : sakuteki.getMinValue() - slotSaku;
            sakuteki.setMinValue(newSakuteki);
            memberShip.setSakuteki(sakuteki);
        }
    }
}
