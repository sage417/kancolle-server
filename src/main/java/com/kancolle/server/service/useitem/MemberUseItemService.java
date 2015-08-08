/**
 * 
 */
package com.kancolle.server.service.useitem;

import java.util.List;

import com.kancolle.server.controller.kcsapi.form.item.UseItemForm;
import com.kancolle.server.model.kcsapi.useitem.UseItemResult;
import com.kancolle.server.model.po.useitem.MemberUseItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月11日
 *
 */
public interface MemberUseItemService {

    List<MemberUseItem> getMemberUseItems(String member_id);

    UseItemResult useItem(String member_id, UseItemForm form);

    void addMemberUseItemCount(String member_id, int useitem_id, int addCount);

    int getCountOfMemberUseItem(String member_id, Integer useItem_id);

}
