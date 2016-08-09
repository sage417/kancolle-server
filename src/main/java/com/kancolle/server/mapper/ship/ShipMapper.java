package com.kancolle.server.mapper.ship;

import com.kancolle.server.model.po.ship.BaseShip;
import com.kancolle.server.model.po.ship.Ship;

import java.util.List;
import java.util.Map;

public interface ShipMapper {

    List<Ship> selectShipsByCond();

    List<BaseShip> selectEmShip();

    int selectCountOfShipTypes();

    long selectShipNeedExpByLevel(int nowLv);

    int selectShipLVByExp(long nowExp);

    Ship selectShipsByCond(int ship_id);

    void replaceShip(Map<String, Object> model);

    void insertBaseShip(Map<String, Object> stringObjectMap);
}
