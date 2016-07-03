/**
 * 
 */
package com.kancolle.server.dao.slotitem.impl;

import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.slotitem.MemberSlotItemDao;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
@Repository
public class MemberSlotItemDaoImpl extends BaseDaoImpl<MemberSlotItem> implements MemberSlotItemDao {

    @Override
    public void update(MemberSlotItem t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<MemberSlotItem> selectMemberSlotItems(String member_id) {
        return getSqlSession().selectList("selectMemberSlotItem", Collections.singletonMap("member_id", member_id));
    }

    @Override
    public List<MemberSlotItem> selectMemberUnSlots(String member_id) {
        return getSqlSession().selectList("selectMemberUnslot", member_id);
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

    @Override
    public void delete(String member_id, List<Long> slotitem_ids) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("slotitem_ids", slotitem_ids);
        getSqlSession().update("deleteMemberSlotitems", params);
    }

    @Override
    public MemberSlotItem createMemberSlotItem(String member_id, int slotitem_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("slotitem_id", slotitem_id);
        return getSqlSession().selectOne("createMemberSlotItem", params);
    }

    @Override
    public int selectCountOfMemberSlotItem(String member_id) {
        return getSqlSession().selectOne("selectCountOfMemberSlotItem", member_id);
    }
}
