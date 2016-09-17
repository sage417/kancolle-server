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
    public static final int LINEAHEAD = 1;
    public static final int DOUBLELINE = 2;
    public static final int DIAMOND = 3;
    public static final int ECHELON = 4;
    public static final int LINEABREAST = 5;

    public static double shellingHougAugment(final int formation) {
        switch (formation) {
            case LINEAHEAD:
                return 1d;
            case DOUBLELINE:
                return 0.8d;
            case DIAMOND:
                return 0.7d;
            case ECHELON:
            case LINEABREAST:
                return 0.6d;
            default:
                return throwIllegalFormationIdx(formation);
        }
    }

    public static double taiSenHougAugment(final int formation) {
        switch (formation) {
            case LINEAHEAD:
                return 0.5d;
            case DOUBLELINE:
                return 0.6d;
            case DIAMOND:
                return 0.8d;
            case ECHELON:
                return 0.8d;
            case LINEABREAST:
                return 1d;
            default:
                return throwIllegalFormationIdx(formation);
        }
    }

    public static double shellingCoverAugment(final int formation) {
        switch (formation) {
            case LINEAHEAD:
                return 0.45d;
            case DOUBLELINE:
            case DIAMOND:
            case LINEABREAST:
                return 0.5d;
            case ECHELON:
                return 0.65d;
            default:
                return throwIllegalFormationIdx(formation);
        }
    }

    private static int throwIllegalFormationIdx(final int formation) {
        throw new IllegalArgumentException("formation argument error, formation id = " + formation);
    }
}
