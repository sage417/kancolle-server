package com.kancolle.server.mapper.ship;

import com.kancolle.server.model.po.ship.ShipType;

import java.util.List;

/**
 * Package: com.kancolle.server.mapper.ship
 * Author: mac
 * Date: 16/8/9
 */
public interface ShipTypeMapper {

    List<ShipType> selectShipTypes();

    ShipType selectShipTypeByCond(int type_id);
}
