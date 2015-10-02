/**
 * 
 */
package com.kancolle.server.model.po.ship;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年7月26日
 *
 */
@Alias("BaseShip")
public class BaseShip implements Serializable {

    private static final long serialVersionUID = -7322805228630342223L;

    @JSONField(ordinal = 1, name = "api_id")
    private int shipId;

    @JSONField(ordinal = 3, name = "api_name")
    private String name;

    @JSONField(ordinal = 4, name = "api_yomi")
    private String yomi;

    @JSONField(ordinal = 5, name = "api_stype")
    public int getShipTypeId() {
        return getType().getShipTypeId();
    }

    @JSONField(serialize = false, deserialize = false)
    private ShipType type;

    /** 速力 */
    @JSONField(ordinal = 14, name = "api_soku")
    private int soku;

    /** 可装备数 */
    @JSONField(ordinal = 16, name = "api_slot_num")
    private int soltNum;

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYomi() {
        return yomi;
    }

    public void setYomi(String yomi) {
        this.yomi = yomi;
    }

    public ShipType getType() {
        return type;
    }

    public void setType(ShipType type) {
        this.type = type;
    }

    public int getSoku() {
        return soku;
    }

    public void setSoku(int soku) {
        this.soku = soku;
    }

    public int getSoltNum() {
        return soltNum;
    }

    public void setSoltNum(int soltNum) {
        this.soltNum = soltNum;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + shipId;
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
        BaseShip other = (BaseShip) obj;
        if (shipId != other.shipId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("BaseShip [name=%s]", name);
    }
}
