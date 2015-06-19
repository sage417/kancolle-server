/**
 * 
 */
package com.kancolle.server.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.member.MemberResourceDao;
import com.kancolle.server.model.po.resource.Resource;
import com.kancolle.server.service.member.MemberResourceService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月19日
 *
 */
@Service
public class MemberResourceServiceImpl implements MemberResourceService {
    @Autowired
    private MemberResourceDao memberResourceDao;

    @Override
    public void consumeResource(long memberId, int chargeFuel, int chargeBull, int comsumeSteal, int comsumeBauxite) {
        memberResourceDao.updateMemberResource(memberId, -chargeFuel, -chargeBull, -comsumeSteal, -comsumeBauxite);
    }

    @Override
    public Resource getMemberResouce(long memberId) {
        return memberResourceDao.selectMemberResource(memberId);
    }
}
