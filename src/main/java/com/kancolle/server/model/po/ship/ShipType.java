package com.kancolle.server.model.po.ship;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.Feature;

@Alias("ShipType")
public class ShipType implements Serializable {
    private static final long serialVersionUID = 2560822460694469946L;

    @JSONField(ordinal = 1, name = "api_id")
    private int shipTypeId;

    @JSONField(ordinal = 2, name = "api_sortno")
    private int sortNo;

    @JSONField(ordinal = 3, name = "api_name")
    private String name;

    @JSONField(ordinal = 4, name = "api_scnt")
    private int scnt;

    @JSONField(ordinal = 5, name = "api_kcnt")
    private int kcnt;

    @JSONField(ordinal = 6, name = "api_equip_type")
    private JSONObject equipTypes;

    public ShipType(String equipTypes) {
        this.equipTypes = JSON.parseObject(equipTypes, Feature.OrderedField);
    }

    public int getShipTypeId() {
        return shipTypeId;
    }

    public void setShipTypeId(int shipTypeId) {
        this.shipTypeId = shipTypeId;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScnt() {
        return scnt;
    }

    public void setScnt(int scnt) {
        this.scnt = scnt;
    }

    public int getKcnt() {
        return kcnt;
    }

    public void setKcnt(int kcnt) {
        this.kcnt = kcnt;
    }

    public JSONObject getEquipTypes() {
        return equipTypes;
    }

    public void setEquipTypes(JSONObject equipTypes) {
        this.equipTypes = equipTypes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + shipTypeId;
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
        ShipType other = (ShipType) obj;
        if (shipTypeId != other.shipTypeId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("ShipTypeModel [name=%s]", name);
    }
}
