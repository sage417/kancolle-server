/**
 * 
 */
package com.kancolle.server.service.mission;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Maps;
import com.kancolle.server.model.po.member.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.mission.impl.Mission1ResultChecker;
import com.kancolle.server.service.mission.utils.MissionCondResult;

/**
 * @author J.K.SAGE
 * @Date 2015年7月6日
 *
 */
public abstract class MissionResultChecker {
    private static final Map<Integer, MissionResultChecker> checkers;
    protected static final Random r = new Random();

    static {
        checkers = Maps.newHashMapWithExpectedSize(40);
        checkers.put(Integer.valueOf(1), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(2), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(3), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(4), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(5), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(6), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(7), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(8), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(9), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(10), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(11), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(12), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(13), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(14), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(15), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(16), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(17), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(18), Mission1ResultChecker.getInstance());
        checkers.put(Integer.valueOf(19), Mission1ResultChecker.getInstance());
    }

    public static MissionResultChecker getMissionResultChecker(Integer mission_id) {
        return checkers.get(mission_id);
    }

    public final MissionCondResult getResult(MemberDeckPort deckport) {
        List<MemberShip> ships = deckport.getShips();
        if (checkCond(deckport)) {
            long goodcond = ships.stream().mapToInt(MemberShip::getCond).filter(cond -> cond > 49).count();
            if (goodcond > 0 && (r.nextInt(5) + 1) <= goodcond)
                return MissionCondResult.GREATE_SUCCESS;
            return MissionCondResult.SUCCESS;
        }
        return MissionCondResult.FAIL;
    }

    protected abstract boolean checkCond(MemberDeckPort deckport);
}
