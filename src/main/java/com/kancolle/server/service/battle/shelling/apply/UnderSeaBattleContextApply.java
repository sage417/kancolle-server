package com.kancolle.server.service.battle.shelling.apply;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.utils.logic.battle.BattleContextUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SAGE on 16/9/7.
 */
@Component
public class UnderSeaBattleContextApply implements BattleContextApply {
    @Override
    public int getCurrentAerialState(BattleContext context) {
        return context.getUnderSeaSakuteki();
    }

    @Override
    public int getCurrentFormation(BattleContext context) {
        return BattleContextUtils.getUnderSeaFormation(context);
    }

    @Override
    public List<MemberShip> getEnemyShips(final BattleContext context) {
        return context.getMemberShips();
    }
}
