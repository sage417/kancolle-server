/**
 *
 */
package com.kancolle.server.utils.logic;


import static com.kancolle.server.model.po.ship.ShipType.*;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 */
public abstract class NdockUtils {

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

    public static long getNdockTime(final int nowLv, final int loseHp, final int shipType) {
        int base;
        if (nowLv <= 12) base = 10 * nowLv;
        else if (nowLv < 100) {
            base = 120;
            int count = 0, bm = 2;
            for (int i = 13; i <= nowLv; i++) {
                base += 5;
                if (count++ >= bm) {
                    bm += 2;
                    count = 0;
                    base += 10;
                }
            }
        } else if (nowLv <= 128) base = 650 + 5 * (nowLv - 100);
        else if (nowLv < 150) {
            base = 800 + 5 * (nowLv - 128);
        } else base = 915;

        double mod;
        switch (shipType) {
            case TYPE_BB:
            case TYPE_BBV:
            case TYPE_CV:
            case TYPE_CVB:
            case TYPE_AR:
                mod = 2d;
                break;
            case TYPE_CA:
            case TYPE_CAV:
            case TYPE_FAST_BB:
            case TYPE_CVL:
            case TYPE_SSV:
                mod = 1.5d;
                break;
            case TYPE_SS:
                mod = .5d;
                break;
            default:
                mod = 1d;
                break;
        }

        return (long) (loseHp * base * mod) + 30L;
    }

    public static int[] getNdockItem(int loseHp, int shipType) {
        return new int[]{(int) (loseHp * getFuelOverride(shipType)), (int) (loseHp * getSteelOverride(shipType))};
    }
}
