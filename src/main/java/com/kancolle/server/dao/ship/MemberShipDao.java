package com.kancolle.server.dao.ship;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

public interface MemberShipDao extends BaseDao<MemberShip> {

    MemberShip selectMemberShip(String member_id, Long ship_id);

    List<MemberShip> selectMemberShips(String member_id);

    int selectCountOfMemberShips(String member_id);

    void chargeMemberShips(String member_id, List<Long> memberShip_ids, int charge_kind);

    void updateMemberShipHpAndCond(MemberShip memberShip);

    void updateMemberExp(MemberShip memberShip);

    void addSlot(MemberShip memberShip, MemberSlotItem memberSlotItem);

    void removeSlot(MemberShip memberShip, List<MemberSlotItem> memberSlotItems);

    void replaceSlot(MemberShip memberShip, MemberSlotItem repalcedSlotItem, MemberSlotItem memberSlotItem);

    void updateMemberShipSlotValue(MemberShip memberShip);

    void updateMemberShipLockStatue(String member_id, Long member_ship_id, Boolean lock);

    void deleteMemberShips(String member_id, List<Long> member_ship_ids);

    MemberShip createShip(String member_id, int createShipId);

    void updateShipOnSlot(String memberId, long memberShipId, int[] onslot);
}
