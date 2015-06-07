/**
 * 
 */
package com.kancolle.server.service.member.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.service.member.MemberService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/spring-context.xml" })
public class MemberServiceImplTest {
    private static final long TEST_MEMBER_ID = 9007383;
    private static final int INIT_LEVEL = 1;
    private static final long INIT_EXP = 0L;
    private static final long INCREASE_EXP = 1282555;
    private static final int INCREASE_LV = 99;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberDao memberDao;

    private void assertResult(Member member) {
        Assert.assertEquals(INCREASE_EXP, member.getExperience());
        Assert.assertEquals(INCREASE_LV, member.getLevel());
    }

    /**
     * Test method for
     * {@link com.kancolle.server.service.member.impl.MemberServiceImpl#increaseMemberExp(java.lang.String, int)}
     * .
     */
    @Test
    public void testIncreaseMemberExp() {
        Member member = memberService.getMember(TEST_MEMBER_ID);
        Assert.assertNotNull(member);
        member.setExperience(INIT_EXP);
        member.setLevel(INIT_LEVEL);
        memberDao.update(member);
        member = memberService.getMember(TEST_MEMBER_ID);
        Assert.assertEquals(INIT_EXP, member.getExperience());
        Assert.assertEquals(INIT_LEVEL, member.getLevel());
        // -----------Test---------------//
        memberService.increaseMemberExp(member, INCREASE_EXP);
        assertResult(member);
    }
}
