package com.kancolle.server.service.battle.shelling.template;

import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;

/**
 * Package: com.kancolle.server.service.battle.shelling
 * Author: mac
 * Date: 16/4/28
 */
public abstract class ShellingTemplate<T extends IShip, E extends IShip> {

    public final void generateShellingResult(T attackShip, BattleContext context) {

        int attackType = getAttackType(attackShip, context);
        addToAttackTypeList(context, attackType);

        E defendShip = getDefendShip(context);

        boolean isHit = getIsHit(attackShip, defendShip, context);


    }

    protected abstract int getAttackType(T attackShip, BattleContext context);

    protected abstract E getDefendShip(BattleContext context);

    protected abstract boolean getIsHit(T attackShip, E defendShip, BattleContext context);

    private void addToAttackTypeList(BattleContext context, int attackType) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        hougekiResult.getApi_at_type().add(attackType);
    }

    private void addToDefendList(BattleContext context, int[] defArr) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        hougekiResult.getApi_df_list().add(defArr);
    }
}
