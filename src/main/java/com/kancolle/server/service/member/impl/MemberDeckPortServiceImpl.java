package com.kancolle.server.service.member.impl;

import org.springframework.stereotype.Service;

import com.kancolle.server.controller.kcsapi.form.deckport.ShipChangeForm;
import com.kancolle.server.model.kcsapi.member.MemberDeckPort;
import com.kancolle.server.service.member.MemberDeckPortService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 *
 */
@Service
public class MemberDeckPortServiceImpl implements MemberDeckPortService {

    @Override
    public void changeShip(String member_id, ShipChangeForm form) {
        MemberDeckPort targetDeck = queryForSingleModel(MemberDeckPort.class, "SELECT SHIP FROM v_member_deckport WHERE member_id = :member_id AND ID = :fleet_id", params);

        if ((ship_id == -2L) && (ship_idx == -1)) {
            // 旗艦以外解除
            int count = targetDeck.removeOthers();
        } else if (ship_id == -1L) {
            targetDeck.removeShip(ship_idx);
        } else {
            params.put("ship_id", ship_id);

            long rship_id = targetDeck.indexOf(ship_idx);

            MemberDeckPort otherDock = queryForSingleModel(MemberDeckPort.class, "SELECT * FROM v_member_deckport WHERE member_id = :member_id AND SHIP LIKE '%:ship_id%'", params);

            if (ship_idx < targetDeck.size()) {
                if (otherDock != null)
                    otherDock.replaceShip(otherDock.indexOf(rship_id), targetDeck.replaceShip(ship_idx, rship_id));
                else
                    targetDeck.replaceShip(ship_idx, ship_id);
            } else {
                if (otherDock != null)
                    otherDock.removeShip(otherDock.indexOf(rship_id));
                targetDeck.addShip(ship_id);
            }

            if (otherDock != null && fleet_id != otherDock.getApi_id()) {
                params.put("fleet_id", otherDock.getApi_id());
                params.put("ships", otherDock.getApi_ship().toJSONString());
                getTemplate().update(UPDATE_SHIP, params);
            }
        }

        params.put("fleet_id", fleet_id);
        params.put("ships", targetDeck.getApi_ship().toJSONString());
        getTemplate().update(UPDATE_SHIP, params);
    }

}
