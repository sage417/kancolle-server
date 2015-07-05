/**
 * 
 */
package com.kancolle.server.service.mission.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kancolle.server.model.po.mission.Mission;
import com.kancolle.server.service.mission.MissionService;

/**
 * @author J.K.SAGE
 * @Date 2015年7月5日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/spring-context.xml" })
public class MissionServiceImplTest {

    @Autowired
    private MissionService missionService;

    @Test
    public void test() {
        Mission mission = missionService.getMission(1);
        Assert.assertNotNull(mission);
    }
}
