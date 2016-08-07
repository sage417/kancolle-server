/**
 * 
 */
package com.kancolle.server.model.po.mission;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author J.K.SAGE
 * @Date 2015年7月7日
 *
 */
@Alias("MissionExp")
public class MissionExp implements Serializable {

    private static final long serialVersionUID = 8166582709699865765L;

    private int missionId;

    private int memberExp;

    private int shipExp;

    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public int getMemberExp() {
        return memberExp;
    }

    public void setMemberExp(int memberExp) {
        this.memberExp = memberExp;
    }

    public int getShipExp() {
        return shipExp;
    }

    public void setShipExp(int shipExp) {
        this.shipExp = shipExp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + missionId;
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
        MissionExp other = (MissionExp) obj;
        if (missionId != other.missionId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("MissionExp [missionId=%s, memberExp=%s, shipExp=%s]", missionId, memberExp, shipExp);
    }
}
