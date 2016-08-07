/**
 * 
 */
package com.kancolle.server.dao.slotitem.impl;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.slotitem.SlotItemDao;
import com.kancolle.server.model.po.slotitem.SlotItem;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        return getSqlSession().selectList("selectSlotItemsByCond");
    }

    @Override
    public int selectCountOfSlotItemTypes() {
        return getSqlSession().selectOne("selectCountOfSlotItemTypes");
    }

    @Override
    public List<SlotItem> selectSlotItemsCanDevelop(Integer slotItemType) {
        return getSqlSession().selectList("selectSlotItemsCanDevelop", slotItemType);
    }
}
