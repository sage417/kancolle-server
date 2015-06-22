/**
 * 
 */
package com.kancolle.server.model.po.ship;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
@Alias("ShipPictureBook")
public class ShipPictureBook {

    @JSONField(ordinal = 1)
    private int api_index_no;

    @JSONField(ordinal = 2)
    private int[][] api_state;

    @JSONField(ordinal = 3)
    private int[] api_table_id;

    @JSONField(ordinal = 4)
    private String api_name;

    @JSONField(ordinal = 5)
    private String api_yomi;

    @JSONField(ordinal = 6)
    private int api_stype;

    @JSONField(ordinal = 7)
    private int api_ctype;

    @JSONField(ordinal = 8)
    private int api_cnum;

    @JSONField(ordinal = 9)
    private int api_taik;

    @JSONField(ordinal = 10)
    private int api_souk;

    @JSONField(ordinal = 11)
    private int api_kaih;

    @JSONField(ordinal = 12)
    private int api_houg;

    @JSONField(ordinal = 13)
    private int api_raig;

    @JSONField(ordinal = 14)
    private int api_tyku;

    @JSONField(ordinal = 15)
    private int api_tais;

    @JSONField(ordinal = 16)
    private int api_leng;

    @JSONField(ordinal = 17)
    private String api_sinfo;

    public int getApi_index_no() {
        return api_index_no;
    }

    public void setApi_index_no(int api_index_no) {
        this.api_index_no = api_index_no;
    }

    public int[][] getApi_state() {
        return api_state;
    }

    public void setApi_state(int[][] api_state) {
        this.api_state = api_state;
    }

    public int[] getApi_table_id() {
        return api_table_id;
    }

    public void setApi_table_id(int[] api_table_id) {
        this.api_table_id = api_table_id;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getApi_yomi() {
        return api_yomi;
    }

    public void setApi_yomi(String api_yomi) {
        this.api_yomi = api_yomi;
    }

    public int getApi_stype() {
        return api_stype;
    }

    public void setApi_stype(int api_stype) {
        this.api_stype = api_stype;
    }

    public int getApi_ctype() {
        return api_ctype;
    }

    public void setApi_ctype(int api_ctype) {
        this.api_ctype = api_ctype;
    }

    public int getApi_cnum() {
        return api_cnum;
    }

    public void setApi_cnum(int api_cnum) {
        this.api_cnum = api_cnum;
    }

    public int getApi_taik() {
        return api_taik;
    }

    public void setApi_taik(int api_taik) {
        this.api_taik = api_taik;
    }

    public int getApi_souk() {
        return api_souk;
    }

    public void setApi_souk(int api_souk) {
        this.api_souk = api_souk;
    }

    public int getApi_kaih() {
        return api_kaih;
    }

    public void setApi_kaih(int api_kaih) {
        this.api_kaih = api_kaih;
    }

    public int getApi_houg() {
        return api_houg;
    }

    public void setApi_houg(int api_houg) {
        this.api_houg = api_houg;
    }

    public int getApi_raig() {
        return api_raig;
    }

    public void setApi_raig(int api_raig) {
        this.api_raig = api_raig;
    }

    public int getApi_tyku() {
        return api_tyku;
    }

    public void setApi_tyku(int api_tyku) {
        this.api_tyku = api_tyku;
    }

    public int getApi_tais() {
        return api_tais;
    }

    public void setApi_tais(int api_tais) {
        this.api_tais = api_tais;
    }

    public int getApi_leng() {
        return api_leng;
    }

    public void setApi_leng(int api_leng) {
        this.api_leng = api_leng;
    }

    public String getApi_sinfo() {
        return api_sinfo;
    }

    public void setApi_sinfo(String api_sinfo) {
        this.api_sinfo = api_sinfo;
    }
}
