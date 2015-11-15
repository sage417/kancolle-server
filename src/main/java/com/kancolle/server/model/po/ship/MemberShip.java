/**
 * 
 */
package com.kancolle.server.model.po.ship;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.common.MaxMinValue;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.utils.logic.MemberShipUtils;
import com.kancolle.server.utils.logic.NdockUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 *
 */
@Alias("MemberShip")
public class MemberShip extends AbstractShip implements Serializable {

    private static final long serialVersionUID = -1844625754351796002L;

    public static final int SLOT_SIZE_MAX = 5;

    @JSONField(serialize = false, deserialize = false)
    private String memberId;

    @JSONField(ordinal = 1, name = "api_id")
    private long memberShipId;

    @JSONField(ordinal = 2, name = "api_sortno")
    public int returnSortNo() {
        return getShip().getSortno();
    }

    @JSONField(ordinal = 3, name = "api_ship_id")
    public int returnShipId() {
        return getShip().getShipId();
    }

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

    @JSONField(ordinal = 12, name = "api_backs")
    public int returnBacks() {
        return getShip().getBacks();
    }

    @JSONField(ordinal = 13, name = "api_fuel")
    private int fuel;

    @JSONField(ordinal = 14, name = "api_bull")
    private int bull;

    @JSONField(ordinal = 15, name = "api_slotnum")
    public int returnslotNum() {
        return getShip().getSoltNum();
    }

    @JSONField(ordinal = 16)
    private long api_ndock_time;

    @JSONField(ordinal = 17)
    private int[] api_ndock_item;

    @JSONField(ordinal = 18, name = "api_srate")
    public int returnSrate() {
        int grownSum = IntStream.of(MemberShipUtils.getShipPowupMaxArray(getShip())).sum();
        int grownValue = IntStream.of(getKyouka()).sum();
        return (int) (5f * grownValue / grownSum + 1);
    }

    @JSONField(ordinal = 19, name = "api_cond")
    private int cond;

    @JSONField(ordinal = 20, name = "api_karyoku")
    public int[] returnKaryoku() {
        return getKaryoku().toArray();
    }

    @JSONField(ordinal = 21, name = "api_raisou")
    public int[] returnRaisou() {
        return getRaisou().toArray();
    }

    @JSONField(ordinal = 22, name = "api_taiku")
    public int[] returnTaiku() {
        return getTaiku().toArray();
    }

    @JSONField(ordinal = 23, name = "api_soukou")
    public int[] returnSoukou() {
        return getSoukou().toArray();
    }

    @JSONField(ordinal = 24, name = "api_kaihi")
    public int[] returnKaihi() {
        return getKaihi().toArray();
    }

    @JSONField(ordinal = 25, name = "api_taisen")
    public int[] returnTaisen() {
        return getTaisen().toArray();
    }

    @JSONField(ordinal = 26, name = "api_sakuteki")
    public int[] returnSakuteki() {
        return getSakuteki().toArray();
    }

    @JSONField(ordinal = 27, name = "api_lucky")
    public int[] returnLuck() {
        return getLucky().toArray();
    }

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

    public long getApi_ndock_time() {
        this.api_ndock_time = NdockUtils.getNdockTime(getLv(), getMaxHp() - getNowHp(), getShip().getShipTypeId()) * 1000L;
        return this.api_ndock_time;
    }

    public void setApi_ndock_time(long api_ndock_time) {
        throw new UnsupportedOperationException();
    }

    public int[] getApi_ndock_item() {
        this.api_ndock_item = NdockUtils.getNdockItem(getMaxHp() - getNowHp(), getShip().getShipTypeId());
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

    public long[] getApi_slot() {
        return api_slot;
    }

    public void setApi_slot(long[] api_slot) {
        this.api_slot = api_slot;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
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
        if (memberId == null) {
            if (other.memberId != null)
                return false;
        } else if (!memberId.equals(other.memberId))
            return false;
        if (memberShipId != other.memberShipId)
            return false;
        return true;
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
    public int getNowSoukou() {
        return getSoukou().getMinValue();
    }

    @Override
    public int getNowLuck() {
        return getLucky().getMinValue();
    }

    @Override
    public int getShipTaiSen() {
        return getTaisen().getMinValue();
    }

    @Override
    public int getShipKaihi() {
        int houkValue = houkThreshold(getShip().getKaihi().getMinValue());
        if (cond < 30)
            return houkValue / 2;
        else if (cond < 40)
            return houkValue * 3 / 4;
        else if (cond < 50)
            return houkValue;
        return houkValue * 9 / 5;
    }

    @Override
    public int getShipKaryoku() {
        return getKaryoku().getMinValue();
    }

    @Override
    public int getShipSakuteki() {
        return getSakuteki().getMinValue();
    }
}
