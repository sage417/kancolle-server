/**
 * 
 */
package com.kancolle.server.service.slotitem.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.slotitem.SlotItemDao;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.model.po.slotitem.SlotItem;
import com.kancolle.server.service.slotitem.SlotItemService;

/**
 * @author J.K.SAGE
 * @Date 2015年5月31日
 *
 */
@Service
public class SlotItemServiceImpl implements SlotItemService {
    @Autowired
    private SlotItemDao slotItemDao;

    /* (non-Javadoc)
     * @see com.kancolle.server.service.slotItem.SlotItemService#getSlotItemById(int)
     */
    @Override
    public SlotItem getSlotItemById(int slotitem_id) {
        return slotItemDao.getSlotItemById(slotitem_id);
    }
}
