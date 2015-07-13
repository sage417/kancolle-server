/**
 * 
 */
package com.kancolle.server.controller.kcsapi;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author J.K.SAGE
 * @Date 2015年7月13日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({ @ContextConfiguration(name = "parent", locations = "classpath*:/spring/spring-context.xml"),
        @ContextConfiguration(name = "child", locations = "classpath*:/spring/mvc-context.xml") })
public class ReqKousyouControllerTest {
    @Autowired
    DruidDataSource dataSource;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @After
    public void restoreDb() throws SQLException, IOException {
        ScriptRunner runner = new ScriptRunner(dataSource.getConnection());
        runner.runScript(Files.newBufferedReader(ResourceUtils.getFile("classpath:kancolle.sql").toPath()));
        runner.closeConnection();
    }

    public void testDestroyShip() {
        fail("Not yet implemented");
    }

    public void testCreateItem() {
        fail("Not yet implemented");
    }

    @Test
    public void testDestroyitem2() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/kcsapi/api_req_kousyou/destroyitem2").param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f").param("api_slotitem_ids", "2,3"))
                .andDo(MockMvcResultHandlers.print()).andReturn();
        Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, result.getResponse().getStatus());
    }
}
