/**
 * 
 */
package com.kancolle.server.service.ship.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.ship.ShipService;

/**
 * @author J.K.SAGE
 * @Date 2015年5月31日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/spring-context.xml" })
public class ShipServiceImplTest {
    @Autowired
    private ShipService shipService;

    /**
     * Test method for {@link com.kancolle.server.service.ship.impl.ShipServiceImpl#getMemberShip2(java.lang.String, long)}.
     */
    @Test
    public void testGetMemberShip() {
       MemberShip ship =  shipService.getMemberShip2("9007383", 1);
    }
}
