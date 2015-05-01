package com.kancolle.server.dao.port.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.dao.port.PortDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberDeckPort;
import com.kancolle.server.model.kcsapi.member.MemberLog;
import com.kancolle.server.model.kcsapi.member.MemberMeterial;
import com.kancolle.server.model.kcsapi.member.MemberNDock;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.kcsapi.member.MemberShip;

public class PortDaoImpl extends BaseDaoImpl<MemberPort> implements PortDao {
    @Autowired
    private MemberDao<?> memberDao;

    @Override
    public List<MemberMeterial> getApiMaterial(String member_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MemberDeckPort> getApiDeckPort(String member_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MemberNDock> getApiNdock(String member_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MemberShip> getApiShip(String member_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MemberBasic getApiBasic(String member_id) {
        return memberDao.getBasic(member_id);
    }

    @Override
    public List<MemberLog> getApiLog(String member_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getApi_p_bgm_id(String member_id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getApiParallelQuestCount(String member_id) {
        // TODO Auto-generated method stub
        return 0;
    }

}
