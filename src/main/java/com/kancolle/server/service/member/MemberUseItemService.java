/**
 * 
 */
package com.kancolle.server.service.member;

import com.kancolle.server.controller.kcsapi.form.item.UseItemForm;
import com.kancolle.server.model.kcsapi.useitem.UseItemResult;

/**
 * @author J.K.SAGE
 * @Date 2015年6月11日
 *
 */
public interface MemberUseItemService {

    /**
     * @param member_id
     * @param form
     */
    UseItemResult useItem(String member_id, UseItemForm form);

    /**
     * @param member_id
     * @param useItem_id
     * @return
     */
    int getCountOfMemberUseItem(String member_id, Integer useItem_id);

}
