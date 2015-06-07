/**
 * 
 */
package com.kancolle.server.service.member.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kancolle.server.service.member.MemberService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/spring-context.xml" })
public class MemberServiceImplTest {
    @Autowired
    private MemberService memberService;

    /**
     * Test method for {@link com.kancolle.server.service.member.impl.MemberServiceImpl#increaseMemberExp(java.lang.String, int)}.
     */
    @Test
    public void testIncreaseMemberExp() {
        memberService.increaseMemberExp("9007389", 1282555);
    }
}
