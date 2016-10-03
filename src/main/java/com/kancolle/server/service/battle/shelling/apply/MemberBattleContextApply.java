package com.kancolle.server.service.battle.shelling.apply;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.UnderSeaShip;
import com.kancolle.server.utils.logic.battle.BattleContextUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SAGE on 16/9/7.
 */
@Component
public class MemberBattleContextApply implements BattleContextApply {

    @Override
    public int getCurrentAerialState(BattleContext context) {
        return context.getMemberAerialState();
    }

    @Override
    public int getCurrentFormation(BattleContext context) {
        return BattleContextUtils.getMemberFormation(context);
    }

    @Override
    public List<UnderSeaShip> getEnemyShips(BattleContext context) {
        return context.getUnderSeaShips();
    }
}
