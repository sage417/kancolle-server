package com.kancolle.server.service.battle;

import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.ship.MemberShip;

public interface IShellingSystem {

    void generateHougkeResult(MemberShip attackShip, int defShipIdx, HougekiResult result, int aerialState);

}
