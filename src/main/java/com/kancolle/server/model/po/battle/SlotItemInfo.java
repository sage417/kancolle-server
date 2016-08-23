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

    // 主炮
    private final ImmutableList<AbstractSlotItem> mainGuns;

    // 副炮
    private final ImmutableList<AbstractSlotItem> secondaryGuns;

    // 侦察机
    private final ImmutableList<AbstractSlotItem> searchPlanes;

    // 雷达
    private final ImmutableList<AbstractSlotItem> radars;

    // 撤甲弹
    private final ImmutableList<AbstractSlotItem> apAmmos;

    private SlotItemInfo(final ImmutableList<AbstractSlotItem> mainGuns, final ImmutableList<AbstractSlotItem> secondaryGuns,
                         final ImmutableList<AbstractSlotItem> searchPlanes, final ImmutableList<AbstractSlotItem> radars,
                         final ImmutableList<AbstractSlotItem> apAmmos) {
        this.mainGuns = mainGuns;
        this.secondaryGuns = secondaryGuns;
        this.searchPlanes = searchPlanes;
        this.radars = radars;
        this.apAmmos = apAmmos;
    }

    public static SlotItemInfo of(final IShip ship) {

        final List<AbstractSlotItem> mainGunIds = Lists.newArrayListWithCapacity(4);
        final List<AbstractSlotItem> secondaryGunIds = Lists.newArrayListWithCapacity(4);
        final List<AbstractSlotItem> radarIds = Lists.newArrayListWithCapacity(4);
        final List<AbstractSlotItem> searchPlaneIds = Lists.newArrayListWithCapacity(4);
        final List<AbstractSlotItem> apAmmoIds = Lists.newArrayListWithCapacity(4);

        int EQIdx = 0;

        for (final AbstractSlotItem slotItem : ship.getSlotItems()) {
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

    public ImmutableList<AbstractSlotItem> getMainGuns() {
        return mainGuns;
    }

    public ImmutableList<AbstractSlotItem> getSecondaryGuns() {
        return secondaryGuns;
    }

    public ImmutableList<AbstractSlotItem> getSearchPlanes() {
        return searchPlanes;
    }

    public ImmutableList<AbstractSlotItem> getRadars() {
        return radars;
    }

    public ImmutableList<AbstractSlotItem> getApAmmos() {
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
