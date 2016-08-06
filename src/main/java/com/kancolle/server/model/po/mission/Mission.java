/**
 *
 */
package com.kancolle.server.model.po.mission;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.po.map.MapArea;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 */
@JsonPropertyOrder(value = {
        "missionId", "mapAreaId", "name", "details",
        "time", "difficulty", "useFuel", "useBull",
        "winItem1", "winItem2", "api_return_flag"
})
@Alias("Mission")
public class Mission implements Serializable {

    private static final long serialVersionUID = -3997082699476879973L;

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1, name = "api_id")
    private int missionId;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private MapArea maparea;

    @JsonProperty(value = "api_maparea_id")
    @JSONField(ordinal = 2, name = "api_maparea_id")
    private int mapAreaId;

    @JsonProperty(value = "api_name")
    @JSONField(ordinal = 3, name = "api_name")
    private String name;

    @JsonProperty(value = "api_details")
    @JSONField(ordinal = 4, name = "api_details")
    private String details;

    @JsonProperty(value = "api_time")
    @JSONField(ordinal = 5, name = "api_time")
    private int time;

    @JsonProperty(value = "api_difficulty")
    @JSONField(ordinal = 6, name = "api_difficulty")
    private int difficulty;

    @JsonProperty(value = "api_use_fuel")
    @JSONField(ordinal = 7, name = "api_use_fuel")
    private double useFuel;

    @JsonProperty(value = "api_use_bull")
    @JSONField(ordinal = 8, name = "api_use_bull")
    private double useBull;

    @JsonProperty(value = "api_win_item1")
    @JSONField(ordinal = 9, name = "api_win_item1")
    private int[] winItem1;

    @JsonProperty(value = "api_win_item2")
    @JSONField(ordinal = 10, name = "api_win_item2")
    private int[] winItem2;

    @JsonProperty(value = "api_return_flag")
    @JSONField(ordinal = 11, name = "api_return_flag")
    public int returnFlag() {
        return returnFlag ? 1 : 0;
    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private boolean returnFlag;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private MissionExp missionExp;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private int[] materials;

    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public MapArea getMaparea() {
        return maparea;
    }

    public void setMaparea(MapArea maparea) {
        this.maparea = maparea;
    }

    public int getMapAreaId() {
        return mapAreaId;
    }

    public void setMapAreaId(int mapAreaId) {
        this.mapAreaId = mapAreaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public double getUseFuel() {
        return useFuel;
    }

    public void setUseFuel(double useFuel) {
        this.useFuel = useFuel;
    }

    public double getUseBull() {
        return useBull;
    }

    public void setUseBull(double useBull) {
        this.useBull = useBull;
    }

    public int[] getWinItem1() {
        return winItem1;
    }

    public void setWinItem1(int[] winItem1) {
        this.winItem1 = winItem1;
    }

    public int[] getWinItem2() {
        return winItem2;
    }

    public void setWinItem2(int[] winItem2) {
        this.winItem2 = winItem2;
    }

    public boolean isReturnFlag() {
        return returnFlag;
    }

    public void setReturnFlag(boolean returnFlag) {
        this.returnFlag = returnFlag;
    }

    public MissionExp getMissionExp() {
        return missionExp;
    }

    public void setMissionExp(MissionExp missionExp) {
        this.missionExp = missionExp;
    }

    public int[] getMaterials() {
        return materials;
    }

    public void setMaterials(int[] materials) {
        this.materials = materials;
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
        Mission other = (Mission) obj;
        if (missionId != other.missionId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Mission [name=%s]", name);
    }
}
