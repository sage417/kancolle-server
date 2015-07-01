/**
 * 
 */
package com.kancolle.server.service.member.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kancolle.server.model.po.member.MemberDeckPort;
import com.kancolle.server.service.member.MemberDeckPortService;

/**
 * @author J.K.SAGE
 * @Date 2015年7月1日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/spring-context.xml" })
public class MemberDeckPortServiceImplTest {
    @Autowired
    private MemberDeckPortService memberDeckPortService;

    @Test
    public void test() {
        MemberDeckPort deck = memberDeckPortService.getMemberDeckPort("8006690", 1);
        assertNotNull(deck);
    }
}
