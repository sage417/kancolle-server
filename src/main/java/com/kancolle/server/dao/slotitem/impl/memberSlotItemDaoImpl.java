/**
 * 
 */
package com.kancolle.server.dao.slotitem.impl;

import java.util.Map;

import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.slotitem.MemberSlotItemDao;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
public class memberSlotItemDaoImpl extends BaseDaoImpl<MemberSlotItem> implements MemberSlotItemDao {

    @Override
    public void update(MemberSlotItem t) {
        // TODO Auto-generated method stub

    }

    @Override
    public MemberSlotItem selectMemberSlotItem(String member_id, Long memberSlotItem_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("memberSlotItem_id", "memberSlotItem_id");
        return getSqlSession().selectOne("selectMemberSlotItem", params);
    }

}
