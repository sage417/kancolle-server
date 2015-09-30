/**
 * 
 */
package com.kancolle.server.model.kcsapi.battle.ship;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
public class HougekiResult {

    /** -1开始 */
    @JSONField(ordinal = 1)
    private List<Long> api_at_list;

    /*
     * -1开始 特殊砲撃 0=通常, 1=レーザー攻撃, 2=連撃, 3=カットイン(主砲/副砲), 4=カットイン(主砲/電探),
     * 5=カットイン(主砲/徹甲), 6=カットイン(主砲/主砲)
     */
    @JSONField(ordinal = 2)
    private List<Integer> api_at_type;

    /** -1开始 */
    /*
     * 防御动作
     */
    @JSONField(ordinal = 3)
    private List<Object> api_df_list;

    /** -1开始 */
    /**
     * 攻击装备
     */
    @JSONField(ordinal = 4)
    private List<Object> api_si_list;

    /** -1开始 */
    /**
     * 命中显示
     */
    @JSONField(ordinal = 5)
    private List<Object> api_cl_list;

    /** -1开始 */
    /**
     * 伤害显示
     */
    @JSONField(ordinal = 6)
    private List<Object> api_damage;

    public HougekiResult() {
        this.api_at_list = newArrayList();
        this.api_at_list.add(-1L);
        this.api_at_type = newArrayList();
        this.api_at_type.add(-1);
        this.api_df_list = newArrayList();
        this.api_df_list.add(-1);
        this.api_si_list = newArrayList();
        this.api_si_list.add(-1);
        this.api_cl_list = newArrayList();
        this.api_cl_list.add(-1);
        this.api_damage = newArrayList();
        this.api_damage.add(-1);
    }

    public List<Long> getApi_at_list() {
        return api_at_list;
    }

    public void setApi_at_list(List<Long> api_at_list) {
        this.api_at_list = api_at_list;
    }

    public List<Integer> getApi_at_type() {
        return api_at_type;
    }

    public void setApi_at_type(List<Integer> api_at_type) {
        this.api_at_type = api_at_type;
    }

    public List<Object> getApi_df_list() {
        return api_df_list;
    }

    public void setApi_df_list(List<Object> api_df_list) {
        this.api_df_list = api_df_list;
    }

    public List<Object> getApi_si_list() {
        return api_si_list;
    }

    public void setApi_si_list(List<Object> api_si_list) {
        this.api_si_list = api_si_list;
    }

    public List<Object> getApi_cl_list() {
        return api_cl_list;
    }

    public void setApi_cl_list(List<Object> api_cl_list) {
        this.api_cl_list = api_cl_list;
    }

    public List<Object> getApi_damage() {
        return api_damage;
    }

    public void setApi_damage(List<Object> api_damage) {
        this.api_damage = api_damage;
    }
}
