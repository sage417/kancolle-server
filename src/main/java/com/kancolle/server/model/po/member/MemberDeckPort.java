/**
 * 
 */
package com.kancolle.server.model.po.member;

import java.util.List;

import com.kancolle.server.model.po.ship.MemberShip;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 *
 */
public class MemberDeckPort {

    private long memberId;

    private int deckId;

    private String name;

    private String name_id;

    private long[] mission;

    private String flagship;

    private List<MemberShip> ships;

}
