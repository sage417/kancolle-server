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

    private static final long serialVersionUID = 8823401708226251266L;

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

    @JSONField(serialize = false, deserialize = false)
    private MissionItem winItem1;

    @JSONField(ordinal = 9, name = "api_win_item1")
    public int[] getItemWin1() {
        return winItem1.toArray();
    }

    @JSONField(serialize = false, deserialize = false)
    private MissionItem winItem2;

    @JSONField(ordinal = 10, name = "api_win_item2")
    public int[] getItemWin2() {
        return winItem2.toArray();
    }

    @JSONField(ordinal = 11, name = "api_return_flag")
    private int returnFlag;

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

    public MissionItem getWinItem1() {
        return winItem1;
    }

    public void setWinItem1(MissionItem winItem1) {
        this.winItem1 = winItem1;
    }

    public MissionItem getWinItem2() {
        return winItem2;
    }

    public void setWinItem2(MissionItem winItem2) {
        this.winItem2 = winItem2;
    }

    public int getReturnFlag() {
        return returnFlag;
    }

    public void setReturnFlag(int returnFlag) {
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

    public Mission(String name) {
        super();
        this.name = name;
    }
}
