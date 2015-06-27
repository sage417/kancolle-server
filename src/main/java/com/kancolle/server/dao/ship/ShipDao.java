package com.kancolle.server.dao.ship;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.ship.ShipType;

public interface ShipDao extends BaseDao<Ship> {

    List<ShipType> selectShipTypes();

    int selectCountOfShipTypes();

    long getNeedExpByLevel(int after_lv);

    int getShipLVByExp(long after_exp);

}
