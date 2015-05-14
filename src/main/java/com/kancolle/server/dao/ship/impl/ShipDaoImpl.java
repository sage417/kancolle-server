package com.kancolle.server.dao.ship.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.model.kcsapi.member.MemberShip;

@Repository
public class ShipDaoImpl extends BaseDaoImpl<MemberShip> implements ShipDao {

    @Override
    public MemberShip getMemberShip(String member_id, long ship_id) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("member_id", member_id);
        params.put("ship_id", ship_id);
        return queryForSingleModel(MemberShip.class, "SELECT * FROM v_member_ship WHERE member_id = :member_id AND ID = :ship_id", params);
    }

}
