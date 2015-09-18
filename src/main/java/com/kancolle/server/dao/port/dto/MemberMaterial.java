package com.kancolle.server.dao.port.dto;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kancolle.server.dao.annotation.Column;
import com.kancolle.server.model.kcsapi.member.MemberMeterialDto;
import com.kancolle.server.utils.BeanUtils;

public class MemberMaterial {

    private static Map<String, Function<MemberMaterial, Integer>> methodMap;

    static {
        methodMap = Maps.newHashMapWithExpectedSize(8);
        methodMap.put("1", MemberMaterial::getFuel);
        methodMap.put("2", MemberMaterial::getBull);
        methodMap.put("3", MemberMaterial::getSteal);
        methodMap.put("4", MemberMaterial::getBauxite);
        methodMap.put("5", MemberMaterial::getFast_build);
        methodMap.put("6", MemberMaterial::getFast_rec);
        methodMap.put("7", MemberMaterial::getDev_item);
        methodMap.put("8", MemberMaterial::getEnh_item);
        methodMap = Collections.unmodifiableMap(methodMap);
    }

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

    public List<MemberMeterialDto> toModel() {
        long count = BeanUtils.getGetMethodStream(MemberMaterial.class, int.class).count();
        List<MemberMeterialDto> meterials = Lists.newArrayListWithCapacity((int) count);

        Stream.iterate(1, i -> ++i).limit(count).forEach(i -> {
            MemberMeterialDto meterial = new MemberMeterialDto();
            meterial.setApi_id(i);
            meterial.setApi_value(methodMap.get(Integer.toString(i)).apply(this));
            meterials.add(meterial);
        });

        return meterials;
    }
}
