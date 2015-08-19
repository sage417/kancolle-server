/**
 * 
 */
package com.kancolle.server.controller.kcsapi.form.map;

import javax.validation.constraints.Min;

/**
 * @author J.K.SAGE
 * @Date 2015年8月19日
 *
 */
public class MapCellForm {

    @Min(value = 1L)
    private int api_maparea_id;

    @Min(value = 1L)
    private int api_mapinfo_no;

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
}
