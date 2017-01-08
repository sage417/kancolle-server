package com.kancolle.server.service.duty;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.kancolle.server.KancolleApp;
import com.kancolle.server.model.po.duty.Duty;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@ActiveProfiles("test")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KancolleApp.class)
@Sql(value = {"classpath:sql/kancolle-dump.sql"})
public class DutyServiceImplTest {

    @Autowired
    DutyService dutyService;

    @Test
    public void test() {
        Duty duty = dutyService.getDutyByNo(701);
        Assert.assertNotNull(duty);
    }
}
