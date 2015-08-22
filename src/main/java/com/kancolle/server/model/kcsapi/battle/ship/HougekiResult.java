/**
 * 
 */
package com.kancolle.server.model.kcsapi.battle.ship;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
public class HougekiResult {

    /** -1开始 */
    @JSONField(ordinal = 1)
    private int[] api_at_list;

    /*
     * -1开始
     * 特殊砲撃　
     * 0=通常, 
     * 1=レーザー攻撃, 
     * 2=連撃, 
     * 3=カットイン(主砲/副砲), 
     * 4=カットイン(主砲/電探), 
     * 5=カットイン(主砲/徹甲), 
     * 6=カットイン(主砲/主砲)
     */
    @JSONField(ordinal = 2)
    private int[] api_at_type;

    /** -1开始 */
    /*
     * 防御动作
     */
    @JSONField(ordinal = 3)
    private int[] api_df_list;

    /** -1开始 */
    /**
     * 攻击装备
     */
    @JSONField(ordinal = 4)
    private int[] api_si_list;

    /** -1开始 */
    /**
     * 命中显示
     */
    @JSONField(ordinal = 5)
    private int[] api_cl_list;

    /** -1开始 */
    /**
     * 伤害显示
     */
    @JSONField(ordinal = 6)
    private int[] api_damage;

    public int[] getApi_at_list() {
        return api_at_list;
    }

    public void setApi_at_list(int[] api_at_list) {
        this.api_at_list = api_at_list;
    }

    public int[] getApi_at_type() {
        return api_at_type;
    }

    public void setApi_at_type(int[] api_at_type) {
        this.api_at_type = api_at_type;
    }

    public int[] getApi_df_list() {
        return api_df_list;
    }

    public void setApi_df_list(int[] api_df_list) {
        this.api_df_list = api_df_list;
    }

    public int[] getApi_si_list() {
        return api_si_list;
    }

    public void setApi_si_list(int[] api_si_list) {
        this.api_si_list = api_si_list;
    }

    public int[] getApi_cl_list() {
        return api_cl_list;
    }

    public void setApi_cl_list(int[] api_cl_list) {
        this.api_cl_list = api_cl_list;
    }

    public int[] getApi_damage() {
        return api_damage;
    }

    public void setApi_damage(int[] api_damage) {
        this.api_damage = api_damage;
    }
}
