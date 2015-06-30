/**
 * 
 */
package com.kancolle.server.service.slotitem;

import java.util.List;
import java.util.Map;

import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemLockResult;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
public interface MemberSlotItemService {

    Map<String, Object> getUnsetSlot(String member_id);

    MemberSlotItem getMemberSlotItem(String memberId, Long memberSlotItemId);

    List<MemberSlotItem> getMemberUnSlots(String member_id);

    /** 裝備加鎖、解鎖 */
    MemberSlotItemLockResult lock(String member_id, Long slotitem_id);

}
