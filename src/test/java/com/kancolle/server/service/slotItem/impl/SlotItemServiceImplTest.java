/**
 * 
 */
package com.kancolle.server.service.slotItem.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kancolle.server.model.po.slotitem.SlotItem;
import com.kancolle.server.service.slotitem.SlotItemService;

/**
 * @author J.K.SAGE
 * @Date 2015年5月31日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/spring-context.xml" })
public class SlotItemServiceImplTest {

    @Autowired
    private SlotItemService slotitemService;
    /**
     * Test method for {@link com.kancolle.server.service.slotitem.impl.SlotItemServiceImpl#getSlotItemById(int)}.
     */
    @Test
    public void testGetSlotItemById() {
        SlotItem slotItem = slotitemService.getSlotItemById(1);
        Assert.assertNotNull(slotItem);
    }
}
