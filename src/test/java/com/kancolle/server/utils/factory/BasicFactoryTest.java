/**
 * 
 */
package com.kancolle.server.utils.factory;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author J.K.SAGE
 * @Date 2015年7月26日
 *
 */
public class BasicFactoryTest {

    @Test
    public void test() {
        Assert.assertEquals(BasicFactory.basic_ship_ids.length, BasicFactory.basic_pdf.length);
        Assert.assertEquals(BasicFactory.cl_ship_ids.length, BasicFactory.cl_pdf.length);
        Assert.assertEquals(BasicFactory.bb_ship_ids.length, BasicFactory.bb_pdf.length);
        Assert.assertEquals(BasicFactory.ca_ship_ids.length, BasicFactory.ca_pdf.length);
        /*        System.out.println(Arrays.stream(BasicFactory.basic_pdf).sum());
        System.out.println(Arrays.stream(BasicFactory.cl_pdf).sum());
        System.out.println(Arrays.stream(BasicFactory.bb_pdf).sum());
        System.out.println(Arrays.stream(BasicFactory.ca_pdf).sum());*/
    }
}
