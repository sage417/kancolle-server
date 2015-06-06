/**
 * 
 */
package com.kancolle.server.dao.slotitem.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.slotitem.SlotItemDao;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.model.po.slotitem.SlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年5月31日
 *
 */
@Repository
public class SlotItemDaoImpl extends BaseDaoImpl<SlotItem> implements SlotItemDao {

    /* (non-Javadoc)
     * @see com.kancolle.server.dao.slotItem.SlotItemDao#getSlotItemById(int)
     */
    @Override
    public SlotItem getSlotItemById(int slotitem_id) {
        return getSqlSession().selectOne("getSlotItemById", slotitem_id);
    }
}
