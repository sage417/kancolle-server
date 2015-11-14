/**
 * 
 */
package com.kancolle.server.model.kcsapi.battle.houku;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年11月14日
 *
 */
public class KouKuStage1 {

    @JSONField(name = "api_f_count", ordinal = 1)
    private int api_f_count;

    @JSONField(name = "api_f_lostcount", ordinal = 2)
    private int api_f_lostcount;

    @JSONField(name = "api_e_count", ordinal = 3)
    private int api_e_count;

    @JSONField(name = "api_e_lostcount", ordinal = 4)
    private int api_e_lostcount;

    /* 制空権表示　0=制空均衡, 1=制空権確保, 2=航空優勢, 3=航空劣勢, 4=制空権喪失 */
    @JSONField(name = "api_disp_seiku", ordinal = 5)
    private int api_disp_seiku;

    /* 触接装備ID　[0]=味方, [1]=敵 */
    @JSONField(name = "api_touch_plane", ordinal = 6)
    private int[] api_touch_plane;

    public int getApi_f_count() {
        return api_f_count;
    }

    public void setApi_f_count(int api_f_count) {
        this.api_f_count = api_f_count;
    }

    public int getApi_f_lostcount() {
        return api_f_lostcount;
    }

    public void setApi_f_lostcount(int api_f_lostcount) {
        this.api_f_lostcount = api_f_lostcount;
    }

    public int getApi_e_count() {
        return api_e_count;
    }

    public void setApi_e_count(int api_e_count) {
        this.api_e_count = api_e_count;
    }

    public int getApi_e_lostcount() {
        return api_e_lostcount;
    }

    public void setApi_e_lostcount(int api_e_lostcount) {
        this.api_e_lostcount = api_e_lostcount;
    }

    public int getApi_disp_seiku() {
        return api_disp_seiku;
    }

    public void setApi_disp_seiku(int api_disp_seiku) {
        this.api_disp_seiku = api_disp_seiku;
    }

    public int[] getApi_touch_plane() {
        return api_touch_plane;
    }

    public void setApi_touch_plane(int[] api_touch_plane) {
        this.api_touch_plane = api_touch_plane;
    }
}
