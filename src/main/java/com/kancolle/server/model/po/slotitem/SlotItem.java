/**
 * 
 */
package com.kancolle.server.model.po.slotitem;

import org.apache.ibatis.type.Alias;

import com.kancolle.server.model.po.common.ResourceValue;

/**
 * @author J.K.SAGE
 * @Date 2015年5月31日
 *
 */
@Alias("SlotItem")
public class SlotItem {

    private int slotItemId;

    private int sortno;

    private String name;

    private int[] type;

    private int taik;

    private int souk;

    private int houg;

    private int raig;

    private int soku;

    private int baku;

    private int tyku;

    private int tais;

    private int atap;

    private int houm;

    private int raim;

    private int houk;

    private int raik;

    private int bakk;

    private int saku;

    private int sakb;

    private int luck;

    private int leng;

    private int rare;

    private ResourceValue broken;

    private String info;

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

    public int[] getType() {
        return type;
    }

    public void setType(int[] type) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + slotItemId;
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
        SlotItem other = (SlotItem) obj;
        if (slotItemId != other.slotItemId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("SlotItem [name=%s]", name);
    }
}
