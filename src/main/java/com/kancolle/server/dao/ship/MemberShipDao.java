package com.kancolle.server.dao.ship;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.ship.MemberShip;

public interface MemberShipDao extends BaseDao<MemberShip> {

    MemberShip selectMemberShip(String member_id, long ship_id);

    List<MemberShip> selectMemberShips(String member_id);

    int selectCountOfMemberShips(String member_id);

}
