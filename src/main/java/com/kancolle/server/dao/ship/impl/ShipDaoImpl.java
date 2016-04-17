package com.kancolle.server.dao.ship.impl;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.model.po.ship.BaseShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.ship.ShipType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShipDaoImpl extends BaseDaoImpl<Ship> implements ShipDao {

    @Override
    public void update(Ship ship) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Ship> selectShips() {
        return getSqlSession().selectList("selectShipsByCond");
    }

    @Override
    public List<BaseShip> selectEmShip() {
        return getSqlSession().selectList("selectEmShip");
    }

    @Override
    public List<ShipType> selectShipTypes() {
        return getSqlSession().selectList("selectShipTypes");
    }

    @Override
    public int selectCountOfShipTypes() {
        return getSqlSession().selectOne("selectCountOfShipTypes");
    }

    @Override
    public long selectShipNeedExpByLevel(int nowLv) {
        return getSqlSession().selectOne("selectShipNeedExpByLevel", nowLv);
    }

    @Override
    public int getShipLVByExp(long nowExp) {
        return getSqlSession().selectOne("selectShipLVByExp", nowExp);
    }

    @Override
    public ShipType selectShipTypeByCond(int type_id) {
        return getSqlSession().selectOne("selectShipTypeByCond", type_id);
    }

    @Override
    public Ship selectShipByCond(int ship_id) {
        return getSqlSession().selectOne("selectShipsByCond", ship_id);
    }
}
