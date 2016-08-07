/**
 * 
 */
package com.kancolle.server.dao.useitem;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.useitem.UseItem;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年7月9日
 *
 */
public interface UseItemDao extends BaseDao<UseItem> {

    List<UseItem> selectUseItems();

    UseItem selectUseItemById(Integer useitem_id);

}
