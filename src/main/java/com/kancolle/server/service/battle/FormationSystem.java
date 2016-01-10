/**
 *
 */
package com.kancolle.server.service.battle;

/**
 * @author J.K.SAGE
 * @Date 2015年10月6日
 */
public abstract class FormationSystem {
    //1=単縦陣 2=複縦陣, 3=輪形陣, 4=梯形陣, 5=単横陣
    public static final int FORMATION_1 = 1;
    public static final int FORMATION_2 = 2;
    public static final int FORMATION_3 = 3;
    public static final int FORMATION_4 = 4;
    public static final int FORMATION_5 = 5;

    public static double shellingAugmenting(int formation) {
        switch (formation) {
            case FORMATION_1:
                return 1d;
            case FORMATION_2:
                return 0.8d;
            case FORMATION_3:
                return 0.7d;
            case FORMATION_4:
            case FORMATION_5:
                return 0.6d;
            default:
                throw new IllegalArgumentException("formation argment error");
        }
    }

    public static double getHoukAddition(int formation) {
        switch (formation) {
            case FORMATION_1:
                return 0.6d;
            case FORMATION_2:
                return 0.8d;
            case FORMATION_3:
                return 1.2d;
            case FORMATION_4:
                return 1d;
            case FORMATION_5:
                return 1.3d;
            default:
                throw new IllegalArgumentException("formation argment error");
        }
    }
}
