package com.kancolle.server.service.battle.shelling.apply;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;

import java.util.List;

/**
 * Created by SAGE on 16/9/7.
 */
public interface BattleContextApply {

    /**
     * 获取当前制空情况
     *
     * @param context
     * @return
     */
    int getCurrentAerialState(final BattleContext context);

    /**
     * 获取当前阵型
     *
     * @param context
     * @return
     */
    int getCurrentFormation(final BattleContext context);

    /**
     * 获取敌舰队
     *
     * @param context
     * @return
     */
    <T extends IShip> List<T> getEnemyShips(BattleContext context);
}
