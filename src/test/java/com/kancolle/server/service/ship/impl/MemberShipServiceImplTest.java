package com.kancolle.server.service.ship.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.ship.MemberShipService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({ @ContextConfiguration(name = "parent", locations = "classpath*:/spring/spring-context.xml"), @ContextConfiguration(name = "child", locations = "classpath*:/spring/mvc-context.xml") })
public class MemberShipServiceImplTest {
    @Autowired
    private MemberShipService service;

    @Test
    public void test() {
        MemberShip mship = service.getMemberShip("8006690", 1L);
        service.increaseMemberShipExp(mship, 1);
    }
}
