package com.kancolle.server.service.ship;

import com.kancolle.server.model.kcsapi.member.MemberShip;
import com.kancolle.server.model.po.ship.Ship;

public interface ShipService {

    MemberShip getMemberShip(String member_id, long ship_id);
    
    Ship getShipById(int ship_id);

}
