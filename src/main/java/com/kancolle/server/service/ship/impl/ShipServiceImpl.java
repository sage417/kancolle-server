package com.kancolle.server.service.ship.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.model.kcsapi.member.MemberShip;
import com.kancolle.server.service.ship.ShipService;

@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private ShipDao shipDao;

    @Override
    public MemberShip getMemberShip(String member_id, long ship_id) {
        return shipDao.getMemberShip(member_id, ship_id);
    }
}
