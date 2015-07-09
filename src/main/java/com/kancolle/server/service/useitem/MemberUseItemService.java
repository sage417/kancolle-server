/**
 * 
 */
package com.kancolle.server.service.useitem;

import com.kancolle.server.controller.kcsapi.form.item.UseItemForm;
import com.kancolle.server.model.kcsapi.useitem.UseItemResult;

/**
 * @author J.K.SAGE
 * @Date 2015年6月11日
 *
 */
public interface MemberUseItemService {

    UseItemResult useItem(String member_id, UseItemForm form);

    void addMemberUseItemCount(String member_id, int useitem_id, int addCount);

    int getCountOfMemberUseItem(String member_id, Integer useItem_id);

}
