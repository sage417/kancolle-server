/**
 * 
 */
package com.kancolle.server.model.po.ship;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.common.MaxMinValue;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.utils.logic.NdockUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 *
 */
@Alias("MemberShip")
public class MemberShip {
    public static final int SLOT_SIZE_MAX = 5;

    @JSONField(serialize = false, deserialize = false)
    private long memberId;

    @JSONField(ordinal = 1, name = "api_id")
    private long memberShipId;

    @JSONField(serialize = false, deserialize = false)
    private Ship ship;

    @JSONField(ordinal = 2)
    private int api_sortno;

    @JSONField(ordinal = 3)
    private int api_ship_id;

    @JSONField(ordinal = 4, name = "api_lv")
    private int lv;

    @JSONField(ordinal = 5, name = "api_exp")
    private long[] exp;

    @JSONField(ordinal = 6, name = "api_nowhp")
    private int nowHp;

    @JSONField(ordinal = 7, name = "api_maxhp")
    private int maxHp;

    @JSONField(ordinal = 8, name = "api_leng")
    private int leng;

    @JSONField(ordinal = 9)
    private long[] api_slot;

    @JSONField(serialize = false, deserialize = false)
    private List<MemberSlotItem> slot;

    @JSONField(ordinal = 10, name = "api_onslot")
    private int[] onslot;

    @JSONField(ordinal = 11, name = "api_kyouka")
    private int[] kyouka;

    @JSONField(ordinal = 12)
    private int api_backs;

    @JSONField(ordinal = 13, name = "api_fuel")
    private int fuel;

    @JSONField(ordinal = 14, name = "api_bull")
    private int bull;

    @JSONField(ordinal = 15)
    private int api_slotnum;

    @JSONField(ordinal = 16)
    private long api_ndock_time;

    @JSONField(ordinal = 17)
    private int[] api_ndock_item;

    @JSONField(ordinal = 18, name = "api_srate")
    private int srate;

    @JSONField(ordinal = 19, name = "api_cond")
    private int cond;

    @JSONField(ordinal = 20)
    private int[] api_karyoku;

    @JSONField(ordinal = 21)
    private int[] api_raisou;

    @JSONField(ordinal = 22)
    private int[] api_taiku;

    @JSONField(ordinal = 23)
    private int[] api_soukou;

    @JSONField(ordinal = 24)
    private int[] api_kaihi;

    @JSONField(ordinal = 25)
    private int[] api_taisen;

    @JSONField(ordinal = 26)
    private int[] api_sakuteki;

    @JSONField(ordinal = 27)
    private int[] api_lucky;

    /** 火力 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue karyoku;

    /** 雷装 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue raisou;

    /** 对空 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue taiku;

    /** 装甲 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue soukou;

    /** 回避 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue kaihi;

    /** 对潜 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue taisen;

    /** 索敌 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue sakuteki;

    /** 运 */
    @JSONField(serialize = false, deserialize = false)
    private MaxMinValue lucky;

    @JSONField(serialize = false, deserialize = false)
    private boolean locked;

    @JSONField(ordinal = 28, name = "api_locked")
    public int getLockStatue() {
        return locked ? 1 : 0;
    }

    @JSONField(serialize = false, deserialize = false)
    private boolean lockedEquip;

    @JSONField(ordinal = 29, name = "api_locked_equip")
    public int getEquipLockStatue() {
        return lockedEquip ? 1 : 0;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

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
        setApi_karyoku(karyoku.toArray());
    }

    public MaxMinValue getRaisou() {
        return raisou;
    }

    public void setRaisou(MaxMinValue raisou) {
        this.raisou = raisou;
        setApi_raisou(raisou.toArray());
    }

    public MaxMinValue getTaiku() {
        return taiku;
    }

    public void setTaiku(MaxMinValue taiku) {
        this.taiku = taiku;
        setApi_taiku(taiku.toArray());
    }

    public MaxMinValue getSoukou() {
        return soukou;
    }

    public void setSoukou(MaxMinValue soukou) {
        this.soukou = soukou;
        setApi_soukou(soukou.toArray());
    }

    public MaxMinValue getKaihi() {
        return kaihi;
    }

