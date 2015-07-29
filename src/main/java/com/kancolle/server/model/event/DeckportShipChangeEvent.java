/**
 * 
 */
package com.kancolle.server.model.event;

import java.util.List;

import com.kancolle.server.model.po.member.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;

/**
 * @author J.K.SAGE
 * @Date 2015年7月29日
 *
 */
public class DeckportShipChangeEvent {
    public static final int TYPE_ADD = 1;

    public static final int TYPE_REMOVE = 2;

    public static final int TYPE_REPLACE = 3;

    public static final int TYPE_REMOVE_OTHERS = 4;

    private MemberDeckPort deckPort;

    private int EVENT_TYPE;

    private List<MemberShip> changeShips;

    public MemberDeckPort getDeckPort() {
        return deckPort;
    }

    public void setDeckPort(MemberDeckPort deckPort) {
        this.deckPort = deckPort;
    }

    public int getEVENT_TYPE() {
        return EVENT_TYPE;
    }

    public void setEVENT_TYPE(int eVENT_TYPE) {
        EVENT_TYPE = eVENT_TYPE;
    }

    public List<MemberShip> getChangeShips() {
        return changeShips;
    }

    public void setChangeShips(List<MemberShip> changeShips) {
        this.changeShips = changeShips;
    }
}
