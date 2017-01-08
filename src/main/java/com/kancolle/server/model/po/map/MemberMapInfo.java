/**
 *
 */
package com.kancolle.server.model.po.map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.MoreObjects;
import com.kancolle.server.utils.jackson.NumericBooleanSerializer;
import org.apache.ibatis.type.Alias;

import java.util.Objects;

/**
 * @author J.K.SAGE
 * @Date 2015年8月17日
 */
@Alias("MemberMapInfo")
@JsonPropertyOrder(value = {
        "api_id", "api_cleared", "api_air_base_decks", "api_exboss_flag", "api_defeat_count"
})
public class MemberMapInfo {

    @JsonIgnore
    private long memberId;

    @JsonProperty(value = "api_id")
    private int mapId;

    @JsonProperty(value = "api_cleared")
    @JsonSerialize(using = NumericBooleanSerializer.class)
    private boolean cleared;

    @JsonProperty(value = "api_exboss_flag")
    @JsonSerialize(using = NumericBooleanSerializer.class)
    private boolean exBossFlag;

    @JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
    @JsonProperty(value = "api_defeat_count")
    private int defeatCount;

    @JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
    @JsonProperty(value = "api_air_base_decks")
    private int airBaseDecks;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
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

    public int getAirBaseDecks() {
        return airBaseDecks;
    }

    public void setAirBaseDecks(int airBaseDecks) {
        this.airBaseDecks = airBaseDecks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberMapInfo that = (MemberMapInfo) o;
        return mapId == that.mapId &&
                Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, mapId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("memberId", memberId)
                .add("mapId", mapId)
                .add("cleared", cleared)
                .add("exBossFlag", exBossFlag)
                .add("defeatCount", defeatCount)
                .add("airBaseDecks", airBaseDecks)
                .toString();
    }
}
