/**
 * 
 */
package com.kancolle.server.dao.slotitem.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.slotitem.SlotItemDao;
import com.kancolle.server.model.po.slotitem.SlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年5月31日
 *
 */
@Repository
public class SlotItemDaoImpl extends BaseDaoImpl<SlotItem> implements SlotItemDao {

    @Override
    public void update(SlotItem t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SlotItem> getSlotItems() {
        return getSqlSession().selectList("selectSlotItems");
    }

    @Override
    public SlotItem getSlotItemById(int slotitem_id) {
        return getSqlSession().selectOne("selectSlotItemByCond", slotitem_id);
    }
}
