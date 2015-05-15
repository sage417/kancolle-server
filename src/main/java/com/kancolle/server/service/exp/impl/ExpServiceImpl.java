package com.kancolle.server.service.exp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.exp.ExpDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberShip;
import com.kancolle.server.model.po.MissionExp;
import com.kancolle.server.service.exp.ExpService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.ship.ShipService;

@Service
public class ExpServiceImpl implements ExpService {
    @Autowired
    private ExpDao<?> expDao;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ShipService shipService;

    @Override
    public int getMemberLevel(long exp) {
        return expDao.getMemberLevel(exp);
    }

    @Override
    public long getMemberNextExp(int now_level) {
        return expDao.getMemberNextExp(now_level);
    }

    @Override
    public MissionExp getMissionExp(int missionId) {
        return expDao.getMissionExp(missionId);
    }

    @Override
    public int getShipLevel(long exp) {
        return expDao.getShipLevel(exp);
    }

    @Override
    public long getShipNextExp(int now_level) {
        return expDao.getShipNextExp(now_level);
    }

    @Override
    public MemberBasic updateMemberExpAndLevel(String member_id, int exp) {

        MemberBasic memberBasic = memberService.getBasic(member_id);

        memberBasic.setApi_experience(memberBasic.getApi_experience() + exp);
        memberBasic.setApi_level(getMemberLevel(memberBasic.getApi_experience()));

        expDao.updateMemberExpAndLevel(member_id, memberBasic.getApi_experience(), memberBasic.getApi_level());

        return memberBasic;
    }

    @Override
    public long[] updateShipExpAndLevel(String member_id, long ship_id, int exp) {
        MemberShip memberShip = shipService.getMemberShip(member_id, ship_id);

        long experience = memberShip.getApi_exp().getLong(1) + exp;

        int level = this.getShipLevel(experience);

        long level_need_exp = this.getShipNextExp(level) - this.getShipNextExp(--level);

        long next_exp = this.getShipNextExp(level) - experience;

        long show = 100 - next_exp / level_need_exp * 100;

        long[] experiences = new long[] { experience, next_exp, show };

        expDao.updateShipExpAndLevel(member_id, ship_id, experiences, level);

        return new long[] { experience, this.getShipNextExp(level) };
    }
}
