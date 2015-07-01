package com.kancolle.server.service.member.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kancolle.server.controller.kcsapi.form.deckport.ShipChangeForm;
import com.kancolle.server.dao.deck.MemberDeckPortDao;
import com.kancolle.server.model.po.member.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.member.MemberDeckPortService;
import com.kancolle.server.service.ship.MemberShipService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 *
 */
@Service
public class MemberDeckPortServiceImpl implements MemberDeckPortService {

    @Autowired
    private MemberDeckPortDao memberDeckPortDao;

    @Autowired
    private MemberShipService memberShipService;

    @Override
    public List<MemberDeckPort> getMemberDeckPorts(String member_id) {
        return memberDeckPortDao.selectMemberDeckPorts(member_id);
    }

    @Override
    public MemberDeckPort getMemberDeckPort(String member_id, Integer deck_id) {
        return memberDeckPortDao.selectMemberDeckPort(member_id, deck_id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void changeShip(String member_id, ShipChangeForm form) {
        Integer fleet_id = form.getApi_id();
        long member_ship_id = form.getApi_ship_id();
        int ship_idx = form.getApi_ship_idx();

        MemberDeckPort targetDeck = getMemberDeckPort(member_id, fleet_id);

        if (targetDeck == null) {
            throw new IllegalArgumentException();
        }

        List<MemberShip> targetShips = targetDeck.getShips();

        if ((member_ship_id == -2L) && (ship_idx == -1)) {
            List<MemberShip> removeShips = targetShips.stream().skip(1L).collect(Collectors.toList());
            removeDeckPortShip(targetDeck, removeShips);
        } else if (member_ship_id == -1L) {
            MemberShip removeShip = targetShips.get(ship_idx);
            removeDeckPortShip(targetDeck, Collections.singletonList(removeShip));
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
                if (ship_idx < targetShips.size()) {

                    replaceDeckPortShip(targetDeck, ship_idx, memberShip);
                } else {

                    addDeckportShip(targetDeck, memberShip);
                }
            }
        }
    }

    /**
     * @param targetDeck
     * @param removeShips
     */
    private void removeDeckPortShip(MemberDeckPort targetDeck, List<MemberShip> removeShips) {
        List<MemberShip> targetShips = targetDeck.getShips();
        targetShips.removeAll(removeShips);
        memberDeckPortDao.deleteDeckPortShip(targetDeck, removeShips);
    }

    private void addDeckportShip(MemberDeckPort targetDeck, MemberShip memberShip) {
        List<MemberShip> targetShips = targetDeck.getShips();
        targetShips.add(memberShip);
        memberDeckPortDao.insertDeckPortShip(targetDeck, memberShip);
    }

    private void replaceDeckPortShip(MemberDeckPort targetDeck, int ship_idx, MemberShip memberShip) {
        List<MemberShip> targetShips = targetDeck.getShips();
        memberDeckPortDao.updateDeckPortShip(targetDeck, targetShips.set(ship_idx, memberShip), memberShip);
    }

    private void moveDeckPortShip(MemberDeckPort targetDeck, MemberDeckPort otherDock, MemberShip memberShip) {
        List<MemberShip> targetShips = targetDeck.getShips();
        List<MemberShip> otherShips = otherDock.getShips();
        otherShips.remove(memberShip);
        targetShips.add(memberShip);
        if (!targetShips.equals(otherShips)) {
            removeDeckPortShip(targetDeck, Collections.singletonList(memberShip));
            addDeckportShip(targetDeck, memberShip);
        } else {
            memberDeckPortDao.updateMemberDeckPortShip(targetDeck);
        }
    }

    private void swapDeckPortShip(MemberDeckPort targetDeck, MemberDeckPort otherDock, int ship_idx, MemberShip memberShip) {
        List<MemberShip> targetShips = targetDeck.getShips();
        List<MemberShip> otherShips = otherDock.getShips();
        int other_index = otherShips.indexOf(memberShip);
        MemberShip replacedShip = targetShips.set(ship_idx, memberShip);
        otherShips.set(other_index, replacedShip);
        if (!targetShips.equals(otherShips)) {
            memberDeckPortDao.updateDeckPortShip(targetDeck, replacedShip, memberShip);
            memberDeckPortDao.updateDeckPortShip(otherDock, memberShip, replacedShip);
        } else {
            memberDeckPortDao.updateMemberDeckPortShip(targetDeck);
        }
    }

    @Override
    public MemberDeckPort getMemberDeckPortContainsMemberShip(String member_id, Long member_ship_id) {
        return memberDeckPortDao.selectMemberDeckPortContainsMemberShip(member_id, member_ship_id);
    }
}