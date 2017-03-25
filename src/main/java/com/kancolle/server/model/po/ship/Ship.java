/**
 *
 */
package com.kancolle.server.model.po.ship;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.po.common.MaxMinValue;
import com.kancolle.server.model.po.common.PowUpValue;
import com.kancolle.server.model.po.common.ResourceValue;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 */
@JsonPropertyOrder(value = {
        "shipId", "sortno", "name", "api_yomi",
        "shipTypeId", "afterLv", "afterShipId", "api_taik",
        "api_souk", "api_houg", "api_raig", "api_tyku",
        "api_luck", "soku", "leng", "slotNum",
        "maxEq", "buildTime", "api_broken", "api_powup",
        "api_backs", "getmes", "afterFuel", "afterBull",
        "fuelMax", "bullMax", "voicef"
})
@Alias("Ship")
public class Ship extends BaseShip implements Serializable {

    private static final long serialVersionUID = 6562446366794002946L;


    @JsonProperty(value = "api_sortno", index = 2)
    @JSONField(ordinal = 2, name = "api_sortno")
    private int sortno;

    /**
     * 改造等级
     */

    @JsonProperty(value = "api_afterlv", index = 6)
    @JSONField(ordinal = 6, name = "api_afterlv")
    private int afterLv;

    /**
     * 改造后
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private Ship afterShip;


    @JsonProperty(value = "api_aftershipid", index = 7)
    @JSONField(ordinal = 7, name = "api_aftershipid")
    public String afterShipId;

    /**
     * 耐久
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue taik;


    @JsonProperty(value = "api_taik", index = 8)
    @JSONField(ordinal = 8, name = "api_taik")
    public int[] getTaikArray() {
        return taik.toArray();
    }

    /**
     * 装甲
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue souk;


    @JsonProperty(value = "api_souk", index = 9)
    @JSONField(ordinal = 9, name = "api_souk")
    public int[] getSoukArray() {
        return souk.toArray();
    }

    /**
     * 火力
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue houg;


    @JsonProperty(value = "api_houg", index = 10)
    @JSONField(ordinal = 10, name = "api_houg")
    public int[] getHougArray() {
        return houg.toArray();
    }

    /**
     * 雷装
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue raig;


    @JsonProperty(value = "api_raig", index = 11)
    @JSONField(ordinal = 11, name = "api_raig")
    public int[] getRaigArray() {
        return raig.toArray();
    }

    /**
     * 对空
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue tyku;


    @JsonProperty(value = "api_tyku", index = 12)
    @JSONField(ordinal = 12, name = "api_tyku")
    public int[] getTykuArray() {
        return tyku.toArray();
    }

    /**
     * 幸运
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue luck;


    @JsonProperty(value = "api_luck", index = 13)
    @JSONField(ordinal = 13, name = "api_luck")
    public int[] getLuckArray() {
        return luck.toArray();
    }

    /**
     * 回避
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue kaihi;

    /**
     * 对潜
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue taisen;

    /**
     * 索敌
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue sakuteki;

    /**
     * 射程
     */

    @JsonProperty(value = "api_leng", index = 15)
    @JSONField(ordinal = 15, name = "api_leng")
    private int leng;

    /**
     * 搭载数
     */

    @JsonProperty(value = "api_maxeq", index = 17)
    @JSONField(ordinal = 17, name = "api_maxeq")
    private int[] maxEq;

    /**
     * 建造时间
     */

    @JsonProperty(value = "api_buildtime", index = 18)
    @JSONField(ordinal = 18, name = "api_buildtime")
    private int buildTime;

    /**
     * 解体
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private ResourceValue broken;


    @JsonProperty(value = "api_broken", index = 19)
    @JSONField(ordinal = 19, name = "api_broken")
    public int[] getBrokenArray() {
        return broken.getResourceArr();
    }

    /**
     * 改修
     */
    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private PowUpValue powUp;


    @JsonProperty(value = "api_powup", index = 20)
    @JSONField(ordinal = 20, name = "api_powup")
    public int[] getPowUpArray() {
        return powUp.getPowUpArr();
    }


    @JsonProperty(value = "api_backs", index = 21)
    @JSONField(ordinal = 21, name = "api_backs")
    private int backs;


    @JsonProperty(value = "api_getmes", index = 22)
    @JSONField(ordinal = 22, name = "api_getmes")
    private String getmes;

    /**
     * 改造燃油
     */

