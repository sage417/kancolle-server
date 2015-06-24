package com.kancolle.server.dao.ship;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;

public interface ShipDao extends BaseDao<MemberShip> {

    Ship getShipById(int ship_id);

    /**
     * @param afterLv
     * @return
     */
    long getNeedExpByLevel(int afterLv);

    /**
     * @param afterExp
     * @return
     */
    int getShipLVByExp(long afterExp);

    int selectCountOfMemberShip(String member_id);

}
