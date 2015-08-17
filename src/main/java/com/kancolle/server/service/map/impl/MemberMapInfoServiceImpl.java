/**
 * 
 */
package com.kancolle.server.service.map.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.mapper.map.MemberMapInfoMapper;
import com.kancolle.server.model.po.map.MemberMapInfo;
import com.kancolle.server.service.map.MemberMapInfoService;

/**
 * @author J.K.SAGE
 * @Date 2015年8月17日
 *
 */
@Service
public class MemberMapInfoServiceImpl implements MemberMapInfoService {

    @Autowired
    private MemberMapInfoMapper memberMapInfoDao;

    @Override
    public List<MemberMapInfo> getMemberMapInfos(String member_id) {
        return memberMapInfoDao.selectMemberMapInfosByMemberId(member_id);
    }
}
