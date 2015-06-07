/**
 * 
 */
package com.kancolle.server.service.ship.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
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

    private MemberShip testMemberShip;

    /**
     * Test method for
     * {@link com.kancolle.server.service.ship.impl.ShipServiceImpl#getMemberShip2(java.lang.String, long)}
     * .
     */

    @Before
    public void createTestMemvberShip() {
        // TODO 真正创建一个
        testMemberShip = shipService.getMemberShip("9007383", 9999);
    }

    @Test
    public void testGetMemberShip() {
        MemberShip ship = shipService.getMemberShip("9007383", 1);
    }

    @Test
    public void testGetMembetShipExp() {
        MemberShip memberShip = new MemberShip();
        memberShip.setLv(99);
        assertEquals(memberShip, shipService.increaseMemberShipExp(memberShip, 1000));
        memberShip.setLv(150);
        assertEquals(memberShip, shipService.increaseMemberShipExp(memberShip, 1000));
        shipService.increaseMemberShipExp(testMemberShip, 1932);
        //assertEquals(2,testMemberShip.getLv());
    }
}
