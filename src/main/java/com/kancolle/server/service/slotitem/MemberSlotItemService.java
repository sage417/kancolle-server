/**
 * 
 */
package com.kancolle.server.service.slotitem;

import com.kancolle.server.controller.kcsapi.form.item.CreateItemForm;
import com.kancolle.server.model.kcsapi.slotitem.CreateItemResult;
import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemDestoryResult;
import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemLockResult;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

import java.util.List;
import java.util.Map;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
public interface MemberSlotItemService {

    /** 开发 */
    CreateItemResult createItem(long member_id, CreateItemForm form);

    MemberSlotItem createSlotItem(long member_id, int slotitem_id);

    /** 解体装备 */
    void destorySlotitems(long member_id, List<MemberSlotItem> removeSlotitems);

    /** 解体装备并返回资源 */
    MemberSlotItemDestoryResult destroyItemAndReturnResource(long member_id, List<Long> slotitem_ids);

    MemberSlotItem getMemberSlotItem(long member_id, Long memberSlotItemId);

    List<MemberSlotItem> getMemberSlotItems(long member_id);

    /** 获得所有未被舰娘装备的装备 */
    List<MemberSlotItem> getUnsetSlotList(long member_id);

    Map<String, Object> getUnsetSlotMap(long member_id);

    /** 裝備加鎖、解鎖 */
    MemberSlotItemLockResult lock(long member_id, Long slotitem_id);

    int getCountOfMemberSlotItem(long member_id);

}
