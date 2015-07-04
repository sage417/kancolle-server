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

    List<MemberSlotItem> getSlotItem(String member_id);

    Map<String, Object> getUnsetSlot(String member_id);

    MemberSlotItem getMemberSlotItem(String memberId, Long memberSlotItemId);

    /** 裝備加鎖、解鎖 */
    MemberSlotItemLockResult lock(String member_id, Long slotitem_id);

    /** 解体装备 */
    void distorySlotitemByIds(String member_id, List<Long> slotitems);

    void distorySlotitems(String member_id, List<MemberSlotItem> removeSlotitems);

}
