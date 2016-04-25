package com.kancolle.server.dao.ship.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.ship.MemberShipDao;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.model.po.slotitem.SlotItem;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MemberShipDaoImpl extends BaseDaoImpl<MemberShip> implements MemberShipDao {

    @Override
    public void update(MemberShip memberShip, String... columns) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("member_ship", memberShip);
        params.put("columns", columns);
        getSqlSession().update("updateMemberShip", params);
    }

    @Override
    public MemberShip selectMemberShip(String member_id, Long ship_id) {
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
        params.put("member_ship_ids", memberShip_ids);
        params.put("charge_kind", charge_kind);
        getSqlSession().update("chargeMemberShips", params);
    }

    @Override
    public void updateMemberExp(MemberShip memberShip) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(4);
        params.put("member_id", memberShip.getMemberId());
        params.put("member_ship_id", memberShip.getMemberShipId());
        params.put("lv", memberShip.getLv());
        params.put("exp", memberShip.getExp());
        getSqlSession().update("updateMemberExp", params);
    }

    private void updateSlot(MemberShip memberShip) {
        List<MemberSlotItem> slotItems = memberShip.getSlot();
        List<Long> slot = slotItems.stream()
                .map(MemberSlotItem::getMemberSlotItemId)
                .collect(Collectors.toList());

        int slotLength = slotItems.stream()
                .map(MemberSlotItem::getSlotItem)
                .mapToInt(SlotItem::getLeng)
                .max().orElse(0);

        int memberShipLength = Math.max(slotLength, memberShip.getShip().getLeng());

        boolean lockedEquip = slotItems.stream().anyMatch(MemberSlotItem::getLocked);

        while (slot.size() < MemberShip.SLOT_SIZE_MAX) {
            slot.add(-1L);
        }
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(5);
        params.put("member_id", memberShip.getMemberId());
        params.put("member_ship_id", memberShip.getMemberShipId());
        params.put("length", memberShipLength);
        params.put("lockedEquip", lockedEquip);
        params.put("slot", generateJsonArray(slot));
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
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(10);
        params.put("member_id", memberShip.getMemberId());
        params.put("member_ship_id", memberShip.getMemberShipId());
        params.put("soukou", memberShip.getSoukou());
        params.put("karyoku", memberShip.getKaryoku());
        params.put("raisou", memberShip.getRaisou());
        params.put("taiku", memberShip.getTaiku());
        params.put("taisen", memberShip.getTaisen());
        params.put("kaihi", memberShip.getKaihi());
        params.put("sakuteki", memberShip.getSakuteki());
        params.put("luck", memberShip.getLucky());
        params.put("kyouka", JSON.toJSONString(memberShip.getKyouka()));
        getSqlSession().update("updateMemberShipValue", params);
    }

    @Override
    public void updateMemberShipLockStatue(String member_id, Long member_ship_id, Boolean lock) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("member_id", member_id);
        params.put("member_ship_id", member_ship_id);
        params.put("lock", lock);
        getSqlSession().update("updateMemberShipLockStatue", params);
    }

    @Override
    public void deleteMemberShips(String member_id, List<Long> member_ship_ids) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("member_ship_ids", member_ship_ids);
        getSqlSession().update("deleteMemberShips", params);
    }

    @Override
    public void updateMemberShipHpAndCond(MemberShip memberShip) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(4);
        params.put("member_id", memberShip.getMemberId());
        params.put("member_ship_id", memberShip.getMemberShipId());
        params.put("nowHp", memberShip.getNowHp());
        params.put("cond", memberShip.getCond());
        getSqlSession().update("updateMemberShipHpAndCond", params);
    }

    @Override
    public MemberShip createShip(String member_id, int createShipId) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("create_ship_id", createShipId);
        return getSqlSession().selectOne("createMemberShip", params);
    }

    @Override
    public void updateShipOnSlot(String memberId, long memberShipId, int[] onslot) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("member_id", memberId);
        params.put("member_ship_id", memberShipId);
        params.put("onslot", onslot);
        getSqlSession().update("updateShipOnSlot", params);
    }
}
