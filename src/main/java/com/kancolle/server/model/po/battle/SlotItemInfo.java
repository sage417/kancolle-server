/**
 *
 */
package com.kancolle.server.model.po.battle;

import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.utils.logic.slot.SlotItemUtils;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年11月14日
 */
public class SlotItemInfo {

    // 主炮数量
    private int mainGunCount;
    private long[] mainGunIds = new long[4];

    // 副炮数量
    private int secondaryGunCount;
    private long[] secondaryGunIds = new long[4];

    // 侦察机数量
    private int searchPlaneCount;
    private long[] searchPlaneIds = new long[4];

    // 雷达数量
    private int radarCount;
    private long[] radarIds = new long[4];

    // 撤甲弹数量
    private int APAmmoCount;
    private long[] apAmmoIds = new long[4];

    private SlotItemInfo() { }

    public static SlotItemInfo of(IShip ship) {
        SlotItemInfo info = new SlotItemInfo();

        for (AbstractSlotItem slotItem : ship.getSlotItems()) {
            long slotItemId = slotItem.getSlotItemId();
            int slotType = SlotItemUtils.getType(slotItem);
            switch (slotType) {
                case 1:
                case 2:
                case 3:
                    info.mainGunCount++;
                    ArrayUtils.add(info.mainGunIds, slotItemId);
                    break;
                case 4:
                    info.secondaryGunCount++;
                    ArrayUtils.add(info.secondaryGunIds, slotItemId);
                    break;
                case 12:
                case 13:
                    info.radarCount++;
                    ArrayUtils.add(info.radarIds, slotItemId);
                    break;
                case 9:
                case 10:
                    info.searchPlaneCount++;
                    ArrayUtils.add(info.searchPlaneIds, slotItemId);
                    break;
                case 19:
                    info.APAmmoCount++;
                    ArrayUtils.add(info.apAmmoIds, slotItemId);
                    break;
                default:
                    break;
            }
        }
        return info;
    }

    public int getSearchPlaneCount() {
        return searchPlaneCount;
    }

    public int getMainGunCount() {
        return mainGunCount;
    }

    public int getSecondaryGunCount() {
        return secondaryGunCount;
    }

    public int getRadarCount() {
        return radarCount;
    }

    public int getAPAmmoCount() {
        return APAmmoCount;
    }

    public long[] getMainGunIds() {
        return mainGunIds;
    }

    public long[] getSecondaryGunIds() {
        return secondaryGunIds;
    }

    public long[] getSearchPlaneIds() {
        return searchPlaneIds;
    }

    public long[] getRadarIds() {
        return radarIds;
    }

    public long[] getApAmmoIds() {
        return apAmmoIds;
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
