package com.kancolle.server.dao.ship.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

@Repository
public class ShipDaoImpl extends BaseDaoImpl<MemberShip> implements ShipDao {

    @Override
    public Ship getShipById(int ship_id) {
        return getSqlSession().selectOne("selectShipById", ship_id);
    }

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
    public void update(MemberShip memberShip) {
        updateShipSlotItem(memberShip);
        super.update(memberShip);
    }

    /**
     * @param memberShip
     */
    private void updateShipSlotItem(MemberShip memberShip) {

        List<Long> slot_ids = memberShip.getSlot().stream().mapToLong(MemberSlotItem::getMemberSlotItemId).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        Stream.iterate(0, i -> i++).limit(6 - slot_ids.size()).forEach(index -> slot_ids.add(Long.valueOf(-1L)));

        String slot = JSON.toJSONString(slot_ids);

        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("memberShip", memberShip);
        params.put("slot", slot);
        getSqlSession().update("updateMemberShipSlot", params);
    }

    @Override
    public long getNeedExpByLevel(int nowLv) {
        return getSqlSession().selectOne("selectNeedShipExpByLevel", nowLv);
    }

    @Override
    public int getShipLVByExp(long nowExp) {
        return getSqlSession().selectOne("selectShipLVByExp", nowExp);
    }

    @Override
    public int selectCountOfMemberShip(String member_id) {
        return getSqlSession().selectOne("selectCountOfMemberShip", member_id);
    }

    @Override
    public List<MemberShip> selectMemberShips(String member_id) {
        return getSqlSession().selectList("selectMemberShipByCond", Collections.singletonMap("member_id", member_id));
    }
}
