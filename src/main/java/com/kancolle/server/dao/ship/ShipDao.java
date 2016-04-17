package com.kancolle.server.dao.ship;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.ship.BaseShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.ship.ShipType;

import java.util.List;

public interface ShipDao extends BaseDao<Ship> {

    List<Ship> selectShips();

    List<BaseShip> selectEmShip();

    List<ShipType> selectShipTypes();

    int selectCountOfShipTypes();

    long selectShipNeedExpByLevel(int after_lv);

    int getShipLVByExp(long after_exp);

    ShipType selectShipTypeByCond(int typeId);

    Ship selectShipByCond(int ship_id);
}
