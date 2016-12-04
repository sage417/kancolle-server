/**
 *
 */
package com.kancolle.server.model.po.deckport;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.po.ship.MemberShip;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 */
@JsonPropertyOrder(value = {
        "memberId", "deckId", "name", "name_id",
        "mission", "flagship", "ship"
})
@Alias("MemberDeckPort")
public class MemberDeckPort implements Serializable {

    public static final int MAX_SHIP_COUNT = 6;

    private static final long serialVersionUID = 1972201247653250451L;

    private static final long[] EMPTY_SHIPS = new long[]{-1L, -1L, -1L, -1L, -1L, -1L};

    @JsonProperty(value = "api_member_id")
    private long memberId;

    @JsonProperty(value = "api_id")
    private int deckId;

    @JsonProperty(value = "api_name")
    private String name;

    @JsonProperty(value = "api_name_id")
    private String name_id;

    @JsonProperty(value = "api_mission")
    private long[] mission;

    @JsonProperty(value = "api_flagship")
    private String flagship;

    @JsonProperty(value = "api_ship")
    private long[] ship;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private List<MemberShip> ships;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private boolean lock;

    public MemberDeckPort() {
    }

    public MemberDeckPort(long member_id_str, int id, boolean lock) {
        this(member_id_str);
        this.setDeckId(id);
        this.setName(String.format("第%d艦隊", id));
        this.setShip(EMPTY_SHIPS);
        this.setLock(lock);
    }

    public MemberDeckPort(long memberId) {
        this.memberId = memberId;
    }

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

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
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

    @Override
    public String toString() {
        return "MemberDeckPort{" +
                "memberId=" + memberId +
                ", deckId=" + deckId +
                ", name='" + name + '\'' +
                ", mission=" + Arrays.toString(mission) +
                ", ships=" + ships +
                ", lock=" + lock +
                '}';
    }
}
