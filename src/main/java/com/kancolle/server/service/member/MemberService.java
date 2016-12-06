package com.kancolle.server.service.member;

import com.google.common.collect.Lists;
import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.dao.port.PortDao;
import com.kancolle.server.mapper.member.MemberLogMapper;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.kcsapi.member.record.MemberRecord;
import com.kancolle.server.model.kcsapi.member.record.MemberRecordFight;
import com.kancolle.server.model.kcsapi.member.record.MemberRecordMission;
import com.kancolle.server.model.kcsapi.member.record.MemberRecordPractise;
import com.kancolle.server.model.kcsapi.start.requireInfo.Basic;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.service.deckport.MemberDeckPortService;
import com.kancolle.server.service.furniture.MemberFurnitureService;
import com.kancolle.server.service.map.MemberMapService;
import com.kancolle.server.service.mission.MissionService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.useitem.MemberUseItemService;
import com.kancolle.server.utils.LoginUtils;
import com.kancolle.server.utils.logic.common.LvUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private PortDao portDao;
    @Autowired
    private MemberLogMapper memberLogMapper;
    @Autowired
    private MemberResourceService memberResourceService;
    @Autowired
    private MemberFurnitureService memberFurnitureService;
    @Autowired
    private MemberKdockService memberKdockService;
    @Autowired
    private MemberNdockService memberNdockService;
    @Autowired
    private MemberShipService memberShipService;
    @Autowired
    private MemberDeckPortService memberDeckPortService;
    @Autowired
    private MemberUseItemService memberUseItemService;
    @Autowired
    private MemberMapService memberMapService;
    @Autowired
    private MissionService missionService;
    @Autowired
    @Qualifier(value = "useItemIds")
    private int[] USE_ITEM_IDS;

    public Member getBasic(String member_id) {
        return getMember(member_id);
    }

    public Basic getBasic2(String member_id) {
        Member member = getMember(member_id);
        return new Basic(member_id, member.getFirstFlag());
    }

    public Member getMember(String memberId) {
        return memberDao.getMemberById(memberId);
    }

    /* @Cacheable(value = "token2MemberId", key = "#api_token") */
    public String getMemberByApiToken(String api_token) {
        return memberDao.getMemberIdByApiToken(api_token);
    }

    /* @CachePut(value = "token2MemberId", key = "#result") */
    public void updateMemberToken(String member_id) {
        memberDao.updateMemberToken(member_id, LoginUtils.generateMemberToken());
    }

    public List<MemberMission> getMission(String member_id) {
        return memberDao.getMission(member_id);
    }

    public MemberPort getPort(String member_id) {
        MemberPort port = new MemberPort();
        Member member = getBasic(member_id);
        port.setApi_basic(member);
        port.setApi_material(portDao.getMaterial(member_id));
        port.setApi_log(memberLogMapper.selectMemberLogs(member_id));
        port.setApi_ship(memberShipService.getMemberShips(member_id));
        port.setApi_deck_port(memberDeckPortService.getMemberDeckPorts(member_id));
        port.setApi_ndock(memberNdockService.getMemberNdocks(member_id));
        return port;
    }

    public MemberRecord getRecord(String member_id) {
        Member basic = getBasic(member_id);
        MemberRecord memberRecord = new MemberRecord();
        BeanUtils.copyProperties(basic, memberRecord);
        memberRecord.setApi_member_id(basic.getMemberId());
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
        memberRecord.setApi_large_dock(basic.isLargeDock());
        memberRecord.setApi_material_max(750 + 250 * basic.getLevel());
        return memberRecord;
    }

    @Cacheable(value = "memberExp", key = "#lv", cacheManager = "cacheManager")
    public long getSumExpByLV(int lv) {
        return memberDao.getNeedExpByLevel(lv);
    }

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

    public void updateMember(Member member) {
        memberDao.update(member);
    }

    public void openLargeDock(String member_id) {
        Member member = getMember(member_id);
        member.setLargeDock(true);
        updateMember(member);
    }

    @Transactional
    public Member addMember(Member member) {
        // 创建member
        boolean isInsert = memberDao.insert(member) == 1;
        Assert.isTrue(isInsert, "用户没有被创建");

        long member_id = member.getMemberId();

        // 创建资源记录
        memberResourceService.initMemberResource(member_id);
        // 创建舰队
        memberDeckPortService.initMemberDeckPort(member_id);
        // 创建远征记录
        missionService.initMemberMission(member_id);
        // 创建工厂
        memberKdockService.initMemberKdock(member_id);
        // 创建渠
        memberNdockService.initMemberNdock(member_id);
        // 创建家具记录
        memberFurnitureService.initMemberFurniture(member_id);
        // 创建item记录
        memberUseItemService.initMemberUseItem(member_id, USE_ITEM_IDS);
        // 创建MapInfo记录
        memberMapService.initMemberMapInfo(member_id);
        // 创建MapCell记录
        memberMapService.initMemberMapCellInfo(member_id);
        // 创建PresetDeck记录
        memberDeckPortService.initMemberPresetDecks(member_id);
        return member;
    }
}
