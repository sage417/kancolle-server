package com.kancolle.server.dao.port.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import com.kancolle.server.dao.annotation.Column;
import com.kancolle.server.model.kcsapi.member.MemberMeterial;
import com.kancolle.server.utils.BeanUtils;

public class MemberMeterialDto {

    private static Map<String, Function<MemberMeterialDto, Integer>> methodMap;

    static {
        methodMap = new HashMap<String, Function<MemberMeterialDto, Integer>>();
        methodMap.put("1", MemberMeterialDto::getFuel);
        methodMap.put("2", MemberMeterialDto::getBull);
        methodMap.put("3", MemberMeterialDto::getSteal);
        methodMap.put("4", MemberMeterialDto::getBauxite);
        methodMap.put("5", MemberMeterialDto::getFast_rec);
        methodMap.put("6", MemberMeterialDto::getFast_build);
        methodMap.put("7", MemberMeterialDto::getDev_item);
        methodMap.put("8", MemberMeterialDto::getEnh_item);
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

    @Column(name = "STEAL", type = int.class)
    public void setSteal(int steal) {
        this.steal = steal;
    }

    public List<MemberMeterial> toModel() {
        long count = BeanUtils.getGetMethodStream(MemberMeterialDto.class, int.class).count();
        List<MemberMeterial> meterials = new ArrayList<>((int) count);

        Stream.iterate(1, n -> ++n).limit(count).forEach(i -> {
            MemberMeterial meterial = new MemberMeterial();
            meterial.setApi_id(i);
            meterial.setApi_value(methodMap.get(Integer.toString(i)).apply(this));
            meterials.add(meterial);
        });

        return meterials;
    }
}
