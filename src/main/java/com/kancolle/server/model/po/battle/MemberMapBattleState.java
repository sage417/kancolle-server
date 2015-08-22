/**
 * 
 */
package com.kancolle.server.model.po.battle;

import java.util.Arrays;

import org.apache.ibatis.type.Alias;

import com.kancolle.server.model.po.deckport.MemberDeckPort;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
@Alias("MemberMapBattleState")
public class MemberMapBattleState {

    private String memberId;

    private MemberDeckPort memberDeckPort;

    private int mapAreaId;

    private int mapCellId;

    private int[] mapGetResources;

    private int[] mapFetchResources;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public MemberDeckPort getMemberDeckPort() {
        return memberDeckPort;
    }

    public void setMemberDeckPort(MemberDeckPort memberDeckPort) {
        this.memberDeckPort = memberDeckPort;
    }

    public int getMapAreaId() {
        return mapAreaId;
    }

    public void setMapAreaId(int mapAreaId) {
        this.mapAreaId = mapAreaId;
    }

    public int getMapCellId() {
        return mapCellId;
    }

    public void setMapCellId(int mapCellId) {
        this.mapCellId = mapCellId;
    }

    public int[] getMapGetResources() {
        return mapGetResources;
    }

    public void setMapGetResources(int[] mapGetResources) {
        this.mapGetResources = mapGetResources;
    }

    public int[] getMapFetchResources() {
        return mapFetchResources;
    }

    public void setMapFetchResources(int[] mapFetchResources) {
        this.mapFetchResources = mapFetchResources;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
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
        MemberMapBattleState other = (MemberMapBattleState) obj;
        if (memberId == null) {
            if (other.memberId != null)
                return false;
        } else if (!memberId.equals(other.memberId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("MemberMapBattleState [memberId=%s, memberDeckPort=%s, mapAreaId=%s, mapCellId=%s, mapGetResources=%s, mapFetchResources=%s]", memberId, memberDeckPort, mapAreaId,
                mapCellId, Arrays.toString(mapGetResources), Arrays.toString(mapFetchResources));
    }
}
