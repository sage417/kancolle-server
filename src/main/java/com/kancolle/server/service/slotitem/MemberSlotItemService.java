/**
 * 
 */
package com.kancolle.server.service.slotitem;

import java.util.List;
import java.util.Map;

import com.kancolle.server.controller.kcsapi.form.item.CreateItemForm;
import com.kancolle.server.model.kcsapi.slotitem.CreateItemResult;
import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemDestoryResult;
import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemLockResult;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
public interface MemberSlotItemService {

    List<MemberSlotItem> getMemberSlotItems(String member_id);

    Map<String, Object> getUnsetSlot(String member_id);

    MemberSlotItem getMemberSlotItem(String memberId, Long memberSlotItemId);

    /** 裝備加鎖、解鎖 */
    MemberSlotItemLockResult lock(String member_id, Long slotitem_id);

    /** 解体装备 */
    void destorySlotitems(String member_id, List<MemberSlotItem> removeSlotitems);

    /** 解体装备并返回资源 */
    MemberSlotItemDestoryResult destroyItemAndReturnResource(String member_id, List<Long> slotitem_ids);

    /** 开发 */
    CreateItemResult createItem(String member_id, CreateItemForm form);

}
