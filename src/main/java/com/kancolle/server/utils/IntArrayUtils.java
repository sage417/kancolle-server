/**
 * 
 */
package com.kancolle.server.utils;

/**
 * @author J.K.SAGE
 * @Date 2015年7月13日
 *
 */
public class IntArrayUtils {

    public static void intsArraySum(int[] sumArr, int[] addArr) {

        if (sumArr.length < addArr.length)
            throw new IndexOutOfBoundsException();

        for (int i = 0; i < sumArr.length; i++) {
            sumArr[i] += addArr[i];
        }
    }
}
