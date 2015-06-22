/**
 * 
 */
package com.kancolle.server.service.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.member.MemberNdockDao;
import com.kancolle.server.model.kcsapi.member.MemberNdock;
import com.kancolle.server.service.member.MemberNdockService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
@Service
public class MemberNdockImpl implements MemberNdockService {
    @Autowired
    MemberNdockDao memberNdockDao;

    @Override
    public List<MemberNdock> getMemberNdock(String member_id) {
        return memberNdockDao.selectMemberNdock(member_id);
    }
}
