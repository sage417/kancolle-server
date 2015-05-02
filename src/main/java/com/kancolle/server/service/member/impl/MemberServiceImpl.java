package com.kancolle.server.service.member.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.dao.port.PortDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;
import com.kancolle.server.model.kcsapi.member.MemberKdock;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.kcsapi.member.MemberSlotItem;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.utils.DaoUtils;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao<?> memberDao;

    @Autowired
    private PortDao portDao;

    @Override
    public String getMemberByApiToken(String api_token) {
        return memberDao.getMemberByApiToken(api_token);
    }

    @Override
    public MemberBasic getBasic(String member_id) {
        return memberDao.getBasic(member_id);
    }

    @Override
    public List<MemberFurniture> getFurniture(String member_id) {
        return memberDao.getFurniture(member_id);
    }

    @Override
    public List<MemberSlotItem> getSlotItem(String member_id) {
        return memberDao.getSlotItem(member_id);
    }

    @Override
    public List<MemberUseItem> getUseItem(String member_id) {
        return memberDao.getUseItem(member_id);
    }

    @Override
    public List<MemberKdock> getKdock(String member_id) {
        return memberDao.getKdock(member_id);
    }

    @Override
    public MemberPort getPort(String member_id) throws InstantiationException, IllegalAccessException {
        return DaoUtils.setBean(portDao, new Class<?>[] { String.class }, new Object[] { member_id });
    }

    @Override
    public Map<String, Object> getUnsetSlot(String member_id) {
        return memberDao.getUnsetSlot(member_id);
    }

    @Override
    @Transactional
    public void destroyShip(String member_id, long api_ship_id) {
        memberDao.destroyShip(member_id, api_ship_id);
    }
}
