/**
 * 
 */
package com.kancolle.server.controller.kcsapi.battle.form;

import javax.validation.constraints.NotNull;

/**
 * @author J.K.SAGE
 * @Date 2015年8月20日
 *
 */
public class MapStartForm {

    @NotNull
    private int api_maparea_id;

    @NotNull
    private int api_mapinfo_no;

    @NotNull
    private int api_deck_id;

    @NotNull
    private int api_formation_id;

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

    public int getApi_deck_id() {
        return api_deck_id;
    }

    public void setApi_deck_id(int api_deck_id) {
        this.api_deck_id = api_deck_id;
    }

    public int getApi_formation_id() {
        return api_formation_id;
    }

    public void setApi_formation_id(int api_formation_id) {
    }
}
