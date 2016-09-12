package com.kancolle.server.dao.port.dto;

import com.google.common.collect.ImmutableMap;
import com.kancolle.server.dao.annotation.Column;
import com.kancolle.server.model.kcsapi.member.MemberMaterialDto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MemberMaterial {

    private static final ImmutableMap<Integer, Function<MemberMaterial, Integer>> methodMap = new ImmutableMap.Builder<Integer, Function<MemberMaterial, Integer>>()
            .put(1, MemberMaterial::getFuel)
            .put(2, MemberMaterial::getBull)
            .put(3, MemberMaterial::getSteal)
            .put(4, MemberMaterial::getBauxite)
            .put(5, MemberMaterial::getFast_build)
            .put(6, MemberMaterial::getFast_rec)
            .put(7, MemberMaterial::getDev_item)
            .put(8, MemberMaterial::getEnh_item)
            .build();


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

    @Column(name = "BAUXITE", type = int.class)
    public void setBauxite(int bauxite) {
        this.bauxite = bauxite;
    }

    @Column(name = "BULL", type = int.class)
    public void setBull(int bull) {
        this.bull = bull;
    }

    @Column(name = "DEV_ITEM", type = int.class)
    public void setDev_item(int dev_item) {
        this.dev_item = dev_item;
    }

    @Column(name = "ENH_ITEM", type = int.class)
    public void setEnh_item(int enh_item) {
        this.enh_item = enh_item;
    }

    @Column(name = "FAST_BUILD", type = int.class)
    public void setFast_build(int fast_build) {
        this.fast_build = fast_build;
    }

    @Column(name = "FAST_REC", type = int.class)
    public void setFast_rec(int fast_rec) {
        this.fast_rec = fast_rec;
    }

    @Column(name = "FUEL", type = int.class)
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    @Column(name = "STEEL", type = int.class)
    public void setSteal(int steal) {
        this.steal = steal;
    }

    public List<MemberMaterialDto> toModel() {

        final int count = methodMap.size() + 1;

        return IntStream.range(1, count).boxed().map(i -> {
            MemberMaterialDto material = new MemberMaterialDto();
            material.setApi_id(i);
            material.setApi_value(methodMap.get(i).apply(this));
            return material;
        }).collect(Collectors.toList());
    }
}
