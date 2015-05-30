/**
 * 
 */
package com.kancolle.server.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.model.po.ship.Ship;

/**
 * @author J.K.SAGE
 * @Date 2015年5月17日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/spring-context.xml" })
public class ShipDaoTest {
    @Autowired
    ShipDao shipdao;

    @Test
    public void test() {
        Ship ship = shipdao.getShipById(1);
    }
}
