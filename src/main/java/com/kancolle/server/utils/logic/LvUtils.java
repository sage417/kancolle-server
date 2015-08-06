/**
 * 
 */
package com.kancolle.server.utils.logic;

import com.kancolle.server.model.po.common.MaxMinValue;

/**
 * @author J.K.SAGE
 * @Date 2015年6月6日
 *
 */
public class LvUtils {

    public static boolean isShipLVOver(int lv) {
        return lv == 99 || lv == 150;
    }

    public static boolean isMemberLVOver(int lv) {
        return lv == 120;
    }

    public static int getLvValue(MaxMinValue value, int lv) {
        return lv * (value.getMaxValue() - value.getMinValue()) / 99;
    }
}
