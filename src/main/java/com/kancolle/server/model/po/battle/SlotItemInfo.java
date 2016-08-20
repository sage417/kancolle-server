/**
 *
 */
package com.kancolle.server.model.po.battle;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.utils.logic.slot.SlotItemUtils;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年11月14日
 */
public class SlotItemInfo {

    // 主炮数量
    private final ImmutableList<Long> mainGunIds;

    // 副炮数量
    private final ImmutableList<Long> secondaryGunIds;

    // 侦察机数量
    private final ImmutableList<Long> searchPlaneIds;

    // 雷达数量
    private final ImmutableList<Long> radarIds;

    // 撤甲弹数量
    private final ImmutableList<Long> apAmmoIds;

    public SlotItemInfo(ImmutableList<Long> mainGunIds, ImmutableList<Long> secondaryGunIds,
                        ImmutableList<Long> searchPlaneIds, ImmutableList<Long> radarIds,
                        ImmutableList<Long> apAmmoIds) {
        this.mainGunIds = mainGunIds;
        this.secondaryGunIds = secondaryGunIds;
        this.searchPlaneIds = searchPlaneIds;
        this.radarIds = radarIds;
        this.apAmmoIds = apAmmoIds;
    }

    public static SlotItemInfo of(IShip ship) {

        List<Long> mainGunIds = Lists.newArrayListWithCapacity(4);
        List<Long> secondaryGunIds = Lists.newArrayListWithCapacity(4);
        List<Long> radarIds = Lists.newArrayListWithCapacity(4);
        List<Long> searchPlaneIds = Lists.newArrayListWithCapacity(4);
        List<Long> apAmmoIds = Lists.newArrayListWithCapacity(4);

        int EQIdx = 0;

        for (AbstractSlotItem slotItem : ship.getSlotItems()) {
            long slotItemId = slotItem.getSlotItemId();
            int slotType = SlotItemUtils.getType(slotItem);
            switch (slotType) {
                case 1:
                case 2:
                case 3:
                    mainGunIds.add(slotItemId);
                    break;
                case 4:
                    secondaryGunIds.add(slotItemId);
                    break;
                case 12:
                case 13:
                    radarIds.add(slotItemId);
                    break;
                case 9:
                case 10:
                    if (ship.getCurrentEQ()[EQIdx] > 0) {
                        searchPlaneIds.add(slotItemId);
                    }
                    break;
                case 19:
                    apAmmoIds.add(slotItemId);
                    break;
                default:
                    break;
            }
            EQIdx++;
        }

        return new SlotItemInfo(ImmutableList.copyOf(mainGunIds), ImmutableList.copyOf(secondaryGunIds)
                , ImmutableList.copyOf(searchPlaneIds), ImmutableList.copyOf(radarIds)
                , ImmutableList.copyOf(apAmmoIds));
    }

    public int getSearchPlaneCount() {
        return searchPlaneIds.size();
    }

    public int getMainGunCount() {
        return mainGunIds.size();
    }

    public int getSecondaryGunCount() {
        return secondaryGunIds.size();
    }

    public int getRadarCount() {
        return radarIds.size();
    }

    public int getAPAmmoCount() {
        return apAmmoIds.size();
    }

    public ImmutableList<Long> getMainGunIds() {
        return mainGunIds;
    }

    public ImmutableList<Long> getSecondaryGunIds() {
        return secondaryGunIds;
    }

    public ImmutableList<Long> getSearchPlaneIds() {
        return searchPlaneIds;
    }

    public ImmutableList<Long> getRadarIds() {
        return radarIds;
    }

    public ImmutableList<Long> getApAmmoIds() {
        return apAmmoIds;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SlotItemInfo{");
        sb.append("mainGunIds=").append(mainGunIds);
        sb.append(", secondaryGunIds=").append(secondaryGunIds);
        sb.append(", searchPlaneIds=").append(searchPlaneIds);
        sb.append(", radarIds=").append(radarIds);
        sb.append(", apAmmoIds=").append(apAmmoIds);
        sb.append('}');
        return sb.toString();
    }
}
