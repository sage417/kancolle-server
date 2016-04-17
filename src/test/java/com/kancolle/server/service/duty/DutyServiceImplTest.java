package com.kancolle.server.service.duty;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kancolle.server.model.po.duty.Duty;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:spring/spring-context.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:spring/spring-mvc.xml")
})
@Sql(value = {"classpath:sql/kancolle-schema.sql", "classpath:sql/kancolle-data.sql"})
public class DutyServiceImplTest {

    @Autowired
    DutyService dutyService;

    public void test() {
        Duty duty = dutyService.getDutyByNo(701);
        Assert.assertNotNull(duty);
    }
}
