/**
 * 
 */
package com.kancolle.server.service.mission.utils;

import java.util.Arrays;

import com.kancolle.server.model.po.member.MemberDeckPort;
import com.kancolle.server.model.po.mission.Mission;
import com.kancolle.server.model.po.mission.MissionExp;

/**
 * @author J.K.SAGE
 * @Date 2015年7月7日
 *
 */
public class MissionUtils {

    public static int[] getShipExps(MemberDeckPort deckport, Mission mission) {
        MissionExp missionExp = mission.getMissionExp();
        int[] ship_exps = new int[deckport.getShips().size()];
        Arrays.fill(ship_exps, missionExp.getShipExp());
        ship_exps[0] = missionExp.getShipExp() * 3 / 2;
        return ship_exps;
    }
}