    @JsonProperty(value = "api_afterfuel", index = 23)
    @JSONField(ordinal = 23, name = "api_afterfuel")
    private int afterFuel;

    /**
     * 改造弹药
     */

    @JsonProperty(value = "api_afterbull", index = 24)
    @JSONField(ordinal = 24, name = "api_afterbull")
    private int afterBull;


    @JsonProperty(value = "api_fuel_max", index = 25)
    @JSONField(ordinal = 25, name = "api_fuel_max")
    private int fuelMax;


    @JsonProperty(value = "api_bull_max", index = 26)
    @JSONField(ordinal = 26, name = "api_bull_max")
    private int bullMax;


    @JsonProperty(value = "api_voicef", index = 27)
    @JSONField(ordinal = 27, name = "api_voicef")
    private int voicef;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private int lv;

    public int getSortno() {
        return sortno;
    }

    public void setSortno(int sortno) {
        this.sortno = sortno;
    }

    public int getAfterLv() {
        return afterLv;
    }

    public void setAfterLv(int afterLv) {
        this.afterLv = afterLv;
    }

    public Ship getAfterShip() {
        return afterShip;
    }

    public void setAfterShip(Ship afterShip) {
        this.afterShip = afterShip;
    }

    public String getAfterShipId() {
        return afterShipId;
    }

    public void setAfterShipId(String afterShipId) {
        this.afterShipId = afterShipId;
    }

    public MaxMinValue getTaik() {
        return taik;
    }

    public void setTaik(MaxMinValue taik) {
        this.taik = taik;
    }

    public MaxMinValue getSouk() {
        return souk;
    }

    public void setSouk(MaxMinValue souk) {
        this.souk = souk;
    }

    public MaxMinValue getHoug() {
        return houg;
    }

    public void setHoug(MaxMinValue houg) {
        this.houg = houg;
    }

    public MaxMinValue getRaig() {
        return raig;
    }

    public void setRaig(MaxMinValue raig) {
        this.raig = raig;
    }

    public MaxMinValue getTyku() {
        return tyku;
    }

    public void setTyku(MaxMinValue tyku) {
        this.tyku = tyku;
    }

    public MaxMinValue getLuck() {
        return luck;
    }

    public void setLuck(MaxMinValue luck) {
        this.luck = luck;
    }

    public MaxMinValue getKaihi() {
        return kaihi;
    }

    public void setKaihi(MaxMinValue kaihi) {
        this.kaihi = kaihi;
    }

    public MaxMinValue getTaisen() {
        return taisen;
    }

    public void setTaisen(MaxMinValue taisen) {
        this.taisen = taisen;
    }

    public MaxMinValue getSakuteki() {
        return sakuteki;
    }

    public void setSakuteki(MaxMinValue sakuteki) {
        this.sakuteki = sakuteki;
    }

    public int getLeng() {
        return leng;
    }

    public void setLeng(int leng) {
        this.leng = leng;
    }

    public int[] getMaxEq() {
        return maxEq;
    }

    public void setMaxEq(int[] maxEq) {
        this.maxEq = maxEq;
    }

    public int getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(int buildTime) {
        this.buildTime = buildTime;
    }

    public ResourceValue getBroken() {
        return broken;
    }

    public void setBroken(ResourceValue broken) {
        this.broken = broken;
    }

    public PowUpValue getPowUp() {
        return powUp;
    }

    public void setPowUp(PowUpValue powUp) {
        this.powUp = powUp;
    }

    public int getBacks() {
        return backs;
    }

    public void setBacks(int backs) {
        this.backs = backs;
    }

    public String getGetmes() {
        return getmes;
    }

    public void setGetmes(String getmes) {
        this.getmes = getmes;
    }

    public int getAfterFuel() {
        return afterFuel;
    }

    public void setAfterFuel(int afterFuel) {
        this.afterFuel = afterFuel;
    }

    public int getAfterBull() {
        return afterBull;
    }

    public void setAfterBull(int afterBull) {
        this.afterBull = afterBull;
    }

    public int getFuelMax() {
        return fuelMax;
    }

    public void setFuelMax(int fuelMax) {
        this.fuelMax = fuelMax;
    }

    public int getBullMax() {
        return bullMax;
    }

    public void setBullMax(int bullMax) {
        this.bullMax = bullMax;
    }

    public int getVoicef() {
        return voicef;
    }

    public void setVoicef(int voicef) {
        this.voicef = voicef;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getShipId();
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
        Ship other = (Ship) obj;
        return getShipId() == other.getShipId();
    }
}
