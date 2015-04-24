package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kancolle.server.mapper.annotation.Column;

public class ShipModel {

    private int api_id;

    private int api_sortno;

    private String api_name;

    private String api_yomi;

    private int api_stype;

    private int api_afterlv;

    private String api_aftershipid;

    private JSONArray api_taik;

    private JSONArray api_souk;

    private JSONArray api_houg;

    private JSONArray api_raig;

    private JSONArray api_tyku;

    private JSONArray api_luck;

    private int api_soku;

    private int api_leng;

    private int api_slot_num;

    private JSONArray api_maxeq;

    private int api_buildtime;

    private JSONArray api_broken;

    private JSONArray api_powup;

    private int api_backs;

    private String api_getmes;

    private int api_afterfuel;

    private int api_afterbull;

    private int api_fuel_max;

    private int api_bull_max;

    private int api_voicef;

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

    public String getApi_yomi() {
        return api_yomi;
    }

    @Column(name = "YOMI", type = String.class)
    public void setApi_yomi(String api_yomi) {
        this.api_yomi = api_yomi;
    }

    public int getApi_stype() {
        return api_stype;
    }

    @Column(name = "TYPE", type = int.class)
    public void setApi_stype(int api_stype) {
        this.api_stype = api_stype;
    }

    public int getApi_afterlv() {
        return api_afterlv;
    }

    @Column(name = "AFTERLV", type = int.class)
    public void setApi_afterlv(int api_afterlv) {
        this.api_afterlv = api_afterlv;
    }

    public String getApi_aftershipid() {
        return api_aftershipid;
    }

    @Column(name = "AFTERSHIPID", type = String.class)
    public void setApi_aftershipid(String api_aftershipid) {
        this.api_aftershipid = api_aftershipid;
    }

    public JSONArray getApi_taik() {
        return api_taik;
    }

    @Column(name = "TAIK", type = String.class)
    public void setApi_taik(String api_taik) {
        this.api_taik = JSON.parseArray(api_taik);
    }

    public JSONArray getApi_souk() {
        return api_souk;
    }

    @Column(name = "SOUK", type = String.class)
    public void setApi_souk(String api_souk) {
        this.api_souk = JSON.parseArray(api_souk);
    }

    public JSONArray getApi_houg() {
        return api_houg;
    }

    @Column(name = "HOUG", type = String.class)
    public void setApi_houg(String api_houg) {
        this.api_houg = JSON.parseArray(api_houg);
    }

    public JSONArray getApi_raig() {
        return api_raig;
    }

    @Column(name = "RAIG", type = String.class)
    public void setApi_raig(String api_raig) {
        this.api_raig = JSON.parseArray(api_raig);
    }

    public JSONArray getApi_tyku() {
        return api_tyku;
    }

    @Column(name = "TYKU", type = String.class)
    public void setApi_tyku(String api_tyku) {
        this.api_tyku = JSON.parseArray(api_tyku);
    }

    public JSONArray getApi_luck() {
        return api_luck;
    }

    @Column(name = "LUCK", type = String.class)
    public void setApi_luck(String api_luck) {
        this.api_luck = JSON.parseArray(api_luck);
    }

    public int getApi_soku() {
        return api_soku;
    }

    @Column(name = "SOKU", type = int.class)
    public void setApi_soku(int api_soku) {
        this.api_soku = api_soku;
    }

    public int getApi_leng() {
        return api_leng;
    }

    @Column(name = "LENG", type = int.class)
    public void setApi_leng(int api_leng) {
        this.api_leng = api_leng;
    }

    public int getApi_slot_num() {
        return api_slot_num;
    }

    @Column(name = "SLOT_NUM", type = int.class)
    public void setApi_slot_num(int api_slot_num) {
        this.api_slot_num = api_slot_num;
    }

    public JSONArray getApi_maxeq() {
        return api_maxeq;
    }

    @Column(name = "MAXEQ", type = String.class)
    public void setApi_maxeq(String api_maxeq) {
        this.api_maxeq = JSON.parseArray(api_maxeq);
    }

    public int getApi_buildtime() {
        return api_buildtime;
    }

    @Column(name = "BUILDTIME", type = int.class)
    public void setApi_buildtime(int api_buildtime) {
        this.api_buildtime = api_buildtime;
    }

    public JSONArray getApi_broken() {
        return api_broken;
    }

    @Column(name = "BROKEN", type = String.class)
    public void setApi_broken(String api_broken) {
        this.api_broken = JSON.parseArray(api_broken);
    }

    public JSONArray getApi_powup() {
        return api_powup;
    }

    @Column(name = "POWUP", type = String.class)
    public void setApi_powup(String api_powup) {
        this.api_powup = JSON.parseArray(api_powup);
    }

    public int getApi_backs() {
        return api_backs;
    }

    @Column(name = "BACKS", type = int.class)
    public void setApi_backs(int api_backs) {
        this.api_backs = api_backs;
    }

    public String getApi_getmes() {
        return api_getmes;
    }

    @Column(name = "GETMES", type = String.class)
    public void setApi_getmes(String api_getmes) {
        this.api_getmes = api_getmes;
    }

    public int getApi_afterfuel() {
        return api_afterfuel;
    }

    @Column(name = "AFTERFUEL", type = int.class)
    public void setApi_afterfuel(int api_afterfuel) {
        this.api_afterfuel = api_afterfuel;
    }

    public int getApi_afterbull() {
        return api_afterbull;
    }

    @Column(name = "AFTERBULL", type = int.class)
    public void setApi_afterbull(int api_afterbull) {
        this.api_afterbull = api_afterbull;
    }

    public int getApi_fuel_max() {
        return api_fuel_max;
    }

    @Column(name = "FUEL_MAX", type = int.class)
    public void setApi_fuel_max(int api_fuel_max) {
        this.api_fuel_max = api_fuel_max;
    }

    public int getApi_bull_max() {
        return api_bull_max;
    }

    @Column(name = "BULL_MAX", type = int.class)
    public void setApi_bull_max(int api_bull_max) {
        this.api_bull_max = api_bull_max;
    }

    public int getApi_voicef() {
        return api_voicef;
    }

    @Column(name = "VOICEF", type = int.class)
    public void setApi_voicef(int api_voicef) {
        this.api_voicef = api_voicef;
    }
}
