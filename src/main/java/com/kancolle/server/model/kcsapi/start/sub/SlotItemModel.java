package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.mapper.annotation.Column;

public class SlotItemModel {

    @JSONField(ordinal = 1)
    private int api_id;

    @JSONField(ordinal = 2)
    private int api_sortno;

    @JSONField(ordinal = 3)
    private String api_name;

    @JSONField(ordinal = 4)
    private JSONArray api_type;

    @JSONField(ordinal = 5)
    private int api_taik;

    @JSONField(ordinal = 6)
    private int api_souk;

    @JSONField(ordinal = 7)
    private int api_houg;

    @JSONField(ordinal = 8)
    private int api_raig;

    @JSONField(ordinal = 9)
    private int api_soku;

    @JSONField(ordinal = 10)
    private int api_baku;

    @JSONField(ordinal = 11)
    private int api_tyku;

    @JSONField(ordinal = 12)
    private int api_tais;

    @JSONField(ordinal = 13)
    private int api_atap;

    @JSONField(ordinal = 14)
    private int api_houm;

    @JSONField(ordinal = 15)
    private int api_raim;

    @JSONField(ordinal = 16)
    private int api_houk;

    @JSONField(ordinal = 17)
    private int api_raik;

    @JSONField(ordinal = 18)
    private int api_bakk;

    @JSONField(ordinal = 19)
    private int api_saku;

    @JSONField(ordinal = 20)
    private int api_sakb;

    @JSONField(ordinal = 21)
    private int api_luck;

    @JSONField(ordinal = 22)
    private int api_leng;

    @JSONField(ordinal = 23)
    private int api_rare;

    @JSONField(ordinal = 24)
    private JSONArray api_broken;

    @JSONField(ordinal = 25)
    private String api_info;

    @JSONField(ordinal = 26)
    private String api_usebull;

    public int getApi_id() {
        return api_id;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_sortno() {
        return api_sortno;
    }

    @Column(name = "SORTNO", type = int.class)
    public void setApi_sortno(int api_sortno) {
        this.api_sortno = api_sortno;
    }

    public String getApi_name() {
        return api_name;
    }

    @Column(name = "NAME", type = String.class)
    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public JSONArray getApi_type() {
        return api_type;
    }

    @Column(name = "TYPE", type = String.class)
    public void setApi_type(String api_type) {
        this.api_type = JSON.parseArray(api_type);
    }

    public int getApi_taik() {
        return api_taik;
    }

    @Column(name = "TAIK", type = int.class)
    public void setApi_taik(int api_taik) {
        this.api_taik = api_taik;
    }

    public int getApi_souk() {
        return api_souk;
    }

    @Column(name = "SOUK", type = int.class)
    public void setApi_souk(int api_souk) {
        this.api_souk = api_souk;
    }

    public int getApi_houg() {
        return api_houg;
    }

    @Column(name = "HOUG", type = int.class)
    public void setApi_houg(int api_houg) {
        this.api_houg = api_houg;
    }

    public int getApi_raig() {
        return api_raig;
    }

    @Column(name = "RAIG", type = int.class)
    public void setApi_raig(int api_raig) {
        this.api_raig = api_raig;
    }

    public int getApi_soku() {
        return api_soku;
    }

    @Column(name = "SOKU", type = int.class)
    public void setApi_soku(int api_soku) {
        this.api_soku = api_soku;
    }

    public int getApi_baku() {
        return api_baku;
    }

    @Column(name = "BAKU", type = int.class)
    public void setApi_baku(int api_baku) {
        this.api_baku = api_baku;
    }

    public int getApi_tyku() {
        return api_tyku;
    }

    @Column(name = "TYKU", type = int.class)
    public void setApi_tyku(int api_tyku) {
        this.api_tyku = api_tyku;
    }

    public int getApi_tais() {
        return api_tais;
    }

    @Column(name = "TAIS", type = int.class)
    public void setApi_tais(int api_tais) {
        this.api_tais = api_tais;
    }

    public int getApi_atap() {
        return api_atap;
    }

    @Column(name = "ATAP", type = int.class)
    public void setApi_atap(int api_atap) {
        this.api_atap = api_atap;
    }

    public int getApi_houm() {
        return api_houm;
    }

    @Column(name = "HOUM", type = int.class)
    public void setApi_houm(int api_houm) {
        this.api_houm = api_houm;
    }

    public int getApi_raim() {
        return api_raim;
    }

    @Column(name = "RAIM", type = int.class)
    public void setApi_raim(int api_raim) {
        this.api_raim = api_raim;
    }

    public int getApi_houk() {
        return api_houk;
    }

    @Column(name = "HOUK", type = int.class)
    public void setApi_houk(int api_houk) {
        this.api_houk = api_houk;
    }

    public int getApi_raik() {
        return api_raik;
    }

    @Column(name = "RAIK", type = int.class)
    public void setApi_raik(int api_raik) {
        this.api_raik = api_raik;
    }

    public int getApi_bakk() {
        return api_bakk;
    }

    @Column(name = "BAKK", type = int.class)
    public void setApi_bakk(int api_bakk) {
        this.api_bakk = api_bakk;
    }

    public int getApi_saku() {
        return api_saku;
    }

    @Column(name = "SAKU", type = int.class)
    public void setApi_saku(int api_saku) {
        this.api_saku = api_saku;
    }

    public int getApi_sakb() {
        return api_sakb;
    }

    @Column(name = "SAKB", type = int.class)
    public void setApi_sakb(int api_sakb) {
        this.api_sakb = api_sakb;
    }

    public int getApi_luck() {
        return api_luck;
    }

    @Column(name = "LUCK", type = int.class)
    public void setApi_luck(int api_luck) {
        this.api_luck = api_luck;
    }

    public int getApi_leng() {
        return api_leng;
    }

    @Column(name = "LENG", type = int.class)
    public void setApi_leng(int api_leng) {
        this.api_leng = api_leng;
    }

    public int getApi_rare() {
        return api_rare;
    }

    @Column(name = "RARE", type = int.class)
    public void setApi_rare(int api_rare) {
        this.api_rare = api_rare;
    }

    public JSONArray getApi_broken() {
        return api_broken;
    }

    @Column(name = "BROKEN", type = String.class)
    public void setApi_broken(String api_broken) {
        this.api_broken = JSON.parseArray(api_broken);
    }

    public String getApi_info() {
        return api_info;
    }

    @Column(name = "INFO", type = String.class)
    public void setApi_info(String api_info) {
        this.api_info = api_info;
    }

    public String getApi_usebull() {
        return api_usebull;
    }

    @Column(name = "USEBULL", type = String.class)
    public void setApi_usebull(String api_usebull) {
        this.api_usebull = api_usebull;
    }
}
