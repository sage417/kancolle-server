/**
 * 
 */
package com.kancolle.server.service.ship;

import java.util.List;

import com.kancolle.server.controller.kcsapi.form.ship.Ship3Form;
import com.kancolle.server.controller.kcsapi.form.ship.ShipChargeForm;
import com.kancolle.server.controller.kcsapi.form.ship.ShipPowerUpForm;
import com.kancolle.server.controller.kcsapi.form.ship.ShipSetSlotForm;
import com.kancolle.server.model.kcsapi.charge.ChargeModel;
import com.kancolle.server.model.kcsapi.ship.MemberShipLockResult;
import com.kancolle.server.model.kcsapi.ship.Ship3Result;
import com.kancolle.server.model.po.resource.MemberRescourceResult;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.MemberShipPowerupResult;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月23日
 *
 */
public interface MemberShipService {

    /** 补给燃弹 */
    ChargeModel chargeShips(String member_id, ShipChargeForm form);

    void destoryShips(String member_id, List<MemberShip> memberShips);

    /** 解体 */
    MemberRescourceResult destroyShipAndReturnResource(String member_id, Long member_ship_id);

    /** 获取提督舰娘数 */
    int getCountOfMemberShip(String member_id);

    /** 获取提督指定舰娘 */
    MemberShip getMemberShip(String member_id, Long ship_id);

    /** 获取提督所有舰娘 */
    List<MemberShip> getMemberShips(String memberId);

    /** 改装结果 */
    Ship3Result getShip3(String member_id, Ship3Form form);

    /** 舰娘获得经验 */
    void increaseMemberShipExp(MemberShip memberShip, int exp);

    /** 上锁 */
    MemberShipLockResult lock(String member_id, Long member_ship_id);

    /** 合成 */
    MemberShipPowerupResult powerup(String member_id, ShipPowerUpForm form);

    /** 改装 */
    void setSlot(String member_id, ShipSetSlotForm form);

    List<MemberSlotItem> unsetAllSlotitems(MemberShip memberShip);

    /** 移除所有裝備 */
    void unsetslotAll(String member_id, Long memberShip_id);

    void updateHpAndCond(MemberShip memberShip);

}
