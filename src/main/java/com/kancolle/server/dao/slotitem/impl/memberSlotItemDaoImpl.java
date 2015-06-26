/**
 * 
 */
package com.kancolle.server.dao.slotitem.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.slotitem.MemberSlotItemDao;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
@Repository
public class memberSlotItemDaoImpl extends BaseDaoImpl<MemberSlotItem> implements MemberSlotItemDao {

    @Override
    public void update(MemberSlotItem t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MemberSlotItem selectMemberSlotItem(String member_id, Long member_slotitem_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("member_slotitem_id", member_slotitem_id);
        return getSqlSession().selectOne("selectMemberSlotItem", params);
    }

    @Override
    public int updateMemberSlotItemLockStatue(String member_id, Long member_slotitem_id, Boolean lock) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("member_id", member_id);
        params.put("member_slotitem_id", member_slotitem_id);
        params.put("lock", lock);
        return getSqlSession().update("updateMemberSlotItemLockStatue", params);
    }
}
