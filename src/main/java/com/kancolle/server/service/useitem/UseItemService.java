/**
 * 
 */
package com.kancolle.server.service.useitem;

import java.util.List;

import com.kancolle.server.model.po.useitem.UseItem;

/**
 * @author J.K.SAGE
 * @Date 2015年7月9日
 *
 */
public interface UseItemService {

    List<UseItem> getUseItems();

    UseItem getUseItemById(Integer useitem_id);
}
