package com.kancolle.server.service.ship.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.service.ship.ShipService;

@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private ShipDao shipDao;

    @Cacheable(value = "ship", key = "#ship_id")
    @Override
    public Ship getShipById(int ship_id) {
        return shipDao.getShipById(ship_id);
    }

    /* (non-Javadoc)
     * @see com.kancolle.server.service.ship.ShipService#getMemberShip2(java.lang.String, long)
     */
    @Override
    public MemberShip getMemberShip(String member_id, long ship_id) {
        return shipDao.getMemberShip(member_id, ship_id);
    }
}
