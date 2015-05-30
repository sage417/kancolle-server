/**
 * 
 */
package com.kancolle.server.dao.mission;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kancolle.server.model.po.map.MapArea;
import com.kancolle.server.model.po.mission.Mission;

/**
 * @author J.K.SAGE
 * @Date 2015年5月17日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/spring-context.xml" })
public class MissionDaoTest {

    @Autowired
    private MissionDao<Mission> missionDao;

    /**
     * Test method for
     * {@link com.kancolle.server.dao.mission.MissionDao#getMissionById(int)}.
     */
    @Test
    public void testGetMissionById() {
        Mission mission = missionDao.getMissionById(16);
        assertEquals(200, mission.getBauxite());
        assertEquals(500, mission.getBull());
        assertNotNull(mission.getDetails());
        assertEquals(6, mission.getDifficulty());
        assertEquals(120, mission.getExp());
        assertEquals(500, mission.getFuel());
        assertEquals(2, mission.getItem1_count());
        assertEquals(2, mission.getItem1_id());
        assertEquals(2, mission.getItem2_count());
        assertEquals(3, mission.getItem2_id());
        assertEquals(16, mission.getMission_id());
        assertEquals(200, mission.getShip_exp());
        assertEquals(200, mission.getSteal());
        assertEquals(900, mission.getTime());
        assertEquals(0.4, mission.getUse_bull(), 0);
        assertEquals(0.5, mission.getUse_fuel(), 0);

        assertNotNull(mission.getMaparea());
        assertEquals(0, mission.getMaparea().getType());
        assertNotNull(mission.getMaparea().getName());
    }

}
