/**
 *
 */
package com.kancolle.server.mapper.furniture;

import com.kancolle.server.model.po.furniture.Furniture;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author J.K.SAGE
 * @Date 2015年7月2日
 */
public interface FurnitureMapper {

    List<Furniture> selectFurnitureByCond();

    Furniture selectFurnitureById(@Param("furniture_id") int furniture_id);

    void replaceFurniture(Map<String, Object> stringObjectMap);
}
