/**
 *
 */
package com.kancolle.server.mapper.slotItem;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.slotitem.SlotItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author J.K.SAGE
 * @Date 2015年5月31日
 */
public interface SlotItemMapper extends BaseDao<SlotItem> {

    List<SlotItem> selectSlotItemsByCond();

    int selectCountOfSlotItemTypes();

    List<SlotItem> selectSlotItemsCanDevelop(@Param("slotitem_type") Integer slotItemType);

    void replaceSlotItem(Map<String, Object> stringObjectMap);
}
