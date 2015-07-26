/**
 * 
 */
package com.kancolle.server.utils.factory;

/**
 * @author J.K.SAGE
 * @Date 2015年7月25日
 *
 */
public class BasicFactory {
    public static final int[] basic_ship_ids = new int[] { 55, 56, 100, 101, 22, 28, 31, 29, 30, 39, 41, 12, 11, 38, 94, 10, 7, 45, 14, 16, 2, 32, 15, 37, 43, 36, 35, 40, 51, 6, 52, 44, 46, 25, 99,
            96, 97, 98, 48, 21, 49, 95, 9, 13, 23, 33, 93, 34, 18, 47, 164, 53, 1, 54, 42, 115, 17, 19 };
    public static final double[] basic_pdf = new double[] { 4d, 4d, 2.58d, 2.42d, 2.14d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 2d,
            1.87d, 1.85, 1.71d, 1.71d, 1.57d, 1.44d, 1.29d, 1.29d, 1.13d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d };
    public static final double[] basic_cdf = FactoryUtils.pdf2cdf(basic_pdf);
    public static final int[] cl_ship_ids = new int[] { 60, 55, 56, 22, 53, 100, 64, 65, 101, 63, 69, 68, 54, 61, 21, 99, 71, 72, 70, 66, 67, 23, 62, 25, 52, 114, 51, 59, 20, 50, 123, 24, 127, 113,
            175, 126, 128, 174 };
    public static final double[] cl_pdf = new double[] { 6.45d, 4.8d, 4.8d, 4.6d, 4.4d, 4d, 4d, 4d, 4d, 4d, 3.2d, 3.3d, 3d, 3d, 3d, 3d, 2.7d, 2.6d, 2.6d, 2.4d, 2.4d, 2d, 2d, 2d, 2d, 2d, 2d, 2d,
            1.75d, 1.65d, 1d, 1d, 1d, 1d, 0.83d, 0.75d, 0.75d, 0.02d };
    public static final double[] cl_cdf = FactoryUtils.pdf2cdf(cl_pdf);
    public static final int[] bb_ship_ids = new int[] { 68, 69, 64, 63, 65, 27, 26, 70, 78, 86, 85, 67, 79, 77, 60, 87, 66, 71, 62, 56, 72, 123, 125, 81, 124, 80, 61, 59, 100, 101, 99, 22, 21, 53,
            54, 55, 23, 25 };
    public static final double[] bb_pdf = new double[] { 6d, 6d, 6d, 6d, 6d, 4.7d, 4.7d, 4d, 3.5d, 3.4d, 3.4d, 3d, 3.4d, 3d, 3d, 3d, 3d, 3d, 3d, 2.7d, 3d, 2d, 2d, 2d, 2d, 1d, 1d, 1d, 1d, 0.72d,
            0.86d, 0.6d, 0.65d, 0.44d, 0.35d, 0.3d, 0.14d, 0.14d };
    public static final double[] bb_cdf = FactoryUtils.pdf2cdf(bb_pdf);
    public static final int[] ca_ship_ids = new int[] { 103, 102, 74, 89, 76, 92, 75, 54, 55, 90, 71, 56, 72, 84, 70, 42, 17, 60, 113, 35, 37, 38, 100, 21, 111, 22, 13, 53, 10, 110, 32, 83, 36, 9,
            47, 11, 43, 12, 116, 25, 91, 34, 115, 14, 114, 33, 18, 101, 99 };
    public static final double[] ca_pdf = new double[] { 9d, 9d, 7d, 7d, 6d, 6d, 5d, 3d, 2d, 2d, 2d, 2d, 2d, 2d, 2d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d,
            1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d };
    public static final double[] ca_cdf = FactoryUtils.pdf2cdf(ca_pdf);

    public static int getBasicShipId() {
        int index = FactoryUtils.discrete(basic_cdf);
        return basic_ship_ids[index];
    }

    /**
     * @return
     */
    public static int getCLShipId() {
        int index = FactoryUtils.discrete(cl_cdf);
        return cl_ship_ids[index];
    }

    /**
     * @return
     */
    public static int getBBShipId() {
        int index = FactoryUtils.discrete(bb_cdf);
        return bb_ship_ids[index];
    }

    /**
     * @return
     */
    public static int getCAShipId() {
        int index = FactoryUtils.discrete(ca_cdf);
        return ca_ship_ids[index];
    }
}
