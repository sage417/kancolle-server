/**
 * 
 */
package com.kancolle.server.service.ship;

import java.util.List;

import com.kancolle.server.controller.kcsapi.form.ship.ShipChargeForm;
import com.kancolle.server.model.kcsapi.charge.ChargeModel;
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

    /** 获取提督舰娘数 */
    int getCountOfMemberShip(String member_id);
    
    /** 补给燃弹 */
    ChargeModel chargeShips(String member_id, ShipChargeForm form);
}
