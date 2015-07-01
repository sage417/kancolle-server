package com.kancolle.server.service.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.controller.kcsapi.form.deckport.ShipChangeForm;
import com.kancolle.server.dao.deck.MemberDeckPortDao;
import com.kancolle.server.model.po.member.MemberDeckPort;
import com.kancolle.server.service.member.MemberDeckPortService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 *
 */
@Service
public class MemberDeckPortServiceImpl implements MemberDeckPortService {

    @Autowired
    private MemberDeckPortDao memberDeckPortDao;

    @Override
    public List<MemberDeckPort> getMemberDeckPorts(String member_id) {
        return memberDeckPortDao.selectMemberDeckPorts(member_id);
    }

    @Override
    public MemberDeckPort getMemberDeckPort(String member_id, Integer deck_id) {
        return memberDeckPortDao.selectMemberDeckPort(member_id, deck_id);
    }

    @Override
    public void changeShip(String member_id, ShipChangeForm form) {
        // TODO Auto-generated method stub

    }
}