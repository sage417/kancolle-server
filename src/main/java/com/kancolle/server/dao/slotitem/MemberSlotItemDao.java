/**
 * 
 */
package com.kancolle.server.dao.slotitem;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
public interface MemberSlotItemDao extends BaseDao<MemberSlotItem> {

    List<MemberSlotItem> selectMemberSlotItems(long member_id);

    List<MemberSlotItem> selectMemberUnSlots(long member_id);

    MemberSlotItem selectMemberSlotItem(long member_id, Long member_slotitem_id);

    int updateMemberSlotItemLockStatue(long member_id, Long member_slotitem_id, Boolean lock);

    void delete(long member_id, List<Long> slotitem_ids);

    MemberSlotItem createMemberSlotItem(long member_id, int slotItem_id);

    int selectCountOfMemberSlotItem(long member_id);

}
