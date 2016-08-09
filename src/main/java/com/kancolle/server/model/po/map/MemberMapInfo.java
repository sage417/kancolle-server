/**
 *
 */
package com.kancolle.server.model.po.map;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kancolle.server.utils.jackson.NumericBooleanSerializer;
import org.apache.ibatis.type.Alias;

/**
 * @author J.K.SAGE
 * @Date 2015年8月17日
 */
@JsonPropertyOrder(value = {
        "mapId", "isCleared", "exBossFlag", "defeatCount"
})
@Alias("MemberMapInfo")
public class MemberMapInfo {

    @JSONField(serialize = false, deserialize = false)
    private String memberId;

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1, name = "api_id")
    private int mapId;

    @JsonProperty(value = "api_cleared")
    @JsonSerialize(using = NumericBooleanSerializer.class)
    private boolean isCleared;

    @JsonProperty(value = "api_exboss_flag")
    @JsonSerialize(using = NumericBooleanSerializer.class)
    private boolean exBossFlag;

    @JsonProperty(value = "api_defeat_countS")
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
