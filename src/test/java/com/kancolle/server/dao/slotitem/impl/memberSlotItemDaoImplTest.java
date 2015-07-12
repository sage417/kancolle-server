/**
 * 
 */
package com.kancolle.server.dao.slotitem.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kancolle.server.dao.slotitem.MemberSlotItemDao;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年7月12日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/spring-context.xml" })
public class memberSlotItemDaoImplTest {

    @Autowired
    private MemberSlotItemDao memberSlotItemDao;

    @Test
    public void test() {
        MemberSlotItem slotitem = memberSlotItemDao.createMemberSlotItem("8006690", 31);
        assertNotNull(slotitem);
    }
}
