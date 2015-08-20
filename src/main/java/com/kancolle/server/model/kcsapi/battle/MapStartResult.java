/**
 * 
 */
package com.kancolle.server.model.kcsapi.battle;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年8月20日
 *
 */
public class MapStartResult {

    @JSONField(ordinal = 1)
    private int api_rashin_flg;

    @JSONField(ordinal = 2)
    private int api_rashin_id;

    @JSONField(ordinal = 3)
    private int api_maparea_id;

    @JSONField(ordinal = 4)
    private int api_mapinfo_no;

    @JSONField(ordinal = 5)
    private int api_no;

    @JSONField(ordinal = 6)
    private int api_color_no;

    @JSONField(ordinal = 7)
    private int api_event_id;

    @JSONField(ordinal = 8)
    private int api_event_kind;

    @JSONField(ordinal = 9)
    private int api_next;

    @JSONField(ordinal = 10)
    private int api_bosscell_no;

    @JSONField(ordinal = 11)
    private int api_bosscomp;

    @JSONField(ordinal = 12)
    private AirSearch api_airsearch;

    public int getApi_rashin_flg() {
        return api_rashin_flg;
    }

    public void setApi_rashin_flg(int api_rashin_flg) {
        this.api_rashin_flg = api_rashin_flg;
    }

    public int getApi_rashin_id() {
        return api_rashin_id;
    }

    public void setApi_rashin_id(int api_rashin_id) {
        this.api_rashin_id = api_rashin_id;
    }

    public int getApi_maparea_id() {
        return api_maparea_id;
    }

    public void setApi_maparea_id(int api_maparea_id) {
        this.api_maparea_id = api_maparea_id;
    }

    public int getApi_mapinfo_no() {
        return api_mapinfo_no;
    }

    public void setApi_mapinfo_no(int api_mapinfo_no) {
        this.api_mapinfo_no = api_mapinfo_no;
    }

    public int getApi_no() {
        return api_no;
    }

    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }

    public int getApi_color_no() {
        return api_color_no;
    }

    public void setApi_color_no(int api_color_no) {
        this.api_color_no = api_color_no;
    }

    public int getApi_event_id() {
        return api_event_id;
    }

    public void setApi_event_id(int api_event_id) {
        this.api_event_id = api_event_id;
    }

    public int getApi_event_kind() {
        return api_event_kind;
    }

    public void setApi_event_kind(int api_event_kind) {
        this.api_event_kind = api_event_kind;
    }

    public int getApi_next() {
        return api_next;
    }

    public void setApi_next(int api_next) {
        this.api_next = api_next;
    }

    public int getApi_bosscell_no() {
        return api_bosscell_no;
    }

    public void setApi_bosscell_no(int api_bosscell_no) {
        this.api_bosscell_no = api_bosscell_no;
    }

    public int getApi_bosscomp() {
        return api_bosscomp;
    }

    public void setApi_bosscomp(int api_bosscomp) {
        this.api_bosscomp = api_bosscomp;
    }

    public AirSearch getApi_airsearch() {
        return api_airsearch;
    }

    public void setApi_airsearch(AirSearch api_airsearch) {
        this.api_airsearch = api_airsearch;
    }
}
