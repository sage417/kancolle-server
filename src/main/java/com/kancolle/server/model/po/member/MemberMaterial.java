package com.kancolle.server.model.po.member;

import org.apache.ibatis.type.Alias;

@Alias("MemberMaterial")
public class MemberMaterial {

    private long memberId;

    private int fuel;

    private int bull;

    private int steal;

    private int bauxite;

    private int fast_rec;

    private int fast_build;

    private int dev_item;

    private int enh_item;

    public int getBauxite() {
        return bauxite;
    }

    public int getBull() {
        return bull;
    }

    public int getDev_item() {
        return dev_item;
    }

    public int getEnh_item() {
        return enh_item;
    }

    public int getFast_build() {
        return fast_build;
    }

    public int getFast_rec() {
        return fast_rec;
    }

    public int getFuel() {
        return fuel;
    }

    public int getSteal() {
        return steal;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public void setBauxite(int bauxite) {
        this.bauxite = bauxite;
    }

    public void setBull(int bull) {
        this.bull = bull;
    }

    public void setDev_item(int dev_item) {
        this.dev_item = dev_item;
    }

    public void setEnh_item(int enh_item) {
        this.enh_item = enh_item;
    }

    public void setFast_build(int fast_build) {
        this.fast_build = fast_build;
    }

    public void setFast_rec(int fast_rec) {
        this.fast_rec = fast_rec;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setSteal(int steal) {
        this.steal = steal;
    }
}