    public void setKaihi(MaxMinValue kaihi) {
        this.kaihi = kaihi;
        setApi_kaihi(kaihi.toArray());
    }

    public MaxMinValue getTaisen() {
        return taisen;
    }

    public void setTaisen(MaxMinValue taisen) {
        this.taisen = taisen;
        setApi_taisen(taisen.toArray());
    }

    public MaxMinValue getSakuteki() {
        return sakuteki;
    }

    public void setSakuteki(MaxMinValue sakuteki) {
        this.sakuteki = sakuteki;
        setApi_sakuteki(sakuteki.toArray());
    }

    public MaxMinValue getLucky() {
        return lucky;
    }

    public void setLucky(MaxMinValue lucky) {
        this.lucky = lucky;
        setApi_lucky(lucky.toArray());
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isLockedEquip() {
        return lockedEquip;
    }

    public void setLockedEquip(boolean lockedEquip) {
        this.lockedEquip = lockedEquip;
    }

    public long getApi_ndock_time() {
        this.api_ndock_time = NdockUtils.getNdockTime(getLv(), getMaxHp() - getNowHp(), getShip().getType()) * 1000L;
        return this.api_ndock_time;
    }

    public void setApi_ndock_time(long api_ndock_time) {
        throw new UnsupportedOperationException();
    }

    public int[] getApi_ndock_item() {
        this.api_ndock_item = NdockUtils.getNdockItem(getMaxHp() - getNowHp(), getShip().getType());
        return this.api_ndock_item;
    }

    public void setApi_ndock_item(int[] api_ndock_item) {
        throw new UnsupportedOperationException();
    }

    public int[] getKyouka() {
        return this.kyouka;
    }

    public void setKyouka(int[] kyouka) {
        this.kyouka = kyouka;
    }

    public int getApi_sortno() {
        return getShip().getSortno();
    }

    public void setApi_sortno(int api_sortno) {
        throw new UnsupportedOperationException();
    }

    public int getApi_ship_id() {
        return getShip().getShipId();
    }

    public void setApi_ship_id(int api_ship_id) {
        throw new UnsupportedOperationException();
    }

    public long[] getApi_slot() {
        return api_slot;
    }

    public void setApi_slot(long[] api_slot) {
        this.api_slot = api_slot;
    }

    public int getApi_backs() {
        return getShip().getBacks();
    }

    public void setApi_backs(int api_backs) {
        throw new UnsupportedOperationException();
    }

    public int getApi_slotnum() {
        return getShip().getSoltNum();
    }

    public void setApi_slotnum(int api_slotnum) {
        throw new UnsupportedOperationException();
    }

    public int[] getApi_karyoku() {
        return api_karyoku;
    }

    public void setApi_karyoku(int[] api_karyoku) {
        this.api_karyoku = api_karyoku;
    }

    public int[] getApi_raisou() {
        return api_raisou;
    }

    public void setApi_raisou(int[] api_raisou) {
        this.api_raisou = api_raisou;
    }

    public int[] getApi_taiku() {
        return api_taiku;
    }

    public void setApi_taiku(int[] api_taiku) {
        this.api_taiku = api_taiku;
    }

    public int[] getApi_soukou() {
        return api_soukou;
    }

    public void setApi_soukou(int[] api_soukou) {
        this.api_soukou = api_soukou;
    }

    public int[] getApi_kaihi() {
        return api_kaihi;
    }

    public void setApi_kaihi(int[] api_kaihi) {
        this.api_kaihi = api_kaihi;
    }

    public int[] getApi_taisen() {
        return api_taisen;
    }

    public void setApi_taisen(int[] api_taisen) {
        this.api_taisen = api_taisen;
    }

    public int[] getApi_sakuteki() {
        return api_sakuteki;
    }

    public void setApi_sakuteki(int[] api_sakuteki) {
        this.api_sakuteki = api_sakuteki;
    }

    public int[] getApi_lucky() {
        return api_lucky;
    }

    public void setApi_lucky(int[] api_lucky) {
        this.api_lucky = api_lucky;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (memberId ^ (memberId >>> 32));
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
        if (memberId != other.memberId)
            return false;
        if (memberShipId != other.memberShipId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("MemberShip [memberId=%s, memberShipId=%s, ship=%s, lv=%s]", memberId, memberShipId, ship, lv);
    }
}
