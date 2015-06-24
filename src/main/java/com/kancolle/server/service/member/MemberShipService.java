/**
 * 
 */
package com.kancolle.server.service.member;

import java.util.List;

import com.kancolle.server.model.po.ship.MemberShip;

/**
 * @author J.K.SAGE
 * @Date 2015年6月23日
 *
 */
public interface MemberShipService {
    List<MemberShip> getMemberShips(String memberId);

    /**
     * @param memberShip
     */
    void repairShip(MemberShip memberShip);
}
