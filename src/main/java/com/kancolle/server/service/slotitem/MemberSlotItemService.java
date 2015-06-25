/**
 * 
 */
package com.kancolle.server.service.slotitem;

import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemLockResult;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
public interface MemberSlotItemService {

    MemberSlotItem getMemberSlotItem(String memberId, Long memberSlotItemId);

    /** 裝備加鎖、解鎖 */
    MemberSlotItemLockResult lock(String member_id, long slotitem_id);

}
