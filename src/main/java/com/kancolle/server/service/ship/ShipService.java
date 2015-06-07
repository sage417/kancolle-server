package com.kancolle.server.service.ship;

import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;

public interface ShipService {

    Ship getShipById(int ship_id);

    MemberShip getMemberShip(String member_id, long ship_id);

    /** 舰娘获得经验 */
    MemberShip increaseMemberShipExp(MemberShip memberShip, int exp);

    long getSumExpByLevel(int level);
}
