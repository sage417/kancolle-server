/**
 * 
 */
package com.kancolle.server.model.po.ship;

import org.apache.ibatis.type.Alias;

import com.kancolle.server.model.po.common.MaxMinValue;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 *
 */
@Alias("MemberShip")
public class MemberShip {

    private long memberShipId;

    private Ship ship;

    private int lv;

    private long[] exp;

    private int nowHp;

    private int maxHp;

    private int leng;

    private long[] slot;

    private int[] onslot;

    private int[] kyouka;

    private int fuel;

    private int bull;

    private long ndockTime;

    private int[] ndockItem;

    private int srate;

    private int cond;

    /** 火力 */
    private MaxMinValue karyoku;

    /** 雷装 */
    private MaxMinValue raisou;

    /** 对空 */
    private MaxMinValue taiku;

    /** 装甲 */
    private MaxMinValue soukou;

    /** 回避 */
    private MaxMinValue kaihi;

    /** 对潜 */
    private MaxMinValue taisen;

    /** 索敌 */
    private MaxMinValue sakuteki;

    /** 运 */
    private MaxMinValue lucky;

    public long getMemberShipId() {
        return memberShipId;
    }

    public void setMemberShipId(long memberShipId) {
        this.memberShipId = memberShipId;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public long[] getExp() {
        return exp;
    }

    public void setExp(long[] exp) {
        this.exp = exp;
    }

    public int getNowHp() {
        return nowHp;
    }

    public void setNowHp(int nowHp) {
        this.nowHp = nowHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getLeng() {
        return leng;
    }

    public void setLeng(int leng) {
        this.leng = leng;
    }

    public long[] getSlot() {
        return slot;
    }

    public void setSlot(long[] slot) {
        this.slot = slot;
    }

    public int[] getOnslot() {
        return onslot;
    }

    public void setOnslot(int[] onslot) {
        this.onslot = onslot;
    }

    public int[] getKyouka() {
        return kyouka;
    }

    public void setKyouka(int[] kyouka) {
        this.kyouka = kyouka;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getBull() {
        return bull;
    }

    public void setBull(int bull) {
        this.bull = bull;
    }

    public long getNdockTime() {
        return ndockTime;
    }

    public void setNdockTime(long ndockTime) {
        this.ndockTime = ndockTime;
    }

    public int[] getNdockItem() {
        return ndockItem;
    }

    public void setNdockItem(int[] ndockItem) {
        this.ndockItem = ndockItem;
    }

    public int getSrate() {
        return srate;
    }

    public void setSrate(int srate) {
        this.srate = srate;
    }

    public int getCond() {
        return cond;
    }

    public void setCond(int cond) {
        this.cond = cond;
    }

    public MaxMinValue getKaryoku() {
        return karyoku;
    }

    public void setKaryoku(MaxMinValue karyoku) {
        this.karyoku = karyoku;
    }

    public MaxMinValue getRaisou() {
        return raisou;
    }

    public void setRaisou(MaxMinValue raisou) {
        this.raisou = raisou;
    }

    public MaxMinValue getTaiku() {
        return taiku;
    }

    public void setTaiku(MaxMinValue taiku) {
        this.taiku = taiku;
    }

    public MaxMinValue getSoukou() {
        return soukou;
    }

    public void setSoukou(MaxMinValue soukou) {
        this.soukou = soukou;
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

    public MaxMinValue getLucky() {
        return lucky;
    }

    public void setLucky(MaxMinValue lucky) {
        this.lucky = lucky;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (memberShipId ^ (memberShipId >>> 32));
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
        MemberShip other = (MemberShip) obj;
        if (memberShipId != other.memberShipId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("MemberShip [ship=%s, lv=%s, cond=%s]", ship, lv, cond);
    }
}
