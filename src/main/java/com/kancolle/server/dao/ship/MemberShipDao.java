package com.kancolle.server.dao.ship;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

public interface MemberShipDao extends BaseDao<MemberShip> {

    MemberShip selectMemberShip(String member_id, long ship_id);

    List<MemberShip> selectMemberShips(String member_id);

    int selectCountOfMemberShips(String member_id);

    void chargeMemberShips(String member_id, List<Long> memberShip_ids, int charge_kind);

    void updateMemberExp(MemberShip memberShip);

    void addSlot(MemberShip memberShip, MemberSlotItem memberSlotItem);

    void removeSlot(MemberShip memberShip, MemberSlotItem slotItem);

    void replaceSlot(MemberShip memberShip, MemberSlotItem repalcedSlotItem, MemberSlotItem memberSlotItem);

}
