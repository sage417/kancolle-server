/**
 * 
 */
package com.kancolle.server.utils.logic.slot;

import com.kancolle.server.model.po.slotitem.AbstractSlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年10月31日
 *
 */
public abstract class SlotItemUtils {

    public static int getType(AbstractSlotItem slotItem) {
        return slotItem.getCategoryId();
    }

    public static boolean isSearchPlane(AbstractSlotItem slotItem) {
        int slotItemType = getType(slotItem);
        return slotItemType == 6 || slotItemType == 7 || slotItemType == 8 || slotItemType == 9 || slotItemType == 25 || slotItemType == 26;
    }
}
