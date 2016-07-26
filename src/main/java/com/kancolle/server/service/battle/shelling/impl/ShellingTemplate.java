package com.kancolle.server.service.battle.shelling.impl;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;

/**
 * Package: com.kancolle.server.service.battle.shelling
 * Author: mac
 * Date: 16/4/28
 */
public abstract class ShellingTemplate<T extends IShip, E extends IShip> {

    public void generateHougkeResult(T attackShip, BattleContext context) {
        prepareContext(context);

        E defendShip = chooseTargetShip(attackShip, context);
        defendShip = callbackAfterChooseTargetShip(defendShip, context);

        int[] damages = generateDamageResult(attackShip, defendShip, context);
        callbackAfterDamage(attackShip, defendShip, damages, context);
    }

    /**
     * 准备战斗上下文
     *
     * @param context
     */
    protected abstract void prepareContext(BattleContext context);

    private E chooseTargetShip(T attackShip, BattleContext context) {

    }

    protected abstract E callbackAfterChooseTargetShip(E defendShip, BattleContext context);

    private int[] generateDamageResult(T attackShip, E defendShip, BattleContext context) {
        int attackType = chooseAttackType(attackShip, defendShip);

        int[] damages = attackTwice(attackType) ?
                generateTwiceDamageResult(attackShip, defendShip, context) :
                generateOnceDamageResult(attackShip, defendShip, context);

        augmentingDamage(attackShip, defendShip, damages, context);

        return damages;
    }

    protected abstract int chooseAttackType(T attackShip, E defendShip);

    protected abstract void augmentingDamage(T attackShip, E defendShip, int[] damages, BattleContext context);

    protected abstract void callbackAfterDamage(T attackShip, E defendShip, int[] damages, BattleContext context);

    private boolean attackTwice(int attackType) {
        return attackType == BaseShipShellingSystem.ATTACK_TYPE_DOUBLE;
    }

    protected abstract int[] generateOnceDamageResult(T attackShip, E defendShip, BattleContext context);

    protected abstract int[] generateTwiceDamageResult(T attackShip, E defendShip, BattleContext context);
}
