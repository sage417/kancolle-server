/**
 *
 */
package com.kancolle.server.model.po.battle;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.slotitem.ISlotItem;
import com.kancolle.server.utils.logic.slot.SlotItemUtils;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年11月14日
 */
public class SlotItemInfo {

    // 主炮
    private final ImmutableList<ISlotItem> mainGuns;

    // 副炮
    private final ImmutableList<ISlotItem> secondaryGuns;

    // 侦察机
    private final ImmutableList<ISlotItem> searchPlanes;

    // 雷达
    private final ImmutableList<ISlotItem> radars;

    // 撤甲弹
    private final ImmutableList<ISlotItem> apAmmos;

    private SlotItemInfo(final ImmutableList<ISlotItem> mainGuns, final ImmutableList<ISlotItem> secondaryGuns,
                         final ImmutableList<ISlotItem> searchPlanes, final ImmutableList<ISlotItem> radars,
                         final ImmutableList<ISlotItem> apAmmos) {
        this.mainGuns = mainGuns;
        this.secondaryGuns = secondaryGuns;
        this.searchPlanes = searchPlanes;
        this.radars = radars;
        this.apAmmos = apAmmos;
    }

    public static SlotItemInfo of(final IShip ship) {

        final List<ISlotItem> mainGunIds = Lists.newArrayListWithCapacity(4);
        final List<ISlotItem> secondaryGunIds = Lists.newArrayListWithCapacity(4);
        final List<ISlotItem> radarIds = Lists.newArrayListWithCapacity(4);
        final List<ISlotItem> searchPlaneIds = Lists.newArrayListWithCapacity(4);
        final List<ISlotItem> apAmmoIds = Lists.newArrayListWithCapacity(4);

        int EQIdx = 0;

        for (final ISlotItem slotItem : ship.getSlotItems()) {
            final int slotType = SlotItemUtils.getType(slotItem);
            switch (slotType) {
                case 1:
                case 2:
                case 3:
                    mainGunIds.add(slotItem);
                    break;
                case 4:
                    secondaryGunIds.add(slotItem);
                    break;
                case 12:
                case 13:
                    radarIds.add(slotItem);
                    break;
                case 9:
                case 10:
                    if (ship.getCurrentEQ()[EQIdx] > 0) {
                        searchPlaneIds.add(slotItem);
                    }
                    break;
                case 19:
                    apAmmoIds.add(slotItem);
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
        return searchPlanes.size();
    }

    public int getMainGunCount() {
        return mainGuns.size();
    }

    public int getSecondaryGunCount() {
        return secondaryGuns.size();
    }

    public int getRadarCount() {
        return radars.size();
    }

    public int getAPAmmoCount() {
        return apAmmos.size();
    }

    public ImmutableList<ISlotItem> getMainGuns() {
        return mainGuns;
    }

    public ImmutableList<ISlotItem> getSecondaryGuns() {
        return secondaryGuns;
    }

    public ImmutableList<ISlotItem> getSearchPlanes() {
        return searchPlanes;
    }

    public ImmutableList<ISlotItem> getRadars() {
        return radars;
    }

    public ImmutableList<ISlotItem> getApAmmos() {
        return apAmmos;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SlotItemInfo{");
        sb.append("mainGuns=").append(mainGuns);
        sb.append(", secondaryGuns=").append(secondaryGuns);
        sb.append(", searchPlanes=").append(searchPlanes);
        sb.append(", radars=").append(radars);
        sb.append(", apAmmos=").append(apAmmos);
        sb.append('}');
        return sb.toString();
    }
}
