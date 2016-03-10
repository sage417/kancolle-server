/**
 *
 */
package com.kancolle.server.controller.kcsapi;

import com.alibaba.druid.pool.DruidDataSource;
import com.kancolle.server.controller.kcsapi.form.ndock.NdockStartForm;
import com.kancolle.server.model.po.member.MemberNdock;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.member.MemberNdockService;
import com.kancolle.server.service.ship.MemberShipService;
import org.apache.ibatis.jdbc.ScriptRunner;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.sql.SQLException;

import static org.junit.Assert.fail;

/**
 * @author J.K.SAGE
 * @Date 2015年7月13日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:battlecontext/spring/spring-context.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:battlecontext/spring/spring-mvc.xml")
})
public class BattleTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testMapTraveller() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/kcsapi/api_req_map/start")
                .param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f")
                .param("api_ship_id", "11"))
                .andReturn();
        Assert.assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        result = mockMvc.perform(MockMvcRequestBuilders.post("kcsapi/api_req_sortie/battle")
                .param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f")
                .param("api_ship_id", "11"))
                .andReturn();
        Assert.assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        result = mockMvc.perform(MockMvcRequestBuilders.post("kcsapi/api_req_sortie/battleresult")
                .param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f")
                .param("api_ship_id", "11"))
                .andReturn();
        Assert.assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        result = mockMvc.perform(MockMvcRequestBuilders.post("/kcsapi/api_req_map/next")
                .param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f")
                .param("api_ship_id", "11"))
                .andReturn();
        Assert.assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        result = mockMvc.perform(MockMvcRequestBuilders.post("kcsapi/api_req_sortie/battle")
                .param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f")
                .param("api_ship_id", "11"))
                .andReturn();
        Assert.assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        result = mockMvc.perform(MockMvcRequestBuilders.post("kcsapi/api_req_sortie/battleresult")
                .param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f")
                .param("api_ship_id", "11"))
                .andReturn();
        Assert.assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
    }
}
