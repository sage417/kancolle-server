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
    CreateItemResult createItem(String member_id, CreateItemForm form);

    MemberSlotItem createSlotItem(String member_id, int slotitem_id);

    /** 解体装备 */
    void destorySlotitems(String member_id, List<MemberSlotItem> removeSlotitems);

    /** 解体装备并返回资源 */
    MemberSlotItemDestoryResult destroyItemAndReturnResource(String member_id, List<Long> slotitem_ids);

    MemberSlotItem getMemberSlotItem(String memberId, Long memberSlotItemId);

    List<MemberSlotItem> getMemberSlotItems(String member_id);

    /** 获得所有未被舰娘装备的装备 */
    List<MemberSlotItem> getUnsetSlotList(String member_id);

    Map<String, Object> getUnsetSlotMap(String member_id);

    /** 裝備加鎖、解鎖 */
    MemberSlotItemLockResult lock(String member_id, Long slotitem_id);

    int getCountOfMemberSlotItem(String member_id);

}
