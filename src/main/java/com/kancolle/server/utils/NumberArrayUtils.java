/**
 * 
 */
package com.kancolle.server.utils;

/**
 * @author J.K.SAGE
 * @Date 2015年7月13日
 *
 */
public class NumberArrayUtils {

    public static void arraySum(int[] sumArray, int[] addArray) {

        if (sumArray.length > addArray.length)
            throw new IndexOutOfBoundsException();

        for (int i = 0; i < sumArray.length; i++) {
            sumArray[i] += addArray[i];
        }
    }
}
