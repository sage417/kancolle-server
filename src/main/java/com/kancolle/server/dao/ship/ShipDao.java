package com.kancolle.server.dao.ship;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.member.MemberShip;
import com.kancolle.server.model.po.ship.Ship;

public interface ShipDao extends BaseDao<MemberShip> {

    MemberShip getMemberShip(String member_id, long ship_id);
    
    Ship getShipById(int ship_id);

    /**
     * @param member_id
     * @param ship_id
     * @return
     */
    com.kancolle.server.model.po.ship.MemberShip getMemberShip2(String member_id, long ship_id);

}