package com.kancolle.server.service.battle.shelling.impl;

import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;

import java.util.Arrays;

/**
 * Package: com.kancolle.server.service.battle.shelling
 * Author: mac
 * Date: 16/4/28
 */
public abstract class ShellingTemplate<T extends IShip, E extends IShip> {

    public void generateHougkeResult(T attackShip, BattleContext context) {

        E defendShip = chooseTargetShip(attackShip, context);

        int[] damages = generateDamageResult(attackShip, defendShip, context);

        callbackAfterDamage(attackShip, defendShip, Arrays.copyOf(damages, damages.length), context);
    }

    public int[] generateDamageResult(T attackShip, E defendShip, BattleContext context) {
        int attackType = chooseAttackType(attackShip, defendShip);

        int[] damages = attackTwice(attackType) ?
                generateTwiceDamageResult(attackShip, defendShip, context) :
                generateOnceDamageResult(attackShip, defendShip, context);

        augmentingDamage(attackShip, defendShip, damages, context);

        return damages;
    }

    protected abstract E chooseTargetShip(T attackShip, BattleContext context);

    protected abstract int chooseAttackType(T attackShip, E defendShip);

    protected abstract void augmentingDamage(T attackShip, E defendShip, int[] damages, BattleContext context);

    protected abstract void callbackAfterDamage(T attackShip, E defendShip, int[] damages, BattleContext context);

    private boolean attackTwice(int attackType) {
        return attackType == BaseShipShellingSystem.ATTACK_TYPE_DOUBLE;
    }

    protected abstract int[] generateOnceDamageResult(T attackShip, E defendShip, BattleContext context);

    protected abstract int[] generateTwiceDamageResult(T attackShip, E defendShip, BattleContext context);

}
