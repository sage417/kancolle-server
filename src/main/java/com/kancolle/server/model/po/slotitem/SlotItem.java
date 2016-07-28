/**
 * 
 */
package com.kancolle.server.model.po.slotitem;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.ImmutableList;
import com.kancolle.server.model.po.common.ResourceValue;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author J.K.SAGE
 * @Date 2015年5月31日
 *
 */
@Alias("SlotItem")
public class SlotItem implements Serializable {

    private static final long serialVersionUID = -673912175570307175L;

    @JSONField(ordinal = 1, name = "api_id")
    private int slotItemId;

    @JSONField(ordinal = 2, name = "api_sortno")
    private int sortno;

    @JSONField(ordinal = 3, name = "api_name")
    private String name;

    @JSONField(ordinal = 4, name = "api_type")
    private ImmutableList<Integer> type;

    @JSONField(ordinal = 5, name = "api_taik")
    private int taik;

    /** 装甲 */
    @JSONField(ordinal = 6, name = "api_souk")
    private int souk;

    /** 火力 */
    @JSONField(ordinal = 7, name = "api_houg")
    private int houg;

    /** 雷装 */
    @JSONField(ordinal = 8, name = "api_raig")
    private int raig;

    @JSONField(ordinal = 9, name = "api_soku")
    private int soku;

    /** 爆装 */
    @JSONField(ordinal = 10, name = "api_baku")
    private int baku;

    /** 对空 */
    @JSONField(ordinal = 11, name = "api_tyku")
    private int tyku;

    /** 对潜 */
    @JSONField(ordinal = 12, name = "api_tais")
    private int tais;

    @JSONField(ordinal = 13, name = "api_atap")
    private int atap;

    /** 命中 */
    @JSONField(ordinal = 14, name = "api_houm")
    private int houm;

    @JSONField(ordinal = 15, name = "api_raim")
    private int raim;

    /* 回避 */
    @JSONField(ordinal = 16, name = "api_houk")
    private int houk;

    @JSONField(ordinal = 17, name = "api_raik")
    private int raik;

    @JSONField(ordinal = 18, name = "api_bakk")
    private int bakk;

    /** 索敌 */
    @JSONField(ordinal = 19, name = "api_saku")
    private int saku;

    @JSONField(ordinal = 20, name = "api_sakb")
    private int sakb;

    /** 运 */
    @JSONField(ordinal = 21, name = "api_luck")
    private int luck;

    /** 射程 */
    @JSONField(ordinal = 22, name = "api_leng")
    private int leng;

    @JSONField(ordinal = 23, name = "api_rare")
    private int rare;

    @JSONField(serialize = false, deserialize = false)
    private ResourceValue broken;

    @JSONField(ordinal = 24, name = "api_broken")
    public int[] getBrokenArray() {
        return broken.getResourceArr();
    }

    @JSONField(ordinal = 25, name = "api_info")
    private String info;

    @JSONField(ordinal = 26, name = "api_usebull")
    private String useBull;

    public int getSlotItemId() {
        return slotItemId;
    }

    public void setSlotItemId(int slotItemId) {
        this.slotItemId = slotItemId;
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

    public ImmutableList<Integer> getType() {
        return type;
    }

    public void setType(ImmutableList<Integer> type) {
        this.type = type;
    }

    public int getTaik() {
        return taik;
    }

    public void setTaik(int taik) {
        this.taik = taik;
    }

    public int getSouk() {
        return souk;
    }

    public void setSouk(int souk) {
        this.souk = souk;
    }

    public int getHoug() {
        return houg;
    }

    public void setHoug(int houg) {
        this.houg = houg;
    }

    public int getRaig() {
        return raig;
    }

    public void setRaig(int raig) {
        this.raig = raig;
    }

    public int getSoku() {
        return soku;
    }

    public void setSoku(int soku) {
        this.soku = soku;
    }

    public int getBaku() {
        return baku;
    }

    public void setBaku(int baku) {
        this.baku = baku;
    }

    public int getTyku() {
        return tyku;
    }

    public void setTyku(int tyku) {
        this.tyku = tyku;
    }

    public int getTais() {
        return tais;
    }

    public void setTais(int tais) {
        this.tais = tais;
    }

    public int getAtap() {
        return atap;
    }

    public void setAtap(int atap) {
        this.atap = atap;
    }

    public int getHoum() {
        return houm;
    }

    public void setHoum(int houm) {
        this.houm = houm;
    }

    public int getRaim() {
        return raim;
    }

    public void setRaim(int raim) {
        this.raim = raim;
    }

    public int getHouk() {
        return houk;
    }

    public void setHouk(int houk) {
        this.houk = houk;
    }

    public int getRaik() {
        return raik;
    }

    public void setRaik(int raik) {
        this.raik = raik;
    }

    public int getBakk() {
        return bakk;
    }

    public void setBakk(int bakk) {
        this.bakk = bakk;
    }

    public int getSaku() {
        return saku;
    }

    public void setSaku(int saku) {
        this.saku = saku;
    }

    public int getSakb() {
        return sakb;
    }

    public void setSakb(int sakb) {
        this.sakb = sakb;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getLeng() {
        return leng;
    }

    public void setLeng(int leng) {
        this.leng = leng;
    }

    public int getRare() {
        return rare;
    }

    public void setRare(int rare) {
        this.rare = rare;
    }

    public ResourceValue getBroken() {
        return broken;
    }

    public void setBroken(ResourceValue broken) {
        this.broken = broken;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUseBull() {
        return useBull;
    }

    public void setUseBull(String useBull) {
        this.useBull = useBull;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlotItem slotItem = (SlotItem) o;
        return slotItemId == slotItem.slotItemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotItemId);
    }

    @Override
    public String toString() {
        return String.format("SlotItem [name=%s]", name);
    }
}
