/**
 * 
 */
package com.kancolle.server.model.po.ship;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.common.MaxMinValue;
import com.kancolle.server.model.po.common.PowUpValue;
import com.kancolle.server.model.po.common.ResourceValue;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 *
 */
@Alias("Ship")
public class Ship implements Serializable {

    private static final long serialVersionUID = 6562446366794002946L;

    @JSONField(ordinal = 1, name = "api_id")
    private int shipId;

    @JSONField(ordinal = 2, name = "api_sortno")
    private int sortno;

    @JSONField(ordinal = 3, name = "api_name")
    private String name;

    @JSONField(ordinal = 4, name = "api_yomi")
    private String yomi;

    @JSONField(ordinal = 5, name = "api_stype")
    public int returnShipTypeId() {
        return getType().getShipTypeId();
    }

    private ShipType type;

    /** 改造等级 */
    @JSONField(ordinal = 6, name = "api_afterlv")
    private int afterLv;

    /** 改造后 */
    @JSONField(serialize = false, deserialize = false)
    private Ship afterShip;

    @JSONField(ordinal = 7, name = "api_aftershipid")
    public String getAfterShipId() {
        return afterShip == null ? "0" : Integer.toString(afterShip.getShipId());
    }

    /** 耐久 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue taik;

    @JSONField(ordinal = 8, name = "api_taik")
    public int[] getTaikArray() {
        return taik.toArray();
    }

    /** 装甲 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue souk;

    @JSONField(ordinal = 9, name = "api_souk")
    public int[] getSoukArray() {
        return souk.toArray();
    }

    /** 火力 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue houg;

    @JSONField(ordinal = 10, name = "api_houg")
    public int[] getHougArray() {
        return houg.toArray();
    }

    /** 雷装 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue raig;

    @JSONField(ordinal = 11, name = "api_raig")
    public int[] getRaigArray() {
        return raig.toArray();
    }

    /** 对空 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue tyku;

    @JSONField(ordinal = 12, name = "api_tyku")
    public int[] getTykuArray() {
        return tyku.toArray();
    }

    /** 幸运 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue luck;

    @JSONField(ordinal = 13, name = "api_luck")
    public int[] getLuckArray() {
        return luck.toArray();
    }

    /** 回避 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue kaihi;

    /** 对潜 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue taisen;

    /** 索敌 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue sakuteki;

    /** 速力 */
    @JSONField(ordinal = 14, name = "api_soku")
    private int soku;

    /** 射程 */
    @JSONField(ordinal = 15, name = "api_leng")
    private int leng;

    /** 可装备数 */
    @JSONField(ordinal = 16, name = "api_slot_num")
    private int soltNum;

    /** 搭载数 */
    @JSONField(ordinal = 17, name = "api_maxeq")
    private int[] maxEq;

    /** 建造时间 */
    @JSONField(ordinal = 18, name = "api_buildtime")
    private int buildTime;

    /** 解体 */
    @JSONField(serialize = false, deserialize = false)
    private ResourceValue broken;

    @JSONField(ordinal = 19, name = "api_broken")
    public int[] getBrokenArray() {
        return broken.toArray();
    }

    /** 改修 */
    @JSONField(serialize = false, deserialize = false)
    private PowUpValue powUp;

    @JSONField(ordinal = 20, name = "api_powup")
    public int[] getPowUpArray() {
        return powUp.toArray();
    }

    @JSONField(ordinal = 21, name = "api_backs")
    private int backs;

    @JSONField(ordinal = 22, name = "api_getmes")
    private String getmes;

    /** 改造燃油 */
    @JSONField(ordinal = 23, name = "api_afterfuel")
    private int afterFuel;

    /** 改造弹药 */
    @JSONField(ordinal = 24, name = "api_afterbull")
    private int afterBull;

    @JSONField(ordinal = 25, name = "api_fuel_max")
    private int fuelMax;

    @JSONField(ordinal = 26, name = "api_bull_max")
    private int bullMax;

    @JSONField(ordinal = 27, name = "api_voicef")
    private int voicef;

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public int getSortno() {
        return sortno;
    }

    public void setSortno(int sortno) {
        this.sortno = sortno;
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

    public ShipType getType() {
        return type;
    }

    public void setType(ShipType type) {
        this.type = type;
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

    public int getSoku() {
        return soku;
    }

    public void setSoku(int soku) {
        this.soku = soku;
    }

    public int getLeng() {
        return leng;
    }

    public void setLeng(int leng) {
        this.leng = leng;
    }

    public int getSoltNum() {
        return soltNum;
    }

    public void setSoltNum(int soltNum) {
        this.soltNum = soltNum;
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
        Ship other = (Ship) obj;
        if (shipId != other.shipId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Ship [name=%s]", name);
    }
}
