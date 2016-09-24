package com.kancolle.server.utils.logic.battle;

import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.UnderSeaShip;

import static com.google.common.collect.Iterables.getLast;

/**
 * Created by J.K.SAGE on 2016/1/17.
 */
public abstract class BattleContextUtils {

    private static final int MEMBER_FORMATION_INDEX = 0;

    private static final int ENEMY_FORMATION_INDEX = 1;

    private static final int COURSE_INDEX = 2;

    public static final int MEMBER_SHIP_FLAGSHIP_IDX = 1;

    public static final int UNDERSEA_SHIP_FLAGSHIP_IDX = 7;

    public static int getMemberFormation(final BattleContext context) {
        final int[] formationArray = getFormationArray(context);
        return formationArray[MEMBER_FORMATION_INDEX];
    }

    public static int getUnderSeaFormation(final BattleContext context) {
        final int[] formationArray = getFormationArray(context);
        return formationArray[ENEMY_FORMATION_INDEX];
    }

    public static int getBattleCourse(final BattleContext context) {
        final int[] formationArray = getFormationArray(context);
        return formationArray[COURSE_INDEX];
    }

    public static int getCurrentAttackType(final BattleContext context) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();
        return getLast(hougekiResult.getApi_at_type());
    }

    private static int[] getFormationArray(final BattleContext context) {
        return context.getBattleResult().getApi_formation();
    }

    /**
     * 判断是否是旗舰
     *
     * @param ship
     * @param context
     * @return
     */
    public static final boolean isFlagShip(final IShip ship, final BattleContext context) {
        int idx = context.getShipMap().inverse().get(ship);
        if (ship instanceof MemberShip) {
            return idx == MEMBER_SHIP_FLAGSHIP_IDX;
        } else if (ship instanceof UnderSeaShip) {
            return idx == UNDERSEA_SHIP_FLAGSHIP_IDX;
        }
        return false;
    }
}
