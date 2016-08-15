package com.kancolle.server.service.deckport;

import com.google.common.collect.Lists;
import com.kancolle.server.controller.kcsapi.form.deckport.ShipChangeForm;
import com.kancolle.server.dao.deck.MemberDeckPortDao;
import com.kancolle.server.mapper.deckport.MemberPresetDeckMapper;
import com.kancolle.server.model.kcsapi.deck.MemberDeckPortChangeResult;
import com.kancolle.server.model.kcsapi.deck.PresetDeckResponse;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.deckport.PresetDeck;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.service.ship.MemberShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 */
@Service
public class MemberDeckPortService {

    @Autowired
    private MemberDeckPortDao memberDeckPortDao;
    @Autowired
    private MemberPresetDeckMapper memberPresetDeckMapper;
    @Autowired
    private MemberShipService memberShipService;

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<MemberDeckPort> getMemberDeckPorts(String member_id) {
        return memberDeckPortDao.selectMemberDeckPorts(member_id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberDeckPort getUnNullableMemberDeckPort(String member_id, Integer deck_id) {
        return checkNotNull(memberDeckPortDao.selectMemberDeckPort(member_id, deck_id));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public MemberDeckPortChangeResult changeShip(String member_id, ShipChangeForm form) {
        Integer fleet_id = form.getApi_id();
        long member_ship_id = form.getApi_ship_id();
        int ship_idx = form.getApi_ship_idx();

        MemberDeckPort targetDeck = getUnNullableMemberDeckPort(member_id, fleet_id);

        List<MemberShip> targetShips = targetDeck.getShips();

        if ((member_ship_id == -2L) && (ship_idx == -1)) {
            List<MemberShip> removeShips = targetShips.stream().skip(1L).collect(Collectors.toList());

            if (removeShips.isEmpty()) {
                throw new IllegalArgumentException("艦隊中只有一位艦娘，無法移除其他艦娘");
            }
            removeDeckPortShips(targetDeck, removeShips);
            return new MemberDeckPortChangeResult(removeShips.size());
        } else if (member_ship_id == -1L) {
            MemberShip removeShip = targetShips.get(ship_idx);
            removeDeckPortShips(targetDeck, Collections.singletonList(removeShip));
        } else {
            MemberShip memberShip = memberShipService.getMemberShip(member_id, member_ship_id);

            if (memberShip == null) {
                throw new IllegalArgumentException();
            }

            MemberDeckPort otherDock = getMemberDeckPortContainsMemberShip(member_id, member_ship_id);

            if (otherDock != null) {
                List<MemberShip> otherShips = otherDock.equals(targetDeck) ? targetShips : otherDock.getShips();
                otherDock.setShips(otherShips);

                if (ship_idx < targetShips.size())
                    swapDeckPortShip(targetDeck, otherDock, ship_idx, memberShip);
                else
                    moveDeckPortShip(targetDeck, otherDock, memberShip);
            } else {
                if (ship_idx < targetShips.size())
                    replaceDeckPortShip(targetDeck, ship_idx, memberShip);
                else
                    addDeckportShip(targetDeck, memberShip);
            }
        }
        return null;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRED)
    public void removeDeckPortShips(MemberDeckPort targetDeck, List<MemberShip> removeShips) {
        List<MemberShip> targetShips = targetDeck.getShips();
        // 旗艦不能被移除
        if (targetDeck.getDeckId() == 1 && targetShips.size() <= removeShips.size()) {
            throw new IllegalArgumentException("不能移除旗舰");
        }
        targetShips.removeAll(removeShips);
        memberDeckPortDao.deleteDeckPortShip(targetDeck, removeShips);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRED)
    public void addDeckportShip(MemberDeckPort targetDeck, MemberShip memberShip) {
        List<MemberShip> targetShips = targetDeck.getShips();
        targetShips.add(memberShip);

        checkDeckPort(targetShips);

        memberDeckPortDao.insertDeckPortShip(targetDeck, memberShip);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRED)
    public void replaceDeckPortShip(MemberDeckPort targetDeck, int ship_idx, MemberShip memberShip) {
        List<MemberShip> targetShips = targetDeck.getShips();
        MemberShip otherShip = targetShips.set(ship_idx, memberShip);

        checkDeckPort(targetShips);

        memberDeckPortDao.updateDeckPortShip(targetDeck, otherShip, memberShip);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRED)
    public void moveDeckPortShip(MemberDeckPort targetDeck, MemberDeckPort otherDock, MemberShip memberShip) {
        List<MemberShip> targetShips = targetDeck.getShips();
        List<MemberShip> otherShips = otherDock.getShips();
        otherShips.remove(memberShip);
        targetShips.add(memberShip);

        checkDeckPort(targetShips);

        if (!targetShips.equals(otherShips)) {
            if (targetDeck.getDeckId() == 1 && otherShips.isEmpty())
                throw new IllegalArgumentException("不能移除旗舰");
            removeDeckPortShips(targetDeck, Collections.singletonList(memberShip));
            addDeckportShip(targetDeck, memberShip);
        } else {
            memberDeckPortDao.updateMemberDeckPortShip(targetDeck);
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRED)
    public void swapDeckPortShip(MemberDeckPort targetDeck, MemberDeckPort otherDock, int ship_idx, MemberShip memberShip) {
        List<MemberShip> targetShips = targetDeck.getShips();
        List<MemberShip> otherShips = otherDock.getShips();
        int other_index = otherShips.indexOf(memberShip);
        MemberShip replacedShip = targetShips.set(ship_idx, memberShip);
        otherShips.set(other_index, replacedShip);
        if (!targetShips.equals(otherShips)) {

            checkDeckPort(targetShips);
            checkDeckPort(otherShips);

            memberDeckPortDao.updateDeckPortShip(targetDeck, replacedShip, memberShip);
            memberDeckPortDao.updateDeckPortShip(otherDock, memberShip, replacedShip);
        } else {

            checkDeckPort(targetShips);

            memberDeckPortDao.updateMemberDeckPortShip(targetDeck);
        }
    }

    private void checkDeckPort(List<MemberShip> targetShips) {
        List<Ship> checkList = Lists.newArrayList();

        for (MemberShip checkship : targetShips) {
            Ship ship = checkship.getShip();
            if (checkList.contains(ship))
                throw new IllegalStateException();
            checkList.add(ship);
            Ship afterShip = ship.getAfterShip();
            while (afterShip != null) {
                if (checkList.contains(afterShip))
                    throw new IllegalStateException();
                checkList.add(afterShip);
                afterShip = afterShip.getAfterShip();
            }
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberDeckPort getMemberDeckPortContainsMemberShip(String member_id, Long member_ship_id) {
        return memberDeckPortDao.selectMemberDeckPortContainsMemberShip(member_id, member_ship_id);
    }

    public void updateDeckPortMission(MemberDeckPort deckport) {
        memberDeckPortDao.updateDeckPortMission(deckport);
    }

    public void openDeckPort(String member_id, Integer deckport_id) {
        memberDeckPortDao.updateDeckPortState(member_id, deckport_id, false);
    }

    public void initMemberDeckPort(long member_id) {
        List<MemberDeckPort> deckPorts = Lists.newArrayListWithCapacity(4);
        MemberDeckPort deckPort;
        for (int id = 1; id < 5; id++) {
            deckPort = id == 1 ?
                    new MemberDeckPort(member_id, id, false) :
                    new MemberDeckPort(member_id, id, true);
            deckPorts.add(deckPort);
        }
        memberDeckPortDao.insertMemberDeckPorts(deckPorts);
    }

    public PresetDeckResponse getMemberPresetDeckResponse(String member_id) {
        List<PresetDeck> presetDecks = memberPresetDeckMapper.selectPresetDeckByMemberId(member_id);
        Map<String, PresetDeck> map = presetDecks.stream().collect(Collectors.toMap(p -> Integer.toString(p.getNo()), p -> p));
        return new PresetDeckResponse(3, map);
    }
}