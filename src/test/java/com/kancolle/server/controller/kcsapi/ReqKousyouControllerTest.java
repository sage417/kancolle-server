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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.jdbc.Sql;
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
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath*:/spring/spring-context.xml"),
        @ContextConfiguration(name = "child", locations = "classpath*:/spring/spring-mvc.xml")
})
@Sql(value = {"classpath:sql/kancolle-schema.sql", "classpath:sql/kancolle-data.sql"})
public class ReqKousyouControllerTest {
    private static Reader scriptReader;

    //@BeforeClass
    public static void prepare() throws FileNotFoundException, IOException {
        scriptReader = Files.newBufferedReader(ResourceUtils.getFile("classpath:kancolle-data.sql").toPath());
    }

    @Autowired
    private MemberShipService memberShipService;

    @Autowired
    private MemberNdockService memberNdockService;

    @Autowired
    DruidDataSource dataSource;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    //@Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //@After
    public void restoreDb() throws SQLException {
        ScriptRunner runner = new ScriptRunner(dataSource.getConnection());
        runner.setLogWriter(null);
        runner.runScript(scriptReader);
        runner.closeConnection();
    }

    public void testCreateShip() {
        fail("Not yet implemented");
    }

    public void testDestroyShip() {
        fail("Not yet implemented");
    }

    //@Test
    public void testDestoryLeaderShip() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/kcsapi/api_req_kousyou/destroyship").param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f").param("api_ship_id", "11")).andReturn();
        Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, result.getResponse().getStatus());
    }

    //@Test
    public void testDestoryShipInNdock() throws Exception {
        final String member_id = "8006690";
        final Long member_ship_id = Long.valueOf(1L);
        final Integer ndock_id = Integer.valueOf(1);

        MemberShip memberShip = memberShipService.getMemberShip(member_id, member_ship_id);
        memberShip.setNowHp(1);
        memberShipService.updateHpAndCond(memberShip);
        memberShipService.unSetAllSlotItems(memberShip);
        // 解锁
        memberShipService.lock(member_id, member_ship_id);

        NdockStartForm form = new NdockStartForm();
        form.setApi_ndock_id(ndock_id);
        form.setApi_ship_id(memberShip.getMemberShipId());
        form.setApi_highspeed(0);
        memberNdockService.start(member_id, form);

        MemberNdock ndock = memberNdockService.getMemberNdockByCond(member_id, ndock_id);
        Assert.assertEquals(MemberNdock.STATE_USING, ndock.getState());
        Assert.assertEquals(member_ship_id.longValue(), ndock.getMemberShipId());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/kcsapi/api_req_kousyou/destroyship").param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f").param("api_ship_id", member_ship_id.toString())).andReturn();
        Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, result.getResponse().getStatus());
    }

    public void testDestoryShipInMission() throws Exception {
        fail("Not yet implemented");
    }

    public void testDestoryShipInBattle() throws Exception {
        fail("Not yet implemented");
    }

    public void testCreateItem() {
        fail("Not yet implemented");
    }

    //@Test
    public void testDestroyitem2() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/kcsapi/api_req_kousyou/destroyitem2").param("api_token", "de1d61f922ae5604a0c479914813d8a18d5c9b6f").param("api_slotitem_ids", "2,3")).andReturn();
        Assert.assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
    }
}
