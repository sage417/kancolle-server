/**
 * 
 */
package com.kancolle.server.service.member;

import com.kancolle.server.controller.kcsapi.form.deckport.ShipChangeForm;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 *
 */
public interface MemberDeckPortService {

    void changeShip(String member_id, ShipChangeForm form);

}
