/**
 * 
 */
package com.kancolle.server.controller.kcsapi;

import static org.junit.Assert.fail;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author J.K.SAGE
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/spring")
@ContextHierarchy({ @ContextConfiguration(name = "parent", locations = "classpath:spring-context.xml"), @ContextConfiguration(name = "child", locations = "classpath:servlet-context.xml") })
public class ReqKousyouTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testDestroyShip() {
        fail("Not yet implemented");
    }

}
