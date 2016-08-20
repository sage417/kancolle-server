/**
 * 
 */
package com.kancolle.server.service.slotitem.impl;

import com.google.common.collect.Lists;
import com.kancolle.server.mapper.slotItem.SlotItemMapper;
import com.kancolle.server.model.po.ship.ShipType;
import com.kancolle.server.model.po.slotitem.SlotItem;
import com.kancolle.server.service.slotitem.SlotItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map.Entry;

/**
 * @author J.K.SAGE
 * @Date 2015年6月29日
 *
 */
@Service
public class SlotItemServiceImpl implements SlotItemService {
    @Autowired
    private SlotItemMapper slotItemDao;

    @Override
    public List<SlotItem> getSlotItems() {
        return slotItemDao.selectSlotItemsByCond();
    }

    @Override
    public int getCountOfSlotItemTypes() {
        return slotItemDao.selectCountOfSlotItemTypes();
    }

    @Override
    public List<SlotItem> getSlotItemsCanDevelop(Integer slotItemType) {
        return slotItemDao.selectSlotItemsCanDevelop(slotItemType);
    }

    @Override
    public List<Integer> getSlotItemTypeCanDevelop(ShipType type) {
        List<Integer> types = Lists.newArrayList();
        for (Entry<String, Integer> entry : type.getEquipTypes().entrySet()) {
            Integer slotItemType = Integer.valueOf(entry.getKey());
            if (entry.getValue().equals(1) && !getSlotItemsCanDevelop(slotItemType).isEmpty())
                types.add(slotItemType);
        }
        return types;
    }
}
