package com.kancolle.server.dao.ship.impl;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.model.po.ship.Ship;

@Repository
public class ShipDaoImpl extends BaseDaoImpl<Ship> implements ShipDao {

    @Override
    public Ship selectShipById(int ship_id) {
        return getSqlSession().selectOne("selectShipById", ship_id);
    }

    @Override
    public void update(Ship ship) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getNeedExpByLevel(int nowLv) {
        return getSqlSession().selectOne("selectNeedShipExpByLevel", nowLv);
    }

    @Override
    public int getShipLVByExp(long nowExp) {
        return getSqlSession().selectOne("selectShipLVByExp", nowExp);
    }

}
