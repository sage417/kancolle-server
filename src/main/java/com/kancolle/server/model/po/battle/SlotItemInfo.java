/**
 * 
 */
package com.kancolle.server.model.po.battle;

import java.util.List;

import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.utils.logic.slot.SlotItemUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年11月14日
 *
 */
public class SlotItemInfo {

    // 主炮数量
    private int mainGunCount;

    // 副炮数量
    private int secondaryGunCount;

    // 侦察机数量
    private int searchPlaneCount;

    // 雷达数量
    private int radarCount;

    // 撤甲弹数量
    private int APAmmoCount;

    private SlotItemInfo() {
    }

    public static SlotItemInfo of(AbstractShip ship) {
        List<? extends AbstractSlotItem> slots = ship.getSlotItems();
        int[] currentEQ = ship.getCurrentEQ();
        SlotItemInfo info = new SlotItemInfo();
        for (int i = 0; i < slots.size(); i++) {
            AbstractSlotItem slotItem = slots.get(i);
            int slotType = SlotItemUtils.getType(slotItem);
            switch (slotType) {
            case 1:
            case 2:
            case 3:
                info.mainGunCount++;
                break;
            case 4:
                info.secondaryGunCount++;
                break;
            case 12:
            case 13:
                info.radarCount++;
                break;
            case 9:
            case 10:
                if (currentEQ[i] > 0)
                    info.searchPlaneCount++;
            }
        }

        return info;
    }

    public int getSearchPlaneCount() {
        return searchPlaneCount;
    }

    public void setSearchPlaneCount(int searchPlaneCount) {
        this.searchPlaneCount = searchPlaneCount;
    }

    public int getMainGunCount() {
        return mainGunCount;
    }

    public void setMainGunCount(int mainGunCount) {
        this.mainGunCount = mainGunCount;
    }

    public int getSecondaryGunCount() {
        return secondaryGunCount;
    }

    public void setSecondaryGunCount(int secondaryGunCount) {
        this.secondaryGunCount = secondaryGunCount;
    }

    public int getRadarCount() {
        return radarCount;
    }

    public void setRadarCount(int radarCount) {
        this.radarCount = radarCount;
    }

    public int getAPAmmoCount() {
        return APAmmoCount;
    }

    public void setAPAmmoCount(int aPAmmoCount) {
        APAmmoCount = aPAmmoCount;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SlotItemInfo [mainGunCount=");
        builder.append(mainGunCount);
        builder.append(", secondaryGunCount=");
        builder.append(secondaryGunCount);
        builder.append(", searchPlaneCount=");
        builder.append(searchPlaneCount);
        builder.append(", radarCount=");
        builder.append(radarCount);
        builder.append(", APAmmoCount=");
        builder.append(APAmmoCount);
        builder.append("]");
        return builder.toString();
    }
}
