/**
 * 
 */
package com.kancolle.server.service.useitem;

import com.kancolle.server.model.po.useitem.UseItem;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年7月9日
 *
 */
public interface UseItemService {

    List<UseItem> getUseItems();

    UseItem getUseItemById(Integer useitem_id);
}
