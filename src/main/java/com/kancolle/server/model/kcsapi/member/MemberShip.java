package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;

public class MemberShip {

    @JSONField(ordinal = 1)
    private int api_id;

    @JSONField(ordinal = 2)
    private int api_sortno;

    @JSONField(ordinal = 3)
    private int api_ship_id;

    @JSONField(ordinal = 4)
    private int api_lv;

    @JSONField(ordinal = 5)
    private JSONArray api_exp;

    @JSONField(ordinal = 6)
    private int api_nowhp;

    @JSONField(ordinal = 7)
    private int api_maxhp;

    @JSONField(ordinal = 8)
    private int api_leng;

    @JSONField(ordinal = 9)
    private JSONArray api_slot;

    @JSONField(ordinal = 10)
    private JSONArray api_onslot;

    @JSONField(ordinal = 11)
    private JSONArray api_kyouka;

    @JSONField(ordinal = 12)
    private int api_backs;

    @JSONField(ordinal = 13)
    private int api_fuel;

    @JSONField(ordinal = 14)
    private int api_bull;

    @JSONField(ordinal = 15)
    private int api_slotnum;

    @JSONField(ordinal = 16)
    private int api_ndock_time;

    @JSONField(ordinal = 17)
    private JSONArray api_ndock_item;

    @JSONField(ordinal = 18)
    private int api_srate;

    @JSONField(ordinal = 19)
    private int api_cond;

    @JSONField(ordinal = 20)
    private JSONArray api_karyoku;

    @JSONField(ordinal = 21)
    private JSONArray api_raisou;

    @JSONField(ordinal = 22)
    private JSONArray api_taiku;

    @JSONField(ordinal = 23)
    private JSONArray api_soukou;

    @JSONField(ordinal = 24)
    private JSONArray api_kaihi;

    @JSONField(ordinal = 25)
    private JSONArray api_taisen;

    @JSONField(ordinal = 26)
    private JSONArray api_sakuteki;

    @JSONField(ordinal = 27)
    private JSONArray api_lucky;

    @JSONField(ordinal = 28)
    private int api_locked;

    @JSONField(ordinal = 29)
    private int api_locked_equip;

    public int getApi_backs() {
        return api_backs;
    }

    public int getApi_bull() {
        return api_bull;
    }

    public int getApi_cond() {
        return api_cond;
    }

    public JSONArray getApi_exp() {
        return api_exp;
    }

    public int getApi_fuel() {
        return api_fuel;
    }

    public int getApi_id() {
        return api_id;
    }

    public JSONArray getApi_kaihi() {
        return api_kaihi;
    }

    public JSONArray getApi_karyoku() {
        return api_karyoku;
    }

    public JSONArray getApi_kyouka() {
        return api_kyouka;
    }

    public int getApi_leng() {
        return api_leng;
    }

    public int getApi_locked() {
        return api_locked;
    }

    public int getApi_locked_equip() {
        return api_locked_equip;
    }

    public JSONArray getApi_lucky() {
        return api_lucky;
    }

    public int getApi_lv() {
        return api_lv;
    }

    public int getApi_maxhp() {
        return api_maxhp;
    }

    public JSONArray getApi_ndock_item() {
        return api_ndock_item;
    }

    public int getApi_ndock_time() {
        return api_ndock_time;
    }

    public int getApi_nowhp() {
        return api_nowhp;
    }

    public JSONArray getApi_onslot() {
        return api_onslot;
    }

    public JSONArray getApi_raisou() {
        return api_raisou;
    }

    public JSONArray getApi_sakuteki() {
        return api_sakuteki;
    }

    public int getApi_ship_id() {
        return api_ship_id;
    }

    public JSONArray getApi_slot() {
        return api_slot;
    }

    public int getApi_slotnum() {
        return api_slotnum;
    }

    public int getApi_sortno() {
        return api_sortno;
    }

    public JSONArray getApi_soukou() {
        return api_soukou;
    }

    public int getApi_srate() {
        return api_srate;
    }

    public JSONArray getApi_taiku() {
        return api_taiku;
    }

    public JSONArray getApi_taisen() {
        return api_taisen;
    }

    @Column(name = "BACKS", type = int.class)
    public void setApi_backs(int api_backs) {
        this.api_backs = api_backs;
    }

    @Column(name = "BULL", type = int.class)
    public void setApi_bull(int api_bull) {
        this.api_bull = api_bull;
    }

