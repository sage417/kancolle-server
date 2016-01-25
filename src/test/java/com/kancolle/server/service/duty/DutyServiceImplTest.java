package com.kancolle.server.service.duty;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.kancolle.server.model.po.duty.Duty;
import org.junit.Assert;
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
public class DutyServiceImplTest {

    @Autowired
    DutyService dutyService;

    @Test
    @DatabaseSetup("/dbunit/duty/duty_data.xml")
    public void test() {
        Duty duty = dutyService.getDutyByNo(701);
        Assert.assertNotNull(duty);
    }
}
