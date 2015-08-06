package com.kancolle.server.service.member.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.dao.port.PortDao;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;
import com.kancolle.server.model.kcsapi.member.record.MemberRecord;
import com.kancolle.server.model.kcsapi.member.record.MemberRecordFight;
import com.kancolle.server.model.kcsapi.member.record.MemberRecordMission;
import com.kancolle.server.model.kcsapi.member.record.MemberRecordPractise;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.service.furniture.MemberFurnitureService;
import com.kancolle.server.service.member.MemberDeckPortService;
import com.kancolle.server.service.member.MemberNdockService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.utils.logic.LvUtils;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private PortDao portDao;

    @Autowired
    private MemberFurnitureService memberFurnitureService;

    @Autowired
    private MemberNdockService memberNdockService;

    @Autowired
    private MemberShipService memberShipService;

    @Autowired
    private MemberDeckPortService memberDeckPortService;

    @Override
    public Member getBasic(String member_id) {
        return getMember(member_id);
    }

    @Override
    public Member getMember(String memberId) {
        return memberDao.getMemberById(memberId);
    }

    @Override
    /* @Cacheable(value = "token2MemberId", key = "#api_token") */
    public String getMemberByApiToken(String api_token) {
        return memberDao.getMemberByApiToken(api_token);
    }

    @Override
    public List<MemberMission> getMission(String member_id) {
        return memberDao.getMission(member_id);
    }

    @Override
    public MemberPort getPort(String member_id) {
        MemberPort port = new MemberPort();
        Member member = getBasic(member_id);
        port.setApi_basic(member);
        port.setApi_material(portDao.getMaterial(member_id));
        port.setApi_log(portDao.getLog(member_id));
        port.setApi_ship(memberShipService.getMemberShips(member_id));
        port.setApi_deck_port(memberDeckPortService.getMemberDeckPorts(member_id));
        port.setApi_ndock(memberNdockService.getMemberNdocks(member_id));
        return port;
    }

    @Override
    public MemberRecord getRecord(String member_id) {
        Member basic = getBasic(member_id);
        MemberRecord memberRecord = new MemberRecord();
        BeanUtils.copyProperties(basic, memberRecord);
        memberRecord.setApi_member_id(Long.valueOf(basic.getMemberId()));
        memberRecord.setApi_cmt(basic.getComment());
        memberRecord.setApi_cmt_id(basic.getCommentId());
        memberRecord.setApi_experience(Lists.newArrayList(basic.getExperience(), getSumExpByLV(basic.getLevel() + 1)));
        memberRecord.setApi_war(new MemberRecordFight(basic.getAttackWin(), basic.getAttackLose()));
        memberRecord.setApi_mission(new MemberRecordMission(basic.getMissionSuccess(), basic.getMissionCount()));
        memberRecord.setApi_practice(new MemberRecordPractise(basic.getPracticeWin(), basic.getPracticeLose()));
        memberRecord.setApi_deck(basic.getDeckCount());
        memberRecord.setApi_kdoc(basic.getKdockCount());
        memberRecord.setApi_ndoc(basic.getNdockCount());
        int ship_count = memberShipService.getCountOfMemberShip(member_id);
        int furniture_count = memberFurnitureService.getCountOfMemberFurniture(member_id);
        memberRecord.setApi_ship(Lists.newArrayList(ship_count, basic.getMaxChara()));
        memberRecord.setApi_slotitem(Lists.newArrayList(furniture_count, basic.getMaxSlotItem()));
        memberRecord.setApi_large_dock(basic.isLargeDock() ? 1 : 0);
        memberRecord.setApi_material_max(750 + 250 * basic.getLevel());
        return memberRecord;
    }

    @Override
    @Cacheable(value = "memberExp", key = "#lv")
    public long getSumExpByLV(int lv) {
        return memberDao.getNeedExpByLevel(lv);
    }

    @Override
    public List<MemberUseItem> getUseItem(String member_id) {
        return memberDao.getUseItem(member_id);
    }

    @Override
    public void increaseMemberExp(Member member, long exp) {
        if (member == null || exp < 0) {
            throw new IllegalArgumentException();
        }
        // 当前等级
        int nowLv = member.getLevel();
        // 150级不获得经验
        if (LvUtils.isMemberLVOver(nowLv))
            return;
        // 当前总经验
        long nowExp = member.getExperience();
        // 获得经验后总经验（未修正前）
        long afterExp = nowExp + exp;
        // 获得经验后等级（经过修正）
        int afterLv = memberDao.getMemberLVByExp(afterExp);

        if (LvUtils.isMemberLVOver(afterLv))
            // 获得经验后总经验（经过修正）
            afterExp = this.getSumExpByLV(afterLv);

        member.setLevel(afterLv);
        member.setExperience(afterExp);

        updateMember(member);
    }

    @Override
    public void updateMember(Member member) {
        memberDao.update(member);
    }

    @Override
    public void updateMemberToken(String member_id) {
        String token = StringUtils.join(UUID.randomUUID().toString().split("-"), StringUtils.EMPTY);
        memberDao.updateMemberToken(member_id, token);
    }

    @Override
    public void openLargeBuild(String member_id) {
        Member member = getMember(member_id);
        member.setLargeDock(true);
        updateMember(member);
    }
}
