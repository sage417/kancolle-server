package com.kancolle.server.utils.logic.battle;

import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;

import static com.google.common.collect.Iterables.getLast;

/**
 * Created by J.K.SAGE on 2016/1/17.
 */
public abstract class BattleContextUtils {

    private static final int MEMBER_FORMATION_INDEX = 0;

    private static final int ENEMY_FORMATION_INDEX = 1;

    private static final int COURSE_INDEX = 2;

    public static int getMemberFormation(BattleContext context) {
        int[] formationArray = getFormationArray(context);
        return formationArray[MEMBER_FORMATION_INDEX];
    }

    public static int getEnemyFormation(BattleContext context) {
        int[] formationArray = getFormationArray(context);
        return formationArray[ENEMY_FORMATION_INDEX];
    }

    public static int getBattleCourse(BattleContext context) {
        int[] formationArray = getFormationArray(context);
        return formationArray[COURSE_INDEX];
    }

    public static int getCurrentAttackType(BattleContext context){
        HougekiResult hougekiResult = context.getNowHougekiResult();
        return getLast(hougekiResult.getApi_at_type());
    }

    private static int[] getFormationArray(BattleContext context) {
        return context.getBattleResult().getApi_formation();
    }
}
