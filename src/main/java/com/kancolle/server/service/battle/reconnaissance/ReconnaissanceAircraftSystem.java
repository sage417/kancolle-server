/**
 * 
 */
package com.kancolle.server.service.battle.reconnaissance;

import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.UnderSeaShip;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.utils.logic.DeckPortUtils;
import com.kancolle.server.utils.logic.ship.ShipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author J.K.SAGE
 * @Date 2015年8月24日
 *
 */
@Service
public class ReconnaissanceAircraftSystem implements IReconnaissanceAircraftSystem {

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
    public int memberDeckPortSearchEnemy(MemberDeckPort deckport, UnderSeaDeckPort underSeaDeckPort, int aerialState) {
        List<MemberShip> ships = deckport.getShips();

        int searchNeedValue = 2 * DeckPortUtils.calEnemyDeckPortSearchMinValue(underSeaDeckPort);
        int searchValue = ships.stream().mapToInt(ShipUtils::getShipSearchValue).sum();

        boolean searchSuccess = searchValue / ships.size() > searchNeedValue;
        Optional<MemberShip> shipHasSearchPlane = ships.stream().filter(ship -> ShipUtils.getSearchPlaneIndex(ship) > -1).findFirst();
        boolean planeSearch = shipHasSearchPlane.isPresent();

        if (planeSearch) {
            boolean planeBack = DeckPortUtils.attackAirSearchPlane(aerialState);
            if (!planeBack) {
                MemberShip ship = shipHasSearchPlane.get();
                int slotIdx = ShipUtils.getSearchPlaneIndex(ship);
                int now_eq = ship.getOnslot()[slotIdx];
                ship.getOnslot()[slotIdx] = now_eq - 1;
                memberShipService.updateShipOnSlot(ship);
            }
            return searchSuccess ? planeBack ? PLANE_SUCCESS : PLANE_SUCCESS_AND_FALLEN : planeBack ? PLANE_FAIL : PLANE_FAIL_AND_FALLEN;
        } else {
            return searchSuccess ? SEARCH_SUCCESS : SEARCH_FAIL;
        }
    }

    @Override
    public int enemyDeckPortSearchMember(MemberDeckPort memberDeckPort, UnderSeaDeckPort underSeaDeckPort) {
        List<UnderSeaShip> underSeaShips = underSeaDeckPort.getUnderSeaShips();

        int searchNeedValue = 2 * DeckPortUtils.calMemberDeckPortSearchMinValue(memberDeckPort);

        int searchValue = underSeaShips.stream().mapToInt(ShipUtils::getShipSearchValue).sum();
        boolean searchSuccess = searchValue / underSeaShips.size() > searchNeedValue;

        boolean planeSearch = underSeaShips.stream().anyMatch(ship -> ShipUtils.getSearchPlaneIndex(ship) > -1);

        return searchSuccess ? planeSearch ? PLANE_SUCCESS : SEARCH_SUCCESS : planeSearch ? PLANE_FAIL : SEARCH_FAIL;
    }

    @Override
    public boolean isSearchSuccess(int resultCode) {
        return resultCode == PLANE_SUCCESS || resultCode == PLANE_SUCCESS_AND_FALLEN || resultCode == SEARCH_SUCCESS;
    }
}
