package com.kancolle.server.mapper.useItem;

import com.kancolle.server.model.po.useitem.UseItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Package: com.kancolle.server.mapper.useItem
 * Author: mac
 * Date: 16/8/19
 */
public interface UseItemMapper {

    List<UseItem> selectUseItems();

    UseItem selectUseItemById(@Param("useitem_id") Integer useitem_id);

    void replaceUseItem(Map<String, Object> stringObjectMap);
}
