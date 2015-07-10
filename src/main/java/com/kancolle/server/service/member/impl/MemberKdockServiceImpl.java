/**
 * 
 */
package com.kancolle.server.service.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.member.MemberKdockDao;
import com.kancolle.server.model.po.member.MemberKdock;
import com.kancolle.server.service.member.MemberKdockService;

/**
 * @author J.K.SAGE
 * @Date 2015年7月10日
 *
 */
@Service
public class MemberKdockServiceImpl implements MemberKdockService {
    @Autowired
    private MemberKdockDao memberKdockDao;

    @Override
    public List<MemberKdock> getMemberKdock(String member_id) {
        return memberKdockDao.selectMemberKdocks(member_id);
    }
}
