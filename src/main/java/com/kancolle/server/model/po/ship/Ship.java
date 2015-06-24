/**
 * 
 */
package com.kancolle.server.model.po.ship;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.kancolle.server.model.po.common.MaxMinValue;
import com.kancolle.server.model.po.common.PowUpValue;
import com.kancolle.server.model.po.common.ResourceValue;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 *
 */
@Alias("Ship")
public class Ship implements Serializable{

    private static final long serialVersionUID = 6562446366794002946L;

    private int shipId;

    private int sortno;

    private String name;

    private String yomi;

    /** 改造等级 */
    private int afterLv;

    private int type;

    /** 改造后 */
    private Ship afterShip;

    /** 耐久 */
    private MaxMinValue taik;

    /** 装甲 */
    private MaxMinValue souk;

    /** 火力 */
    private MaxMinValue houg;

    /** 雷装 */
    private MaxMinValue raig;

    /** 对空 */
    private MaxMinValue tyku;

    /** 幸运 */
    private MaxMinValue luck;

    /** 速力 */
    private int soku;

    /** 射程 */
    private int leng;

    /** 可装备数 */
    private int soltNum;

    /** 搭载数 */
    private int[] maxEq;

    /** 建造时间 */
    private int buildTime;

    /** 解体 */
    private ResourceValue broken;

    /** 改修 */
    private PowUpValue powUp;

    private int backs;

    private String getmes;

    /** 改造燃油 */
    private int afterFuel;

    /** 改造弹药 */
    private int afterBull;

    private int fuelMax;

    private int bullMax;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
