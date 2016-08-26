package com.kancolle.server.mapper.ship;

import com.kancolle.server.model.po.ship.ShipType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Package: com.kancolle.server.mapper.ship
 * Author: mac
 * Date: 16/8/9
 */
public interface ShipTypeMapper {

    List<ShipType> selectShipTypes();

    ShipType selectShipTypeByCond(@Param("type_id") int type_id);

    void updateShipType(Map<String, Object> stringObjectMap);

}
