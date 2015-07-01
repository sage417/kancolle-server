/**
 * 
 */
package com.kancolle.server.service.member;

import java.util.List;

import com.kancolle.server.controller.kcsapi.form.deckport.ShipChangeForm;
import com.kancolle.server.model.po.member.MemberDeckPort;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 *
 */
public interface MemberDeckPortService {

    List<MemberDeckPort> getMemberDeckPorts(String member_id);

    MemberDeckPort getMemberDeckPort(String member_id, Integer deck_id);

    MemberDeckPort getMemberDeckPortContainsMemberShip(String member_id, Long member_ship_id);

    void changeShip(String member_id, ShipChangeForm form);
}
