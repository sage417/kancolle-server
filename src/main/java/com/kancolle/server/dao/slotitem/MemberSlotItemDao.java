/**
 * 
 */
package com.kancolle.server.dao.slotitem;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
public interface MemberSlotItemDao extends BaseDao<MemberSlotItem> {

    List<MemberSlotItem> selectMemberSlotItems(String member_id);

    List<MemberSlotItem> selectMemberUnSlots(String member_id);

    MemberSlotItem selectMemberSlotItem(String member_id, Long member_slotitem_id);

    int updateMemberSlotItemLockStatue(String member_id, Long member_slotitem_id, Boolean lock);

    void delete(String member_id, List<Long> slotitem_ids);

    MemberSlotItem createMemberSlotItem(String member_id, int slotItem_id);

    int selectCountOfMemberSlotItem(String member_id);

}
