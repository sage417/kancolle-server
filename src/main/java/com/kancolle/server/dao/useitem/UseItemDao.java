/**
 * 
 */
package com.kancolle.server.dao.useitem;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.useitem.UseItem;

/**
 * @author J.K.SAGE
 * @Date 2015年7月9日
 *
 */
public interface UseItemDao extends BaseDao<UseItem> {

    List<UseItem> selectUseItems();

    UseItem selectUseItemById(Integer useitem_id);

}
