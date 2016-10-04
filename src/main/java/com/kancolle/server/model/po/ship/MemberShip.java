/**
 *
 */
package com.kancolle.server.model.po.ship;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kancolle.server.model.po.common.MaxMinValue;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.utils.jackson.NumericBooleanSerializer;
import com.kancolle.server.utils.logic.MemberShipUtils;
import com.kancolle.server.utils.logic.NdockUtils;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 */
@JsonPropertyOrder(value = {
        "memberShipId", "api_sortno", "shipId", "lv",
        "exp", "nowHp", "maxHp", "leng",
        "slotIds", "onslot", "kyouka", "api_backs",
        "fuel", "bull", "api_slotnum", "ndockTime",
        "ndockItem", "api_srate", "cond", "api_karyoku",
        "api_raisou", "api_taiku", "api_soukou", "api_kaihi",
        "api_taisen", "api_sakuteki", "api_lucky", "locked",
        "lockedEquip"
})
@Alias("MemberShip")
public class MemberShip implements IShip, Serializable {

    private static final long serialVersionUID = -1844625754351796002L;

    public static final int SLOT_SIZE_MAX = 5;

    public static final int BAD_COND = 30;

    public static final int WARN_COND = 40;

    public static final int GOOD_COND = 50;

    public static final int MAX_COND = 100;

    @JsonIgnore
    private long id;

    @JsonProperty(value = "api_ship_id")
    private int shipId;

    @JsonIgnore
    private Ship ship;

    @JsonIgnore
    private String memberId;

    @JsonProperty(value = "api_id")
    private long memberShipId;

    @JsonProperty(value = "api_lv")
    private int lv;

    @JsonProperty(value = "api_exp")
    private long[] exp;

    @JsonProperty(value = "api_nowhp")
    private int nowHp;

    @JsonProperty(value = "api_maxhp")
    private int maxHp;

    @JsonProperty(value = "api_leng")
    private int leng;

    @JsonProperty(value = "api_slot")
    private long[] slotIds;

    @JsonIgnore
    private List<MemberSlotItem> slot;

    @JsonProperty(value = "api_onslot")
    private int[] onslot;

    @JsonProperty(value = "api_kyouka")
    private int[] kyouka;

    @JsonProperty(value = "api_fuel")
    private int fuel;

    @JsonProperty(value = "api_bull")
    private int bull;

    @JsonProperty(value = "api_ndock_time")
    private long ndockTime;

    @JsonProperty(value = "api_ndock_item")
    private int[] ndockItem;

    @JsonProperty(value = "api_cond")
    private int cond;
    /**
     * 火力
     */
    @JsonIgnore
    private MaxMinValue karyoku;
    /**
     * 雷装
     */
    @JsonIgnore
    private MaxMinValue raisou;
    /**
     * 对空
     */
    @JsonIgnore
    private MaxMinValue taiku;
    /**
     * 装甲
     */
    @JsonIgnore
    private MaxMinValue soukou;
    /**
     * 回避
     */
    @JsonIgnore
    private MaxMinValue kaihi;
    /**
     * 对潜
     */
    @JsonIgnore
    private MaxMinValue taisen;
    /**
     * 索敌
     */
    @JsonIgnore
    private MaxMinValue sakuteki;
    /**
     * 运
     */
    @JsonIgnore
    private MaxMinValue lucky;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty(value = "api_sortno")
    public int returnSortNo() {
        return getShip().getSortno();
    }

    @JsonProperty(value = "api_backs")
    public int returnBacks() {
        return getShip().getBacks();
    }

    @JsonProperty(value = "api_slotnum")
    public int returnslotNum() {
        return getShip().getSlotNum();
    }

    @JsonProperty(value = "api_srate")
    public int returnSrate() {
        int grownSum = IntStream.of(MemberShipUtils.getShipPowupMaxArray(getShip())).sum();
        int grownValue = IntStream.of(getKyouka()).sum();
        return (int) (5f * grownValue / grownSum + 1);
    }

    @JsonProperty(value = "api_karyoku")
    public int[] returnKaryoku() {
        return getKaryoku().toArray();
    }

    @JsonProperty(value = "api_raisou")
    public int[] returnRaisou() {
        return getRaisou().toArray();
    }

    @JsonProperty(value = "api_taiku")
    public int[] returnTaiku() {
        return getTaiku().toArray();
    }

    @JsonProperty(value = "api_soukou")
    public int[] returnSoukou() {
        return getSoukou().toArray();
    }

    @JsonProperty(value = "api_kaihi")
    public int[] returnKaihi() {
        return getKaihi().toArray();
    }

    @JsonProperty(value = "api_taisen")
    public int[] returnTaisen() {
        return getTaisen().toArray();
    }

    @JsonProperty(value = "api_sakuteki")
    public int[] returnSakuteki() {
        return getSakuteki().toArray();
    }

    @JsonProperty(value = "api_lucky")
    public int[] returnLuck() {
        return getLucky().toArray();
    }

    @JsonSerialize(using = NumericBooleanSerializer.class)
    private boolean locked;

    @JsonSerialize(using = NumericBooleanSerializer.class)
    private boolean lockedEquip;

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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public long[] getExp() {
        return exp;
    }

    public void setExp(long[] exp) {
        this.exp = exp;
    }

    @Override
    public int getNowHp() {
        return nowHp;
    }

    @Override
    public void setNowHp(int nowHp) {
        this.nowHp = nowHp;
    }

    @Override
    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    @Override
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

    public long getNdockTime() {
        this.ndockTime = NdockUtils.getNdockTime(getLv(), getMaxHp() - getNowHp(), getShip().getShipTypeId()) * 1000L;
        return this.ndockTime;
    }

    public void setNdockTime(long api_ndock_time) {
        throw new UnsupportedOperationException();
    }

    public int[] getNdockItem() {
        this.ndockItem = NdockUtils.getNdockItem(getMaxHp() - getNowHp(), getShip().getShipTypeId());
        return this.ndockItem;
    }

    public void setNdockItem(int[] ndockItem) {
        throw new UnsupportedOperationException();
    }

    public int[] getKyouka() {
        return this.kyouka;
    }

    public void setKyouka(int[] kyouka) {
        this.kyouka = kyouka;
    }

    public long[] getSlotIds() {
        return slotIds;
    }

    public void setSlotIds(long[] slotIds) {
        this.slotIds = slotIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberShip that = (MemberShip) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return String.format("MemberShip [memberId=%s, memberShipId=%s, ship=%s, lv=%s]", memberId, memberShipId, getShip(), lv);
    }

    //------抽象方法------//

    @Override
    public List<? extends AbstractSlotItem> getSlotItems() {
        return getSlot();
    }

    @Override
    public int[] getCurrentEQ() {
        return Arrays.copyOf(getOnslot(), getOnslot().length);
    }

    @Override
    public int getShipSoukou() {
        return getSoukou().getMinValue();
    }

    @Override
    public int getNowLuck() {
        return getLucky().getMinValue();
    }

    @Override
    public int getNowLv() {
        return this.lv;
    }

    @Override
    public int getShipTaiSen() {
        return getTaisen().getMinValue();
    }

    @Override
    public int getShipKaihi() {
        return getShip().getKaihi().getMinValue();
    }

    @Override
    public int getShipKaryoku() {
        return getKaryoku().getMinValue();
    }

    @Override
    public int getShipRaisou() {
        return getRaisou().getMinValue();
    }

    @Override
    public int getShipSakuteki() {
        return getSakuteki().getMinValue();
    }
}
