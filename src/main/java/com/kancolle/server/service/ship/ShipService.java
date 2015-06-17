package com.kancolle.server.service.ship;

import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;

public interface ShipService {

    Ship getShipById(int ship_id);

    MemberShip getMemberShip(String member_id, long ship_id);

    /** 舰娘获得经验 */
    void increaseMemberShipExp(MemberShip memberShip, int exp);

    long getSumExpByLevel(int level);

    int getCountOfMemberShip(String member_id);

    /** 消耗油弹 */
    void consume(MemberShip memberShip, boolean fuel, boolean bull);

    /** 补给 */
    void charge(MemberShip memberShip, boolean fuel, boolean bull);
}
