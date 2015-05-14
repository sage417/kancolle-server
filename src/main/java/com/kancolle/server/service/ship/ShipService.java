package com.kancolle.server.service.ship;

import com.kancolle.server.model.kcsapi.member.MemberShip;

public interface ShipService {

    MemberShip getMemberShip(String member_id, long ship_id);

}
