package com.kancolle.server.service.ship.impl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.ship.MemberShipService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({@ContextConfiguration(name = "parent", locations = "classpath*:/spring/spring-context.xml"), @ContextConfiguration(name = "child", locations = "classpath*:/spring/mvc-context.xml")})
public class MemberShipServiceTest {
    @Autowired
    private MemberShipService service;

    @Test
    @DatabaseSetups({@DatabaseSetup("/dbunit/membership/membership_data.xml"),
            @DatabaseSetup("/dbunit/membership/ship_level_data.xml"),
            @DatabaseSetup("/dbunit/membership/ship_data.xml")})
    public void test() {
        MemberShip mship = service.getMemberShip("8006690", 1L);
        service.increaseMemberShipExp(mship, 1);
    }
}
