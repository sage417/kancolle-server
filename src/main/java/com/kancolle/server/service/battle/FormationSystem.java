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

    public static double shelllingHougAugment(int formation) {
        switch (formation) {
            case FORMATION_1:
                return 0d;
            case FORMATION_2:
                return -0.2d;
            case FORMATION_3:
                return -0.3d;
            case FORMATION_4:
            case FORMATION_5:
                return -0.4d;
            default:
                throw new IllegalArgumentException("formation argument error, formation id = " + formation);
        }
    }

    public static double taiSenHougAugment(int formation) {
        switch (formation) {
            case FORMATION_1:
                return -0.5d;
            case FORMATION_2:
                return -0.4d;
            case FORMATION_3:
                return -0.2d;
            case FORMATION_4:
                return -0.2d;
            case FORMATION_5:
                return 0d;
            default:
                throw new IllegalArgumentException("formation argument error, formation id = " + formation);
        }
    }
}
