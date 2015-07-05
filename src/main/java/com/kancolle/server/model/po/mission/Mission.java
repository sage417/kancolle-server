/**
 * 
 */
package com.kancolle.server.model.po.mission;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.map.MapArea;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 *
 */
@Alias("Mission")
public class Mission implements Serializable {

    private static final long serialVersionUID = -3997082699476879973L;

    @JSONField(ordinal = 1, name = "api_id")
    private int missionId;

    @JSONField(serialize = false, deserialize = false)
    private MapArea maparea;

    @JSONField(ordinal = 2, name = "api_maparea_id")
    public int getMapareaId() {
        return maparea.getMapareaId();
    }

    @JSONField(ordinal = 3, name = "api_name")
    private String name;

    @JSONField(ordinal = 4, name = "api_details")
    private String details;

    @JSONField(ordinal = 5, name = "api_time")
    private int time;

    @JSONField(ordinal = 6, name = "api_difficulty")
    private int difficulty;

    @JSONField(ordinal = 7, name = "api_use_fuel")
    private double useFuel;

    @JSONField(ordinal = 8, name = "api_use_bull")
    private double useBull;

    @JSONField(ordinal = 9, name = "api_win_item1")
    private int[] winItem1;

    @JSONField(ordinal = 10, name = "api_win_item2")
    private int[] winItem2;

    @JSONField(serialize = false, deserialize = false)
    private boolean returnFlag;

    @JSONField(ordinal = 11, name = "api_return_flag")
    public int returnFlag() {
        return returnFlag ? 1 : 0;
    }

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
