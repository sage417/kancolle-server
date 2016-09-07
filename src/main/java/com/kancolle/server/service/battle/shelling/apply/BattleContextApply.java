package com.kancolle.server.service.battle.shelling.apply;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;

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
     * 获取当前舰队
     *
     * @param context
     * @return
     */
    List<? extends IShip> getCurrentShips(final BattleContext context);
}
