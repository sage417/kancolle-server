package com.kancolle.server.model.kcsapi.battle.map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by SAGE on 2016/11/12.
 */
public class MapEventMap {

    @JsonProperty("api_max_maphp")
    private int maxHp;

    @JsonProperty("api_max_nowhp")
    private int nowHp;

    @JsonProperty("api_max_damage")
    private int damage;

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getNowHp() {
        return nowHp;
    }

    public void setNowHp(int nowHp) {
        this.nowHp = nowHp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
