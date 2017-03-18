/**
 *
 */
package com.kancolle.server.model.po.ship;

import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import org.apache.ibatis.type.Alias;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Transient;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 */
@Alias("SlimShip")
public class SlimShip {
    @Transient
    private long id;
    @Property("id")
    private int shipId;
    @Transient
    private Ship ship;
    @Property("mid")
    private long memberShipId;
    @Property("LVL")
    private int lv;
    @Property("HP")
    private int nowHp;
    @Property("maxHP")
    private int maxHp;
    @Property("RNG")
    private int leng;
    @Embedded("equips")
    private List<MemberSlotItem> slot;
    @Property("planeslots")
    private int[] onslot;
    @Property("fuelleft")
    private int fuel;
    @Property("ammoleft")
    private int bull;
    @Property("morale")
    private int cond;
    /**
     * 火力
     */
    @Property("FP")
    private int karyoku;
    /**
     * 雷装
     */
    @Property("TP")
    private int raisou;
    /**
     * 对空
     */
    @Property("AA")
    private int taiku;
    /**
     * 装甲
     */
    @Property("AR")
    private int soukou;
    /**
     * 回避
     */
    @Property("EV")
    private int kaihi;
    /**
     * 对潜
     */
    @Property("ASW")
    private int taisen;
    /**
     * 索敌
     */
    @Property("LOS")
    private int sakuteki;
    /**
     * 运
     */
    @Property("LUK")
    private int lucky;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public long getMemberShipId() {
        return memberShipId;
    }

    public void setMemberShipId(long memberShipId) {
        this.memberShipId = memberShipId;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
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

    public List<MemberSlotItem> getSlot() {
        return slot;
    }

    public void setSlot(List<MemberSlotItem> slot) {
        this.slot = slot;
    }

    public int[] getOnslot() {
        return onslot;
    }

    public void setOnslot(int[] onslot) {
        this.onslot = onslot;
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

    public int getCond() {
        return cond;
    }

    public void setCond(int cond) {
        this.cond = cond;
    }

    public int getKaryoku() {
        return karyoku;
    }

    public void setKaryoku(int karyoku) {
        this.karyoku = karyoku;
    }

    public int getRaisou() {
        return raisou;
    }

    public void setRaisou(int raisou) {
        this.raisou = raisou;
    }

    public int getTaiku() {
        return taiku;
    }

    public void setTaiku(int taiku) {
        this.taiku = taiku;
    }

    public int getSoukou() {
        return soukou;
    }

    public void setSoukou(int soukou) {
        this.soukou = soukou;
    }

    public int getKaihi() {
        return kaihi;
    }

    public void setKaihi(int kaihi) {
        this.kaihi = kaihi;
    }

    public int getTaisen() {
        return taisen;
    }

    public void setTaisen(int taisen) {
        this.taisen = taisen;
    }

    public int getSakuteki() {
        return sakuteki;
    }

    public void setSakuteki(int sakuteki) {
        this.sakuteki = sakuteki;
    }

    public int getLucky() {
        return lucky;
    }

    public void setLucky(int lucky) {
        this.lucky = lucky;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlimShip that = (SlimShip) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return String.format("SlimShip [memberShipId=%s, ship=%s, lv=%s]", memberShipId, getShip(), lv);
    }
}