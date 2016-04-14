package com.kancolle.server.service.member;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.utils.LoginUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.UUID;

/**
 * Package: com.kancolle.server.service
 * Author: mac
 * Date: 16/3/24
 */
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:spring/spring-context.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:spring/spring-mvc.xml")
})
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    public void testAddMember(){
        Member member = new Member();
        memberService.addMember(member);
    }


    @Test
    @DatabaseSetup("/dbunit/member/t_member.xml")
    public void testUpdateToken() {
        String memberId = "9007383";
        Member member = memberService.getMember(memberId);
        String token = member.getToken();
        memberService.updateMemberToken(memberId);
        member = memberService.getMember(memberId);
        Assert.assertFalse(token.equals(member.getToken()));
    }

    @Test
    public void createNewMember(){
        Member member = Member.builder()
                .nickName("sage417")
                .fleetName("舰队")
                .maxChara(150)
                .maxSlotItem(500)
                .token(LoginUtils.generateMemberToken())
                .build();
        memberService.addMember(member);
    }
}
