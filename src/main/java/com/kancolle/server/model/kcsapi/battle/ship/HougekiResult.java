/**
 *
 */
package com.kancolle.server.model.kcsapi.battle.ship;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.LinkedList;

import static com.google.common.collect.Lists.newLinkedList;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
@JsonPropertyOrder(value = {
        "api_at_list", "api_at_type", "api_df_list", "api_si_list",
        "api_cl_list", "api_damage"
})
public class HougekiResult {

    /** -1开始 */
    @JsonProperty(value = "api_at_list")
    @JSONField(ordinal = 1)
    private LinkedList<Integer> api_at_list;

    /*
     * -1开始 特殊砲撃 0=通常, 1=レーザー攻撃, 2=連撃, 3=カットイン(主砲/副砲), 4=カットイン(主砲/電探),
     * 5=カットイン(主砲/徹甲), 6=カットイン(主砲/主砲)
     */
    @JsonProperty(value = "api_at_type")
    @JSONField(ordinal = 2)
    private LinkedList<Integer> api_at_type;

    /** -1开始 */
    /*
     * 防御动作
     */
    @JsonProperty(value = "api_df_list")
    @JSONField(ordinal = 3)
    private LinkedList<Object> api_df_list;

    /** -1开始 */
    /**
     * 攻击装备
     */
    @JsonProperty(value = "api_si_list")
    @JSONField(ordinal = 4)
    private LinkedList<Object> api_si_list;

    /** -1开始 */
    /**
     * 命中显示
     */
    @JsonProperty(value = "api_cl_list")
    @JSONField(ordinal = 5)
    private LinkedList<Object> api_cl_list;

    /** -1开始 */
    /**
     * 伤害显示
     */
    @JsonProperty(value = "api_damage")
    @JSONField(ordinal = 6)
    private LinkedList<Object> api_damage;

    public HougekiResult() {
        this.api_at_list = newLinkedList();
        this.api_at_list.add(-1);
        this.api_at_type = newLinkedList();
        this.api_at_type.add(-1);
        this.api_df_list = newLinkedList();
        this.api_df_list.add(-1);
        this.api_si_list = newLinkedList();
        this.api_si_list.add(-1);
        this.api_cl_list = newLinkedList();
        this.api_cl_list.add(-1);
        this.api_damage = newLinkedList();
        this.api_damage.add(-1);
    }

    public LinkedList<Integer> getApi_at_list() {
        return api_at_list;
    }

    public void setApi_at_list(LinkedList<Integer> api_at_list) {
        this.api_at_list = api_at_list;
    }

    public LinkedList<Integer> getApi_at_type() {
        return api_at_type;
    }

    public void setApi_at_type(LinkedList<Integer> api_at_type) {
        this.api_at_type = api_at_type;
    }

    public LinkedList<Object> getApi_df_list() {
        return api_df_list;
    }

    public void setApi_df_list(LinkedList<Object> api_df_list) {
        this.api_df_list = api_df_list;
    }

    public LinkedList<Object> getApi_si_list() {
        return api_si_list;
    }

    public void setApi_si_list(LinkedList<Object> api_si_list) {
        this.api_si_list = api_si_list;
    }

    public LinkedList<Object> getApi_cl_list() {
        return api_cl_list;
    }

    public void setApi_cl_list(LinkedList<Object> api_cl_list) {
        this.api_cl_list = api_cl_list;
    }

    public LinkedList<Object> getApi_damage() {
        return api_damage;
    }

    public void setApi_damage(LinkedList<Object> api_damage) {
        this.api_damage = api_damage;
    }
}
