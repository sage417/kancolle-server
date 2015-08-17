/**
 * 
 */
package com.kancolle.server.model.po.map;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年8月17日
 *
 */
@Alias("MemberMapInfo")
public class MemberMapInfo {

    @JSONField(serialize = false, deserialize = false)
    private String memberId;

    @JSONField(ordinal = 1, name = "api_id")
    private int mapId;

    @JSONField(serialize = false, deserialize = false)
    private boolean isCleared;

    @JSONField(ordinal = 2, name = "api_cleared")
    public int getIsCleared() {
        return isCleared() ? 1 : 0;
    }

    private boolean exBossFlag;

    @JSONField(ordinal = 3, name = "api_exboss_flag")
    public int getIsExBossFlag() {
        return isExBossFlag() ? 1 : 0;
    }

    @JSONField(ordinal = 4, name = "api_defeat_count")
    private int defeatCount;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public boolean isCleared() {
        return isCleared;
    }

    public void setCleared(boolean isCleared) {
        this.isCleared = isCleared;
    }

    public boolean isExBossFlag() {
        return exBossFlag;
    }

    public void setExBossFlag(boolean exBossFlag) {
        this.exBossFlag = exBossFlag;
    }

    public int getDefeatCount() {
        return defeatCount;
    }

    public void setDefeatCount(int defeatCount) {
        this.defeatCount = defeatCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + mapId;
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
        MemberMapInfo other = (MemberMapInfo) obj;
        if (mapId != other.mapId)
            return false;
        if (memberId == null) {
            if (other.memberId != null)
                return false;
        } else if (!memberId.equals(other.memberId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("MemberMapInfo [member_id=%s, mapId=%s, isCleared=%s, exBossFlag=%s, defeatCount=%s]", memberId, mapId, isCleared, exBossFlag, defeatCount);
    }
}
