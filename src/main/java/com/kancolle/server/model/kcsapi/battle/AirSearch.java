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
public class AirSearch {

    @JSONField(ordinal = 1)
    private int api_plane_type;

    @JSONField(ordinal = 2)
    private int api_result;

    public int getApi_plane_type() {
        return api_plane_type;
    }

    public void setApi_plane_type(int api_plane_type) {
        this.api_plane_type = api_plane_type;
    }

    public int getApi_result() {
        return api_result;
    }

    public void setApi_result(int api_result) {
        this.api_result = api_result;
    }
}
