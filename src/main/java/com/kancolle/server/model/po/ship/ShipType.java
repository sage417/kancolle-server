package com.kancolle.server.model.po.ship;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "shipTypeId", "sortNo", "name", "scnt",
        "kcnt", "equipTypes"
})
@Alias("ShipType")
public class ShipType implements Serializable {
    private static final long serialVersionUID = 2560822460694469946L;

    public static final int TYPE_DD = 2;

    public static final int TYPE_CL = 3;

    public static final int TYPE_CLT = 4;

    public static final int TYPE_CA = 5;

    public static final int TYPE_CAV = 6;

    public static final int TYPE_CVL = 7;

    public static final int TYPE_BB1 = 8;

    public static final int TYPE_BB2 = 9;

    public static final int TYPE_BBV = 10;

    public static final int TYPE_CV = 11;

    public static final int TYPE_BBS = 12;

    public static final int TYPE_SS = 13;

    public static final int TYPE_SSV = 14;

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1, name = "api_id")
    private int shipTypeId;

    @JsonProperty(value = "api_sortno")
    @JSONField(ordinal = 2, name = "api_sortno")
    private int sortNo;

    @JsonProperty(value = "api_name")
    @JSONField(ordinal = 3, name = "api_name")
    private String name;

    @JsonProperty(value = "api_scnt")
    @JSONField(ordinal = 4, name = "api_scnt")
    private int scnt;

    @JsonProperty(value = "api_kcnt")
    @JSONField(ordinal = 5, name = "api_kcnt")
    private int kcnt;

    @JsonProperty(value = "api_equip_type")
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
        return String.format("ShipType [name=%s]", name);
    }
}
