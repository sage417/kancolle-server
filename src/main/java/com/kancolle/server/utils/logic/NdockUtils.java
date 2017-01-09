/**
 *
 */
package com.kancolle.server.utils.logic;


import com.google.common.math.DoubleMath;
import com.kancolle.server.model.po.ship.MemberShip;

import java.math.RoundingMode;

import static com.kancolle.server.model.po.ship.ShipType.*;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 */
public abstract class NdockUtils {

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

    public static int[] getNdockItem(final MemberShip memberShip) {
        double base = (memberShip.getMaxHp() - memberShip.getNowHp()) * memberShip.getShip().getFuelMax();
        return new int[]{DoubleMath.roundToInt(base * 0.032d, RoundingMode.FLOOR), DoubleMath.roundToInt(base * 0.06d, RoundingMode.FLOOR)};
    }
}
