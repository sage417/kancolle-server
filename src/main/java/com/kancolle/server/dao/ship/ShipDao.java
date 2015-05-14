package com.kancolle.server.dao.ship;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.member.MemberShip;

public interface ShipDao extends BaseDao<MemberShip> {

    MemberShip getMemberShip(String member_id, long ship_id);

}
