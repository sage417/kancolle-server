package com.kancolle.server.service;

import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.service.member.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Package: com.kancolle.server.service
 * Author: mac
 * Date: 16/3/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath*:/spring/spring-context.xml"),
        @ContextConfiguration(name = "child", locations = "classpath*:/spring/mvc-context.xml") })
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;


    @Test
    public void testAddMember(){
        Member member = new Member();
        memberService.addMember(member);
    }
}
