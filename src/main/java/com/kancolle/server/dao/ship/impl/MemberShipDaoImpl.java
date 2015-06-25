package com.kancolle.server.dao.ship.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.ship.MemberShipDao;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

@Repository
public class MemberShipDaoImpl extends BaseDaoImpl<MemberShip> implements MemberShipDao {

    @Override
    @Deprecated
    public void update(MemberShip memberShip) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MemberShip selectMemberShip(String member_id, long ship_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("ship_id", ship_id);
        return getSqlSession().selectOne("selectMemberShipByCond", params);
    }

    @Override
    public List<MemberShip> selectMemberShips(String member_id) {
        return getSqlSession().selectList("selectMemberShipByCond", Collections.singletonMap("member_id", member_id));
    }

    @Override
    public int selectCountOfMemberShips(String member_id) {
        return getSqlSession().selectOne("selectCountOfMemberShip", member_id);
    }

    @Override
    public void chargeMemberShips(String member_id, List<Long> memberShip_ids, int charge_kind) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("member_id", member_id);
        params.put("memberShip_ids", memberShip_ids);
        params.put("charge_kind", charge_kind);
        getSqlSession().update("chargeMemberShips", params);
    }

    @Override
    public void updateMemberExp(MemberShip memberShip) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(8);
        params.put("member_id", memberShip.getMemberId());
        params.put("memberShp_id", memberShip.getMemberShipId());
        params.put("lv", memberShip.getLv());
        params.put("exp", memberShip.getExp());
        getSqlSession().update("updateMemberExp", params);
    }

    private void updateSlot(MemberShip memberShip) {
        List<Long> slot = memberShip.getSlot().stream().map(MemberSlotItem::getMemberSlotItemId).collect(Collectors.toList());

        OptionalInt length = memberShip.getSlot().stream().mapToInt(memberSlotItem -> memberSlotItem.getSlotItem().getLeng()).max();

        boolean lockedEquip = memberShip.getSlot().stream().filter(memberSlotItem -> memberSlotItem.getLocked() == 1).count() > 0L;

        while (slot.size() < MemberShip.SLOT_SIZE_MAX) {
            slot.add(Long.valueOf(-1L));
        }
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("member_id", memberShip.getMemberId());
        params.put("member_ship_id", memberShip.getMemberShipId());
        params.put("length", length.getAsInt());
        params.put("lockedEquip", lockedEquip);
        params.put("slot", JSON.toJSONString(slot));
        getSqlSession().update("updateMemberShipSlot", params);
    }

    @Override
    public void addSlot(MemberShip memberShip, MemberSlotItem memberSlotItem) {
        updateSlot(memberShip);
        getSqlSession().insert("", parameter);
    }

    @Override
    public void removeSlot(MemberShip memberShip, MemberSlotItem slotItem) {
        updateSlot(memberShip);
        getSqlSession().delete(statement, parameter);
    }

    @Override
    public void replaceSlot(MemberShip memberShip, MemberSlotItem repalcedSlotItem, MemberSlotItem memberSlotItem) {
        updateSlot(memberShip);
        getSqlSession().insert(statement, parameter);
        getSqlSession().delete(statement, parameter);
    }
}
