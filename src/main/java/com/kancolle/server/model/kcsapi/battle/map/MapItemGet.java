/**
 * 
 */
package com.kancolle.server.model.kcsapi.battle.map;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
public class MapItemGet {

    @JSONField(ordinal = 1)
    private int api_usemst;

    @JSONField(ordinal = 2)
    private int api_id;

    @JSONField(ordinal = 3)
    private int api_getcount;

    @JSONField(ordinal = 4)
    private String api_name;

    @JSONField(ordinal = 5)
    private int api_icon_id;

    public int getApi_usemst() {
        return api_usemst;
    }

    public void setApi_usemst(int api_usemst) {
        this.api_usemst = api_usemst;
    }

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_getcount() {
        return api_getcount;
    }

    public void setApi_getcount(int api_getcount) {
        this.api_getcount = api_getcount;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public int getApi_icon_id() {
        return api_icon_id;
    }

    public void setApi_icon_id(int api_icon_id) {
        this.api_icon_id = api_icon_id;
    }
}
