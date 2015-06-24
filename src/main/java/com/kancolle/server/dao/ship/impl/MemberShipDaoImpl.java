package com.kancolle.server.dao.ship.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.ship.MemberShipDao;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

@Repository
public class MemberShipDaoImpl extends BaseDaoImpl<MemberShip> implements MemberShipDao {

    @Override
    public MemberShip getMemberShip(String member_id, long ship_id) {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("member_id", member_id);
        params.put("ship_id", ship_id);
        MemberShip memberShip = getSqlSession().selectOne("selectMemberShipByCond", params);
        if (memberShip != null) {
            String slotitem_ids = getSqlSession().selectOne("selectMemberShipSlotId", params);
            params.put("slotitem_ids", JSON.parseArray(slotitem_ids, Long.class));
            List<MemberSlotItem> slotItems = getSqlSession().selectList("selectMemberShipSlot", params);
            memberShip.setSlot(slotItems);
        }
        return memberShip;
    }

    @Override
    public List<MemberShip> selectMemberShips(String member_id) {
        return getSqlSession().selectList("selectMemberShipByCond", Collections.singletonMap("member_id", member_id));
    }
}
