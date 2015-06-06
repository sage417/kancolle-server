/**
 * 
 */
package com.kancolle.server.utils.logic;

/**
 * @author J.K.SAGE
 * @Date 2015年6月6日
 *
 */
public class LVUtil {

    public static boolean isShipLVOver(int level) {
        return level == 99 || level == 150;
    }

    public static boolean isMemberLVOver(int level) {
        return level == 120;
    }
}
