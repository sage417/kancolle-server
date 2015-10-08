/**
 * 
 */
package com.kancolle.server.utils.logic;

import java.math.RoundingMode;

import com.google.common.math.LongMath;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
public class NdockUtils {

    private static float getTimeOverride(int shipType) {
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

    private static float getFuelOverride(int shipType) {
        switch (shipType) {
        case 2:
            return 0.48f;
        case 3:
        case 4:
            return 0.9f;
        case 5:
            return 1.28f;
        case 6:
            return 1.6f;
        case 7:
            return 1.12f;
        case 8:
            return 2.3f;
        case 9:
            return 4.16f;
        case 10:
            return 3.04f;
        case 11:
            return 2.08f;
        case 12:
            return 2.72f;
        case 13:
            return 0.32f;
        case 14:
            return 2.88f;
        case 16:
            return 1.12f;
        case 17:
            return 1.44f;
        case 18:
            return 2.88f;
        case 19:
            return 1.76f;
        case 20:
            return 2.88f;
        case 21:
            return 1.12f;
        default:
            return 1.0f;
        }
    }

    private static float getSteelOverride(int shipType) {
        switch (shipType) {
        case 2:
            return 1.0f;
        case 3:
        case 4:
            return 1.5f;
        case 5:
            return 2.4f;
        case 6:
            return 3.0f;
        case 7:
            return 2.4f;
        case 8:
            return 6.0f;
        case 9:
            return 8.0f;
        case 10:
            return 6.0f;
        case 11:
            return 4.0f;
        case 12:
            return 5.0f;
        case 13:
            return 0.6f;
        case 14:
            return 1.0f;
        case 16:
            return 2.5f;
        case 17:
            return 2.7f;
        case 18:
            return 5.4f;
        case 19:
            return 3.3f;
        case 20:
            return 5.4f;
        case 21:
            return 2.1f;
        default:
            return 1.0f;
        }
    }

    public static long getNdockTime(int nowLv, int loseHp, int shipType) {
        long ndockTime = 0L;
        if (loseHp == 0) {
            return ndockTime;
        }
        if (nowLv > 11) {
            ndockTime = (nowLv * 5L + LongMath.sqrt((nowLv - 11) * 10L + 50, RoundingMode.DOWN));
        } else {
            ndockTime = (nowLv * 10L);
        }
        ndockTime = (long) (ndockTime * getTimeOverride(shipType) * loseHp + 30L);
        return ndockTime;
    }

    public static int[] getNdockItem(int loseHp, int shipType) {
        return new int[] { (int) (loseHp * getFuelOverride(shipType)), (int) (loseHp * getSteelOverride(shipType)) };
    }
}
