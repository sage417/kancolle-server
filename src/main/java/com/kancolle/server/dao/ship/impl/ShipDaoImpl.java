package com.kancolle.server.dao.ship.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.model.kcsapi.member.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

@Repository
public class ShipDaoImpl extends BaseDaoImpl<MemberShip> implements ShipDao {

    @Override
    public MemberShip getMemberShip(String member_id, long ship_id) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("member_id", member_id);
        params.put("ship_id", ship_id);
        return queryForSingleModel(MemberShip.class, "SELECT * FROM v_member_ship WHERE member_id = :member_id AND ID = :ship_id", params);
    }

    /* (non-Javadoc)
     * @see com.kancolle.server.dao.ship.ShipDao#getShipById(int)
     */
    @Override
    public Ship getShipById(int ship_id) {
        return getSqlSession().selectOne("selectShipById", ship_id);
    }

    /* (non-Javadoc)
     * @see com.kancolle.server.dao.ship.ShipDao#getMemberShip2(java.lang.String, long)
     */
    @Override
    public com.kancolle.server.model.po.ship.MemberShip getMemberShip2(String member_id, long ship_id) {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("member_id", member_id);
        params.put("ship_id", ship_id);
        com.kancolle.server.model.po.ship.MemberShip memberShip =  getSqlSession().selectOne("selectMemberShipByCond", params);
        String slotitem_ids = getSqlSession().selectOne("selectMemberShipSlotId", params);
        params.put("slotitem_ids", JSON.parseArray(slotitem_ids, Long.class));
        List<MemberSlotItem> slotItems = getSqlSession().selectList("selectMemberShipSlot", params);
        memberShip.setSlot(slotItems);
        return memberShip;
    }
}
