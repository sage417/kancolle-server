package com.kancolle.server.service.duty;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kancolle.server.model.po.duty.Duty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({ @ContextConfiguration(name = "parent", locations = "classpath*:/spring/spring-context.xml"), @ContextConfiguration(name = "child", locations = "classpath*:/spring/mvc-context.xml") })
public class DutyServiceImplTest {
    @Autowired
    DutyService dutyService;

    @Test
    public void test() {
        Duty duty = dutyService.getDutyByNo(1);
        Assert.assertNotNull(duty);
    }

}
