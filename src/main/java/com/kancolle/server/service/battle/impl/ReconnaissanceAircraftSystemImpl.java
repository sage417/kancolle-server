/**
 * 
 */
package com.kancolle.server.service.battle.impl;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.model.po.slotitem.SlotItem;
import com.kancolle.server.service.battle.ReconnaissanceAircraftSystem;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.utils.logic.DeckPortUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年8月24日
 *
 */
@Service
public class ReconnaissanceAircraftSystemImpl implements ReconnaissanceAircraftSystem {
    /** 1 = 成功 */
    public static final int PLANE_SUCCESS = 1;

    /** 2=成功(未帰還機あり) */
    public static final int PLANE_SUCCESS_AND_FALLEN = 2;

    /** 3=失敗+未帰還 */
    public static final int PLANE_FAIL_AND_FALLEN = 3;

    /** 4=失敗, */
    public static final int PLANE_FAIL = 4;

    /** 5=成功(艦載機使用せず) */
    public static final int SEARCH_SUCCESS = 5;

    /** 6=失敗(艦載機使用せず) */
    public static final int SEARCH_FAIL = 6;

    @Autowired
    private MemberShipService memberShipService;

    @Override
    public int memberDeckPortSearchEnemy(MemberDeckPort deckport, EnemyDeckPort enemyDeckPort) {
        boolean planeSearch = false;

        int searchNeedValue = 2 * DeckPortUtils.calEnemyDeckPortSearchMinValue(enemyDeckPort);
        int searchValue = 0;

        List<MemberShip> ship_has_plane = Lists.newArrayListWithCapacity(6);

        Multimap<MemberShip, MemberSlotItem> shipMap = ArrayListMultimap.create(6, 4);

        List<MemberShip> ships = deckport.getShips();
        for (MemberShip ship : ships) {
            int ex_sakuteki = 0;
            for (int i = 0; i < ship.getSlot().size(); i++) {
                MemberSlotItem slotItem = ship.getSlot().get(i);
                int slotItem_saku = slotItem.getSlotItem().getSaku();
                if (slotItem.getSlotItem().getType()[2] == 10 && ship.getOnslot()[i] > 0) {
                    ship_has_plane.add(ship);
                    ex_sakuteki += 2 * slotItem_saku;
                    planeSearch = true;
                    shipMap.put(ship, slotItem);
                } else
                    ex_sakuteki += slotItem_saku;
            }
            searchValue += ((int) Math.sqrt(ship.getSakuteki().getMinValue()) + ex_sakuteki);
        }
        searchValue /= ships.size();

        boolean searchSuccess = searchValue > searchNeedValue;

        if (planeSearch) {
            boolean planeBack = DeckPortUtils.attackAirSearchPlane(enemyDeckPort);
            if (!planeBack) {
                Object[] memberShipArray = shipMap.keys().toArray();
                MemberShip keyShip = (MemberShip) memberShipArray[RandomUtils.nextInt(0, memberShipArray.length)];
                Object[] slotItems = shipMap.get(keyShip).toArray();
                MemberSlotItem slotItem = (MemberSlotItem) slotItems[RandomUtils.nextInt(0, slotItems.length)];
                int slotitem_index = keyShip.getSlot().indexOf(slotItem);
                int now_eq = keyShip.getOnslot()[slotitem_index];
                keyShip.getOnslot()[slotitem_index] = now_eq - 1;
                memberShipService.updateShipOnSlot(keyShip);
            }
            return searchSuccess ? planeBack ? PLANE_SUCCESS : PLANE_SUCCESS_AND_FALLEN : planeBack ? PLANE_FAIL : PLANE_FAIL_AND_FALLEN;
        } else {
            return searchSuccess ? SEARCH_SUCCESS : SEARCH_FAIL;
        }
    }

    @Override
    public int enemyDeckPortSearchMember(MemberDeckPort memberDeckPort, EnemyDeckPort enemyDeckPort) {
        boolean planeSearch = false;
        int searchNeedValue = 2 * DeckPortUtils.calMemberDeckPortSearchMinValue(memberDeckPort);
        int searchValue = 0;
        for (EnemyShip ship : enemyDeckPort.getEnemyShips()) {
            int ex_sakuteki = 0;
            for (SlotItem slotItem : ship.getSlot()) {
                int slotItem_saku = slotItem.getSaku();
                if (slotItem.getType()[2] == 10) {
                    ex_sakuteki += 2 * slotItem_saku;
                    planeSearch = true;
                } else {
                    ex_sakuteki += slotItem_saku;
                }
            }
            searchValue += ((int) Math.sqrt(ship.getSakuteki().getMinValue()) + ex_sakuteki);
        }

        boolean searchSuccess = searchValue > searchNeedValue;
        return searchSuccess ? planeSearch ? PLANE_SUCCESS : SEARCH_SUCCESS : planeSearch ? PLANE_FAIL : SEARCH_FAIL;
    }

    @Override
    public boolean isSearchSuccess(int resultCode) {
        return (resultCode == PLANE_SUCCESS || resultCode == PLANE_SUCCESS_AND_FALLEN || resultCode == SEARCH_SUCCESS);
    }
}