    @Column(name = "COND", type = int.class)
    public void setApi_cond(int api_cond) {
        this.api_cond = api_cond;
    }

    @Column(name = "EXP", type = String.class)
    public void setApi_exp(String api_exp) {
        this.api_exp = JSON.parseArray(api_exp);
    }

    @Column(name = "FUEL", type = int.class)
    public void setApi_fuel(int api_fuel) {
        this.api_fuel = api_fuel;
    }

    @Column(name = "ID", type = int.class)
    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    @Column(name = "KAIHI", type = String.class)
    public void setApi_kaihi(String api_kaihi) {
        this.api_kaihi = JSON.parseArray(api_kaihi);
    }

    @Column(name = "KARYOKU", type = String.class)
    public void setApi_karyoku(String api_karyoku) {
        this.api_karyoku = JSON.parseArray(api_karyoku);
    }

    @Column(name = "KYOUKA", type = String.class)
    public void setApi_kyouka(String api_kyouka) {
        this.api_kyouka = JSON.parseArray(api_kyouka);
    }

    @Column(name = "LENG", type = int.class)
    public void setApi_leng(int api_leng) {
        this.api_leng = api_leng;
    }

    @Column(name = "LOCKED", type = int.class)
    public void setApi_locked(int api_locked) {
        this.api_locked = api_locked;
    }

    @Column(name = "LOCKED_EQUIP", type = int.class)
    public void setApi_locked_equip(int api_locked_equip) {
        this.api_locked_equip = api_locked_equip;
    }

    @Column(name = "LUCKY", type = String.class)
    public void setApi_lucky(String api_lucky) {
        this.api_lucky = JSON.parseArray(api_lucky);
    }

    @Column(name = "LV", type = int.class)
    public void setApi_lv(int api_lv) {
        this.api_lv = api_lv;
    }

    @Column(name = "MAXHP", type = int.class)
    public void setApi_maxhp(int api_maxhp) {
        this.api_maxhp = api_maxhp;
    }

    @Column(name = "NDOCK_ITEM", type = String.class)
    public void setApi_ndock_item(String api_ndock_item) {
        this.api_ndock_item = JSON.parseArray(api_ndock_item);
    }

    @Column(name = "NDOCK_TIME", type = int.class)
    public void setApi_ndock_time(int api_ndock_time) {
        this.api_ndock_time = api_ndock_time;
    }

    @Column(name = "NOWHP", type = int.class)
    public void setApi_nowhp(int api_nowhp) {
        this.api_nowhp = api_nowhp;
    }

    @Column(name = "ONSLOT", type = String.class)
    public void setApi_onslot(String api_onslot) {
        this.api_onslot = JSON.parseArray(api_onslot);
    }

    @Column(name = "RAISOU", type = String.class)
    public void setApi_raisou(String api_raisou) {
        this.api_raisou = JSON.parseArray(api_raisou);
    }

    @Column(name = "SAKUTEKI", type = String.class)
    public void setApi_sakuteki(String api_sakuteki) {
        this.api_sakuteki = JSON.parseArray(api_sakuteki);
    }

    @Column(name = "SHIP_ID", type = int.class)
    public void setApi_ship_id(int api_ship_id) {
        this.api_ship_id = api_ship_id;
    }

    @Column(name = "SLOT", type = String.class)
    public void setApi_slot(String api_slot) {
        this.api_slot = JSON.parseArray(api_slot);
    }

    @Column(name = "SLOTNUM", type = int.class)
    public void setApi_slotnum(int api_slotnum) {
        this.api_slotnum = api_slotnum;
    }

    @Column(name = "SORTNO", type = int.class)
    public void setApi_sortno(int api_sortno) {
        this.api_sortno = api_sortno;
    }

    @Column(name = "SOUKOU", type = String.class)
    public void setApi_soukou(String api_soukou) {
        this.api_soukou = JSON.parseArray(api_soukou);
    }

    @Column(name = "SRATE", type = int.class)
    public void setApi_srate(int api_srate) {
        this.api_srate = api_srate;
    }

    @Column(name = "TAIKU", type = String.class)
    public void setApi_taiku(String api_taiku) {
        this.api_taiku = JSON.parseArray(api_taiku);
    }

    @Column(name = "TAISEN", type = String.class)
    public void setApi_taisen(String api_taisen) {
        this.api_taisen = JSON.parseArray(api_taisen);
    }
}
