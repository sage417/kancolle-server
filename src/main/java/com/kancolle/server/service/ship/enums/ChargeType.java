/**
 * 
 */
package com.kancolle.server.service.ship.enums;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
public enum ChargeType {
    FUEL, BULL, ALL;

    public static ChargeType getChargeType(int kind) {
        switch (kind) {
        case 1:
            return FUEL;
        case 2:
            return BULL;
        case 3:
            return ALL;
        default:
            throw new IllegalArgumentException();
        }
    }
}
