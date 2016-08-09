/**
 *
 */
package com.kancolle.server.model.po.slotitem;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import com.kancolle.server.model.po.common.ResourceValue;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author J.K.SAGE
 * @Date 2015年5月31日
 */
@Alias("SlotItem")
@JsonPropertyOrder(value = {
        "slotItemId", "sortno", "name", "type",
        "taik", "souk", "houg", "raig",
        "soku", "baku", "tyku", "tais",
        "atap", "houm", "raim", "houk",
        "raik", "bakk", "saku", "sakb",
        "luck", "leng", "rare", "api_broken",
        "info", "useBull", "cost", "distance"
})
public class SlotItem implements Serializable {

    private static final long serialVersionUID = -673912175570307175L;

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1, name = "api_id")
    private int slotItemId;

    @JsonProperty(value = "api_sortno")
    @JSONField(ordinal = 2, name = "api_sortno")
    private int sortno;

    @JsonProperty(value = "api_name")
    @JSONField(ordinal = 3, name = "api_name")
    private String name;

    @JsonProperty(value = "api_type")
    @JSONField(ordinal = 4, name = "api_type")
    private ImmutableList<Integer> type;

    @JsonIgnore
    @JSONField(serialize = false)
    private int classId;

    @JsonIgnore
    @JSONField(serialize = false)
    private int photographId;

    @JsonIgnore
    @JSONField(serialize = false)
    private int categoryId;

    @JsonIgnore
    @JSONField(serialize = false)
    private int iconId;

    @JsonProperty(value = "api_taik")
    @JSONField(ordinal = 5, name = "api_taik")
    private int taik;

    /** 装甲 */
    @JsonProperty(value = "api_souk")
    @JSONField(ordinal = 6, name = "api_souk")
    private int souk;

    /** 火力 */
    @JsonProperty(value = "api_houg")
    @JSONField(ordinal = 7, name = "api_houg")
    private int houg;

    /** 雷装 */
    @JsonProperty(value = "api_raig")
    @JSONField(ordinal = 8, name = "api_raig")
    private int raig;

    @JsonProperty(value = "api_soku")
    @JSONField(ordinal = 9, name = "api_soku")
    private int soku;

    /** 爆装 */
    @JsonProperty(value = "api_baku")
    @JSONField(ordinal = 10, name = "api_baku")
    private int baku;

    /** 对空 */
    @JsonProperty(value = "api_tyku")
    @JSONField(ordinal = 11, name = "api_tyku")
    private int tyku;

    /** 对潜 */
    @JsonProperty(value = "api_tais")
    @JSONField(ordinal = 12, name = "api_tais")
    private int tais;

    @JsonProperty(value = "api_atap")
    @JSONField(ordinal = 13, name = "api_atap")
    private int atap;

    /** 命中 */
    @JsonProperty(value = "api_houm")
    @JSONField(ordinal = 14, name = "api_houm")
    private int houm;

    @JsonProperty(value = "api_raim")
    @JSONField(ordinal = 15, name = "api_raim")
    private int raim;

    /** 回避 */
    @JsonProperty(value = "api_houk")
    @JSONField(ordinal = 16, name = "api_houk")
    private int houk;

    @JsonProperty(value = "api_raik")
    @JSONField(ordinal = 17, name = "api_raik")
    private int raik;

    @JsonProperty(value = "api_bakk")
    @JSONField(ordinal = 18, name = "api_bakk")
    private int bakk;

    /** 索敌 */
    @JsonProperty(value = "api_saku")
    @JSONField(ordinal = 19, name = "api_saku")
    private int saku;

    @JsonProperty(value = "api_sakb")
    @JSONField(ordinal = 20, name = "api_sakb")
    private int sakb;

    /** 运 */
    @JsonProperty(value = "api_luck")
    @JSONField(ordinal = 21, name = "api_luck")
    private int luck;

    /** 射程 */
    @JsonProperty(value = "api_leng")
    @JSONField(ordinal = 22, name = "api_leng")
    private int leng;

    @JsonProperty(value = "api_rare")
    @JSONField(ordinal = 23, name = "api_rare")
    private int rare;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private ResourceValue broken;

    @JsonProperty(value = "api_broken")
    @JSONField(ordinal = 24, name = "api_broken")
    public int[] getBrokenArray() {
        return broken.getResourceArr();
    }

    @JsonProperty(value = "api_info")
    @JSONField(ordinal = 25, name = "api_info")
    private String info;

    @JsonProperty(value = "api_usebull")
    @JSONField(ordinal = 26, name = "api_usebull")
    private String useBull;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonProperty("api_cost")
    Integer cost;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonProperty("api_distance")
    Integer distance;

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

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getPhotographId() {
        return photographId;
    }

    public void setPhotographId(int photographId) {
        this.photographId = photographId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
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
