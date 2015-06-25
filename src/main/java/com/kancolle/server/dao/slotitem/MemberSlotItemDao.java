/**
 * 
 */
package com.kancolle.server.dao.slotitem;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
public interface MemberSlotItemDao extends BaseDao<MemberSlotItem> {

    MemberSlotItem selectMemberSlotItem(String member_id, Long memberSlotItem_id);

    void updateLockStatue(String member_id, long slotitem_id, boolean lock);

}
