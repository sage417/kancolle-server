/**
 * 
 */
package com.kancolle.server.utils.logic;

import com.kancolle.server.model.po.ship.MemberShip;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
public class NdockUtils {

    private static float getOverride(int shipType) {
        switch (shipType) {
        case 13:
            return 0.5f;
        case 5:
        case 6:
        case 7:
        case 8:
        case 14:
            return 1.5f;
        case 9:
        case 10:
        case 11:
        case 18:
        case 19:
            return 2.0f;
        default:
            return 1.0f;
        }
    }

    public static long getNdockTime(MemberShip memberShip) {
        int nowLv = memberShip.getLv();
        int missHp = memberShip.getMaxHp() - memberShip.getNowHp();

        long ndockTime = 0L;
        if (nowLv > 11) {
            ndockTime = (nowLv * 5L + (int) Math.sqrt(nowLv - 11) * 10L + 50);
        } else {
            ndockTime = (nowLv * 10L);
        }
        ndockTime = (long) (ndockTime * getOverride(memberShip.getShip().getType()) * missHp + 30L);
        return ndockTime;
    }
}
