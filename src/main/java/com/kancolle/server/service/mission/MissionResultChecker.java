/**
 * 
 */
package com.kancolle.server.service.mission;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.mission.utils.MissionCondResult;

/**
 * @author J.K.SAGE
 * @Date 2015年7月29日
 *
 */
public interface MissionResultChecker {

    default MissionCondResult getResult(MemberDeckPort deckport) {
        List<MemberShip> ships = deckport.getShips();
        if (checkCond(deckport)) {
            long goodcond = ships.stream().mapToInt(MemberShip::getCond).filter(cond -> cond > 49).count();
            if (goodcond > 0L && RandomUtils.nextInt(0, 7) <= goodcond)
                return MissionCondResult.GREATE_SUCCESS;
            return MissionCondResult.SUCCESS;
        }
        return MissionCondResult.FAIL;
    }

    boolean checkCond(MemberDeckPort deckport);

}
