/**
 * 
 */
package com.kancolle.server.service.deckport;

import com.kancolle.server.controller.kcsapi.form.deckport.ShipChangeForm;
import com.kancolle.server.model.kcsapi.deck.MemberDeckPortChangeResult;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 *
 */
public interface MemberDeckPortService {

    List<MemberDeckPort> getMemberDeckPorts(String member_id);

    MemberDeckPort getUnNullableMemberDeckPort(String member_id, Integer deck_id);

    MemberDeckPort getMemberDeckPortContainsMemberShip(String member_id, Long member_ship_id);

    MemberDeckPortChangeResult changeShip(String member_id, ShipChangeForm form);

    void removeDeckPortShips(MemberDeckPort targetDeck, List<MemberShip> removeShips);

    void addDeckportShip(MemberDeckPort targetDeck, MemberShip memberShip);

    void replaceDeckPortShip(MemberDeckPort targetDeck, int ship_idx, MemberShip memberShip);

    void moveDeckPortShip(MemberDeckPort targetDeck, MemberDeckPort otherDock, MemberShip memberShip);

    void swapDeckPortShip(MemberDeckPort targetDeck, MemberDeckPort otherDock, int ship_idx, MemberShip memberShip);

    void updateDeckPortMission(MemberDeckPort deckport);

    void openDeckPort(String member_id, Integer deckport_id);

    void initMemberDeckPort(long member_id);
}
