/**
 * 
 */
package com.kancolle.server.service.battle;

/**
 * @author J.K.SAGE
 * @Date 2015年10月6日
 *
 */
public abstract class FormationSystem {
    //1=単縦陣 2=複縦陣, 3=輪形陣, 4=梯形陣, 5=単横陣
    public static final int FORMATION_1 = 1;
    public static final int FORMATION_2 = 2;
    public static final int FORMATION_3 = 3;
    public static final int FORMATION_4 = 4;
    public static final int FORMATION_5 = 5;

    public static double getHoumAddition(int formation) {
        switch (formation) {
        case FORMATION_2:
        case FORMATION_4:
        case FORMATION_5:
            return 5 / 4;
        case FORMATION_1:
        case FORMATION_3:
        default:
            return 1;
        }
    }

    public static double getHoukAddition(int formation) {
        switch (formation) {
        case FORMATION_4:
        case FORMATION_5:
            return 21 / 20;
        case FORMATION_1:
        case FORMATION_2:
        case FORMATION_3:
        default:
            return 1;
        }
    }
}
