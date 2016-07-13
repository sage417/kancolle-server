/**
 * 
 */
package com.kancolle.server.service.slotitem;

import java.util.List;

import com.kancolle.server.model.po.ship.ShipType;
import com.kancolle.server.model.po.slotitem.SlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月29日
 *
 */
public interface SlotItemService {

    List<SlotItem> getSlotItems();

    int getCountOfSlotItemTypes();

    List<SlotItem> getSlotItemsCanDevelop(Integer integer);

    List<Integer> getSlotItemTypeCanDevelop(ShipType type);

}
