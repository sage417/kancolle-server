/**
 * 
 */
package com.kancolle.server.service.mission.utils;

import com.kancolle.server.model.po.deckport.MemberDeckPort;

import java.util.Arrays;

/**
 * @author J.K.SAGE
 * @Date 2015年7月7日
 *
 */
public class MissionUtils {

    public static int[] getShipExps(MemberDeckPort deckport, int ship_exp) {
        int[] ship_exps = new int[deckport.getShips().size()];
        Arrays.fill(ship_exps, ship_exp);
        ship_exps[0] = ship_exp * 3 / 2;
        return ship_exps;
    }
}
