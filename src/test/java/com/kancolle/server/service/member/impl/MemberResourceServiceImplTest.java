/**
 * 
 */
package com.kancolle.server.service.member.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kancolle.server.model.po.resource.Resource;
import com.kancolle.server.service.member.MemberResourceService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月20日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/spring-context.xml" })
public class MemberResourceServiceImplTest {
    @Autowired
    private MemberResourceService memberResourceService;

    /**
     * Test method for
     * {@link com.kancolle.server.service.member.impl.MemberResourceServiceImpl#getMemberResouce(long)}
     * .
     */
    @Test
    public void testGetMemberResouce() {
        Resource resource = memberResourceService.getMemberResouce(9007383);
        Assert.assertNotNull(resource);
        /*
         * Assert.assertEquals(1, resource.getFuel()); Assert.assertEquals(2,
         * resource.getBull()); Assert.assertEquals(3, resource.getSteal());
         * Assert.assertEquals(4, resource.getBauxite()); Assert.assertEquals(5,
         * resource.getFastRecovery()); Assert.assertEquals(6,
         * resource.getFastBuild()); Assert.assertEquals(7,
         * resource.getDevItem()); Assert.assertEquals(8, resource.getEhItem());
         */
    }

    @Test
    public void testUpdateResource() {
        memberResourceService.consumeResource(9007383, 1, 2, 3, 4, 5, 6, 7, 8);
        Resource resource = memberResourceService.getMemberResouce(9007383);
        memberResourceService.increaseResource(9007383, 1, 2, 3, 4, 5, 6, 7, 8);
        resource = memberResourceService.getMemberResouce(9007383);
    }

    @Test
    public void testIncreaseMaxResource() {
        Resource resource = memberResourceService.getMemberResouce(9007383);
        memberResourceService.increaseResource(9007383, Resource.MAX_RESOURCE_VALUE, Resource.MAX_RESOURCE_VALUE, Resource.MAX_RESOURCE_VALUE, Resource.MAX_RESOURCE_VALUE,
                Resource.MAX_METERIAL_VALUE, Resource.MAX_METERIAL_VALUE, Resource.MAX_METERIAL_VALUE, Resource.MAX_METERIAL_VALUE);
        Resource new_resource = memberResourceService.getMemberResouce(9007383);
    }
}
