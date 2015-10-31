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
        return slotItem.getType()[2];
    }
}
