/**
 * 
 */
package com.kancolle.server.utils.factory;

import java.util.Arrays;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年7月25日
 *
 */
public class FactoryUtils {

    public static double[] pdf2cdf(double[] pdf) {
        double[] cdf = Arrays.copyOf(pdf, pdf.length);

        for (int i = 1; i < cdf.length - 1; i++) {
            cdf[i] += cdf[i - 1];
        }
        cdf[cdf.length - 1] = 100;
        return cdf;
    }

    public static int discrete(double[] cdf) {
        double seed = RandomUtils.nextDouble(0, 100d);
        for (int i = 0; i < cdf.length; i++) {
            if (seed <= cdf[i])
                return i;
        }
        return -1;
    }
}
