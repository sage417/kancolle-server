/**
 * 
 */
package com.kancolle.server.model.po.member;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.ship.MemberShip;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 *
 */
@Alias("MemberDeckPort")
public class MemberDeckPort {

    @JSONField(ordinal = 1, name = "api_member_id")
    private long memberId;

    @JSONField(ordinal = 2, name = "api_id")
    private int deckId;

    @JSONField(ordinal = 3, name = "api_name")
    private String name;

    @JSONField(ordinal = 4, name = "api_name_id")
    private String name_id;

    @JSONField(ordinal = 5, name = "api_mission")
    private long[] mission;

    @JSONField(ordinal = 6, name = "api_flagship")
    private String flagship;

    @JSONField(ordinal = 7, name = "api_ship")
    private long[] ship;

    @JSONField(serialize = false, deserialize = false)
    private List<MemberShip> ships;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_id() {
        return name_id;
    }

    public void setName_id(String name_id) {
        this.name_id = name_id;
    }

    public long[] getMission() {
        return mission;
    }

    public void setMission(long[] mission) {
        this.mission = mission;
    }

    public String getFlagship() {
        return flagship;
    }

    public void setFlagship(String flagship) {
        this.flagship = flagship;
    }

    public long[] getShip() {
        return ship;
    }

    public void setShip(long[] api_ship) {
        this.ship = api_ship;
    }

    public List<MemberShip> getShips() {
        return ships;
    }

    public void setShips(List<MemberShip> ships) {
        this.ships = ships;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + deckId;
        result = prime * result + (int) (memberId ^ (memberId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MemberDeckPort other = (MemberDeckPort) obj;
        if (deckId != other.deckId)
            return false;
        if (memberId != other.memberId)
            return false;
        return true;
    }
}