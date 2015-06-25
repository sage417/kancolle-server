package com.kancolle.server.dao.ship;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.ship.Ship;

public interface ShipDao extends BaseDao<Ship> {

    long getNeedExpByLevel(int after_lv);

    int getShipLVByExp(long after_exp);
}
