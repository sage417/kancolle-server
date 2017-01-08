/**
 *
 */
package com.kancolle.server.controller.kcsapi;

import com.kancolle.server.KancolleApp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;

/**
 * @author J.K.SAGE
 * @Date 2015年7月13日
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KancolleApp.class)
@WebAppConfiguration
@Sql(value = {"classpath:sql/kancolle-dump.sql"})
public class BattleControllerTest {

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
                .param("api_deck_id", "1")
                .param("api_mapinfo_no", "1")
                .param("api_maparea_id", "1")
                .param("api_formation_id", "1"))
                .andReturn();
        Assert.assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        result = mockMvc.perform(MockMvcRequestBuilders.post("/kcsapi/api_req_sortie/battle")
                .param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f")
                .param("api_formation", "1"))
                .andReturn();
        Assert.assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        result = mockMvc.perform(MockMvcRequestBuilders.post("/kcsapi/api_req_sortie/battleresult")
                .param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f"))
                .andReturn();
        Assert.assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        result = mockMvc.perform(MockMvcRequestBuilders.post("/kcsapi/api_req_map/next")
                .param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f"))
                .andReturn();
        Assert.assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        result = mockMvc.perform(MockMvcRequestBuilders.post("/kcsapi/api_req_sortie/battle")
                .param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f")
                .param("api_formation", "1"))
                .andReturn();
        Assert.assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        result = mockMvc.perform(MockMvcRequestBuilders.post("/kcsapi/api_req_sortie/battleresult")
                .param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f")
                .param("api_formation", "1"))
                .andReturn();
        Assert.assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
    }
}
