package com.kancolle.server.utils.logic.battle;

import com.kancolle.server.model.po.battle.BattleContext;

/**
 * Created by J.K.SAGE on 2016/1/17.
 */
public abstract class BattleContextUtils {

    public static int getMemberFormation(BattleContext context){
        int[] formationArray = getFormationArray(context);
        return formationArray[0];
    }

    public static int getEnemyFormation(BattleContext context){
        int[] formationArray = getFormationArray(context);
        return formationArray[1];
    }

    public static int getBattleCourse(BattleContext context){
        int[] formationArray = getFormationArray(context);
        return formationArray[2];
    }

    private static int[] getFormationArray(BattleContext context){
        return context.getBattleResult().getApi_formation();
    }
}
