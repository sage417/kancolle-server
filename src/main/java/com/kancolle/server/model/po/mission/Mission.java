/**
 * 
 */
package com.kancolle.server.model.po.mission;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.map.MapArea;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 *
 */
public class Mission implements Serializable {

    private static final long serialVersionUID = 8823401708226251266L;

    @JSONField(ordinal = 1)
    private int missionId;

    @JSONField(serialize = false, deserialize = false)
    private MapArea maparea;

    @JSONField(ordinal = 2)
    public int getMapareaId() {
        return maparea.getMapareaId();
    }

    private String name;

    private String details;

    private int time;

    private int difficulty;

    private double useFuel;

    private double useBull;

    private MissionItem winItem1;

    public int[] getItemWin1() {
        return winItem1.toArray();
    }

    private MissionItem winItem2;

    public int[] getItemWin2() {
        return winItem2.toArray();
    }

    private boolean returnFlag;

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

    public Mission(String name) {
        super();
        this.name = name;
    }
}
