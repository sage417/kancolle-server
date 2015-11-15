/**
 * 
 */
package com.kancolle.server.model.po.battle;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.utils.logic.slot.SlotItemUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年11月14日
 *
 */
public class SlotItemInfo {

    // 主炮数量
    private int mainGunCount;
    private long[] mainGunId = new long[4];

    // 副炮数量
    private int secondaryGunCount;
    private long[] secondaryGunId = new long[4];

    // 侦察机数量
    private int searchPlaneCount;
    private long[] searchPlaneId = new long[4];

    // 雷达数量
    private int radarCount;
    private long[] radarId = new long[4];

    // 撤甲弹数量
    private int APAmmoCount;
    private long[] apAmmoId = new long[4];

    private SlotItemInfo() {
    }

    public static SlotItemInfo of(MemberShip ship) {
        SlotItemInfo info = new SlotItemInfo();

        List<MemberSlotItem> slots = ship.getSlot();
        int[] currentEQ = ship.getCurrentEQ();
        for (int i = 0; i < slots.size(); i++) {
            MemberSlotItem slotItem = slots.get(i);
            long slotItemId = slotItem.getMemberSlotItemId();
            int slotType = SlotItemUtils.getType(slotItem);
            switch (slotType) {
            case 1:
            case 2:
            case 3:
                info.mainGunCount++;
                ArrayUtils.add(info.mainGunId, slotItemId);
                break;
            case 4:
                info.secondaryGunCount++;
                ArrayUtils.add(info.secondaryGunId, slotItemId);
                break;
            case 12:
            case 13:
                info.radarCount++;
                ArrayUtils.add(info.radarId, slotItemId);
                break;
            case 9:
            case 10:
                if (currentEQ[i] > 0) {
                    info.searchPlaneCount++;
                    ArrayUtils.add(info.searchPlaneId, slotItemId);
                }
                break;
            case 19:
                info.APAmmoCount++;
                ArrayUtils.add(info.apAmmoId, slotItemId);
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

    public long[] getMainGunId() {
        return mainGunId;
    }

    public void setMainGunId(long[] mainGunId) {
        this.mainGunId = mainGunId;
    }

    public long[] getSecondaryGunId() {
        return secondaryGunId;
    }

    public void setSecondaryGunId(long[] secondaryGunId) {
        this.secondaryGunId = secondaryGunId;
    }

    public long[] getSearchPlaneId() {
        return searchPlaneId;
    }

    public void setSearchPlaneId(long[] searchPlaneId) {
        this.searchPlaneId = searchPlaneId;
    }

    public long[] getRadarId() {
        return radarId;
    }

    public void setRadarId(long[] radarId) {
        this.radarId = radarId;
    }

    public long[] getApAmmoId() {
        return apAmmoId;
    }

    public void setApAmmoId(long[] apAmmoId) {
        this.apAmmoId = apAmmoId;
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
