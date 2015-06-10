package com.kancolle.server.service.member.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.dao.port.PortDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;
import com.kancolle.server.model.kcsapi.member.MemberKdock;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.kcsapi.member.MemberRecord;
import com.kancolle.server.model.kcsapi.member.MemberSlotItem;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.utils.DaoUtils;
import com.kancolle.server.utils.logic.LVUtil;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private PortDao portDao;

    @Override
    public void changeShip(String member_id, int fleet_id, long ship_id, int ship_idx) {
        memberDao.changeShip(member_id, fleet_id, ship_id, ship_idx);
    }

    @Override
    @Transactional
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
    public String getMemberByApiToken(String api_token) {
        return memberDao.getMemberByApiToken(api_token);
    }

    @Override
    public List<MemberMission> getMission(String member_id) {
        return memberDao.getMission(member_id);
    }

    @Override
    public MemberPort getPort(String member_id) throws InstantiationException, IllegalAccessException {
        MemberPort port = DaoUtils.setBean(portDao, new Class<?>[] { String.class }, new Object[] { member_id }, "setApi_p_bgm_id", "setApi_parallel_quest_count");
        port.setApi_p_bgm_id(port.getApi_basic().getApi_p_bgm_id());
        port.setApi_parallel_quest_count(port.getApi_basic().getApi_parallel_quest_count());
        return port;
    }

    @Override
    public MemberRecord getRecord(String member_id) {
        return memberDao.getRecord(member_id);
    }

    @Override
    public List<MemberSlotItem> getSlotItem(String member_id) {
        return memberDao.getSlotItem(member_id);
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
    @Cacheable(value = "memberExp", key = "#lv")
    public long getSumExpByLV(int lv) {
        return memberDao.getNeedExpByLevel(lv);
    }

    @Override
    public Member getMember(long memberId) {
        return memberDao.getMemberById(memberId);
    }

    @Override
    public void updateMember(Member member) {
        memberDao.update(member);
    }
}
