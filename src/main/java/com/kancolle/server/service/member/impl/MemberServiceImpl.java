package com.kancolle.server.service.member.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.dao.port.PortDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;
import com.kancolle.server.model.kcsapi.member.MemberKdock;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.kcsapi.member.MemberSlotItem;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;
import com.kancolle.server.model.kcsapi.member.record.MemberRecord;
import com.kancolle.server.model.kcsapi.member.record.MemberRecordFight;
import com.kancolle.server.model.kcsapi.member.record.MemberRecordMission;
import com.kancolle.server.model.kcsapi.member.record.MemberRecordPractise;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.service.member.MemberFurnitureService;
import com.kancolle.server.service.member.MemberNdockService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.utils.DaoUtils;
import com.kancolle.server.utils.logic.LVUtil;

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

    @Override
    public void changeShip(String member_id, int fleet_id, long ship_id, int ship_idx) {
        memberDao.changeShip(member_id, fleet_id, ship_id, ship_idx);
    }

    @Override
    public void destroyShip(String member_id, long api_ship_id) {
        memberDao.destroyShip(member_id, api_ship_id);
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
    public List<MemberKdock> getKdock(String member_id) {
        return memberDao.getKdock(member_id);
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
    public MemberPort getPort(String member_id) throws InstantiationException, IllegalAccessException {
        MemberPort port = DaoUtils.setBean(portDao, new Class<?>[] { String.class }, new Object[] { member_id }, "setApi_p_bgm_id", "setApi_parallel_quest_count", "setApi_ndock", "setApi_ship");
        port.setApi_ship(memberShipService.getMemberShips(member_id));
        port.setApi_ndock(memberNdockService.getMemberNdocks(member_id));
        port.setApi_p_bgm_id(port.getApi_basic().getApi_p_bgm_id());
        port.setApi_parallel_quest_count(port.getApi_basic().getApi_parallel_quest_count());
        return port;
    }

    @Override
    public MemberRecord getRecord(String member_id) {
        MemberBasic basic = memberDao.getBasic(member_id);
        MemberRecord memberRecord = new MemberRecord();
        BeanUtils.copyProperties(basic, memberRecord);
        memberRecord.setApi_member_id(Long.valueOf(basic.getApi_member_id()));
        memberRecord.setApi_cmt(basic.getApi_comment());
        memberRecord.setApi_cmt_id(basic.getApi_comment_id());
        memberRecord.setApi_experience(Lists.newArrayList(basic.getApi_experience(), getSumExpByLV(basic.getApi_level() + 1)));
        memberRecord.setApi_war(new MemberRecordFight(basic.getApi_st_win(), basic.getApi_st_lose()));
        memberRecord.setApi_mission(new MemberRecordMission(basic.getApi_ms_success(), basic.getApi_ms_count()));
        memberRecord.setApi_practice(new MemberRecordPractise(basic.getApi_pt_win(), basic.getApi_pt_lose()));
        memberRecord.setApi_deck(basic.getApi_count_deck());
        memberRecord.setApi_kdoc(basic.getApi_count_kdock());
        memberRecord.setApi_ndoc(basic.getApi_count_ndock());
        int ship_count = memberShipService.getCountOfMemberShip(member_id);
        int furniture_count = memberFurnitureService.getCountOfMemberFurniture(member_id);
        memberRecord.setApi_ship(Lists.newArrayList(ship_count, basic.getApi_max_chara()));
        memberRecord.setApi_slotitem(Lists.newArrayList(furniture_count, basic.getApi_max_slotitem()));
        // TODO 是否能够大型建造
        memberRecord.setApi_large_dock(1);
        memberRecord.setApi_material_max(750 + 250 * basic.getApi_level());
        return memberRecord;
    }

    @Override
    public List<MemberSlotItem> getSlotItem(String member_id) {
        return memberDao.getSlotItem(member_id);
    }

    @Override
    @Cacheable(value = "memberExp", key = "#lv")
    public long getSumExpByLV(int lv) {
        return memberDao.getNeedExpByLevel(lv);
    }

    @Override
    public Map<String, Object> getUnsetSlot(String member_id) {
        return memberDao.getUnsetSlot(member_id);
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
        if (LVUtil.isMemberLVOver(nowLv))
            return;
        // 当前总经验
        long nowExp = member.getExperience();
        // 获得经验后总经验（未修正前）
        long afterExp = nowExp + exp;
        // 获得经验后等级（经过修正）
        int afterLv = memberDao.getMemberLVByExp(afterExp);

        if (LVUtil.isMemberLVOver(afterLv))
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
}
