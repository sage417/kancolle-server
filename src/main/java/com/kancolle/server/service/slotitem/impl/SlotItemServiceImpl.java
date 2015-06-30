/**
 * 
 */
package com.kancolle.server.service.slotitem.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.slotitem.SlotItemDao;
import com.kancolle.server.model.po.slotitem.SlotItem;
import com.kancolle.server.service.slotitem.SlotItemService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月29日
 *
 */
@Service
public class SlotItemServiceImpl implements SlotItemService {
    @Autowired
    private SlotItemDao slotItemDao;

    @Override
    public List<SlotItem> getSlotItems() {
        return slotItemDao.getSlotItems();
    }

    @Override
    public int getCountOfSlotItemTypes() {
        return slotItemDao.selectCountOfSlotItemTypes();
    }
}
