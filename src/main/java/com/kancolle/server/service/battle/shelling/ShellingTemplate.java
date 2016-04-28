package com.kancolle.server.service.battle.shelling;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;

/**
 * Package: com.kancolle.server.service.battle.shelling
 * Author: mac
 * Date: 16/4/28
 */
public abstract class ShellingTemplate {

    public final void generateShellingResult(IShip attackShip, IShip defendShip, BattleContext context){

    }


    protected abstract int getAttackType(IShip attackShip, BattleContext context);

    protected abstract int getBaiscHoug(IShip attackShip);

    /**
     * 获得火力阈值前攻击力
     * @param attackShip
     * @param context
     * @return
     */
    protected abstract int getAttackValueBeforeThreshold(IShip attackShip, BattleContext context);

    protected abstract int getDameage(IShip attackShip, IShip defendShip, BattleContext context);
}
