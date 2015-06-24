package com.kancolle.server.service.ship;

import com.kancolle.server.controller.kcsapi.form.ship.ShipChargeForm;
import com.kancolle.server.model.kcsapi.charge.ChargeModel;
import com.kancolle.server.model.po.ship.MemberShip;

public interface ShipService {

    /** 舰娘获得经验 */
    void increaseMemberShipExp(MemberShip memberShip, int exp);

    long getSumExpByLevel(int level);

    int getCountOfMemberShip(String member_id);

    /** 消耗油弹 */
    void consume(MemberShip memberShip, boolean fuel, boolean bull);

    /**
     * 补给
     * 
     * @param form
     * @return
     */
    ChargeModel chargeShips(String member_id, ShipChargeForm form);
}
