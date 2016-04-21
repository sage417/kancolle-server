package com.kancolle.server.model.kcsapi.battle.enemy;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Package: com.kancolle.server.model.kcsapi.battle.enemy
 * Author: mac
 * Date: 16/4/1
 */
public class EnemyInfo {

    @JSONField(name="api_lv")
    @JsonProperty(value = "api_lv")
    private int lv;

    @JSONField(name="api_rank")
    @JsonProperty(value = "api_rank")
    private int rank;

    @JSONField(name="api_deck_name")
    @JsonProperty(value = "api_deck_name")
    private String deckName;

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
