/**
 * 
 */
package com.kancolle.server.dao.slotitem;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.slotitem.SlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年5月31日
 *
 */
public interface SlotItemDao extends BaseDao<SlotItem> {

    SlotItem getSlotItemById(int slotitem_id);

}
