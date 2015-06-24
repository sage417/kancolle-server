/**
 * 
 */
package com.kancolle.server.service.ship;

import java.util.List;

import com.kancolle.server.model.po.ship.MemberShip;

/**
 * @author J.K.SAGE
 * @Date 2015年6月23日
 *
 */
public interface MemberShipService {

    /** 获取提督指定舰娘 */
    MemberShip getMemberShip(String member_id, long ship_id);

    /** 获取提督所有舰娘 */
    List<MemberShip> getMemberShips(String memberId);
}
