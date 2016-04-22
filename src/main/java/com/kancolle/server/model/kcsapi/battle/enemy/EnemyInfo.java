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
    private String lv;

    @JSONField(name="api_rank")
    @JsonProperty(value = "api_rank")
    private String rank;

    @JSONField(name="api_deck_name")
    @JsonProperty(value = "api_deck_name")
    private String deckName;

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
