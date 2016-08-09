package com.kancolle.server.mapper.ship;

import com.kancolle.server.model.po.ship.BaseShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.ship.ShipType;

import java.util.List;

public interface ShipMapper {

    List<Ship> selectShipsByCond();

    List<BaseShip> selectEmShip();

    List<ShipType> selectShipTypes();

    int selectCountOfShipTypes();

    long selectShipNeedExpByLevel(int nowLv);

    int selectShipLVByExp(long nowExp);

    ShipType selectShipTypeByCond(int type_id);

    Ship selectShipsByCond(int ship_id);
}
