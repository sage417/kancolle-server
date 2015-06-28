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
        int memberShipLength = Math.max(length.orElse(0), memberShip.getShip().getLeng());

        boolean lockedEquip = memberShip.getSlot().stream().filter(MemberSlotItem::getLocked).count() > 0L;

        while (slot.size() < MemberShip.SLOT_SIZE_MAX) {
            slot.add(Long.valueOf(-1L));
        }
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(5);
        params.put("member_id", memberShip.getMemberId());
        params.put("member_ship_id", memberShip.getMemberShipId());
        params.put("leng", memberShipLength);
        params.put("lockedEquip", lockedEquip);
        params.put("slot", JSON.toJSONString(slot));
        getSqlSession().update("updateMemberShipSlot", params);
    }

    @Override
    public void addSlot(MemberShip memberShip, MemberSlotItem memberSlotItem) {
        updateSlot(memberShip);
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("member_id", memberShip.getMemberId());
        params.put("member_ship_id", memberShip.getMemberShipId());
        params.put("member_slotitem_id", memberSlotItem.getMemberSlotItemId());
        getSqlSession().insert("insertMemberSlotItemMapping", params);
    }

    @Override
    public void removeSlot(MemberShip memberShip, List<MemberSlotItem> memberSlotItem) {
        updateSlot(memberShip);
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("member_id", memberShip.getMemberId());
        params.put("member_ship_id", memberShip.getMemberShipId());
        params.put("member_slotitem_ids", memberSlotItem);
        getSqlSession().delete("deleteMemberSlotItemMapping", params);
    }

    @Override
    public void replaceSlot(MemberShip memberShip, MemberSlotItem removedMemberSlotItem, MemberSlotItem replaceMemberSlotItem) {
        updateSlot(memberShip);
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(5);
        params.put("member_id", memberShip.getMemberId());
        params.put("member_ship_id", memberShip.getMemberShipId());
        params.put("removed_slotitem_id", removedMemberSlotItem.getMemberSlotItemId());
        params.put("replace_slotitem_id", replaceMemberSlotItem.getMemberSlotItemId());
        getSqlSession().update("updateMemberSlotItemMapping", params);
    }

    @Override
    public void updateMemberShipSlotValue(MemberShip memberShip) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(9);
        params.put("member_id", memberShip.getMemberId());
        params.put("member_ship_id", memberShip.getMemberShipId());
        params.put("soukou", memberShip.getSoukou());
        params.put("karyoku", memberShip.getKaryoku());
        params.put("raisou", memberShip.getRaisou());
        params.put("taiku", memberShip.getTaiku());
        params.put("taisen", memberShip.getTaisen());
        params.put("kaihi", memberShip.getKaihi());
        params.put("sakuteki", memberShip.getSakuteki());
        getSqlSession().update("updateMemberShipSlotValue", params);
    }
}
