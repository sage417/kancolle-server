/**
 * 
 */
package com.kancolle.server.service.ship.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.service.ship.MemberShipService;
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

    @Autowired
    private MemberShipService memberShipService;

    private MemberShip testMemberShip;

    /**
     * Test method for
     * {@link com.kancolle.server.service.ship.impl.ShipServiceImpl#getMemberShip2(java.lang.String, long)}
     * .
     */

    public void createTestMemvberShip() {
        // TODO 真正创建一个
        testMemberShip = memberShipService.getMemberShip("8006690", 1L);
    }

    @Test
    public void testGetMemberShip() {
        MemberShip ship = memberShipService.getMemberShip("8006690", 1L);
        Assert.assertTrue(!ship.getSlot().isEmpty());
    }

    public void testGetMembetShipExp() {
        MemberShip memberShip = new MemberShip();
        memberShip.setLv(99);
        memberShipService.increaseMemberShipExp(memberShip, 1000);
        memberShip.setLv(150);
        memberShipService.increaseMemberShipExp(testMemberShip, 1932);
    }

    @Test
    public void testSelectNullIDShip() {
        List<Ship> ships = shipService.getShips();
        Assert.assertTrue(ships.size() > 2);
    }
}
