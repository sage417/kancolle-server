package com.kancolle.server.model.kcsapi.battle.enemy;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Package: com.kancolle.server.model.kcsapi.battle.enemy
 * Author: mac
 * Date: 16/4/1
 */
@JsonPropertyOrder(value = {"lv","rank","deckName"})
public class EnemyInfo {

    @JSONField(name="api_level",ordinal = 1)
    @JsonProperty(value = "api_level")
    private String lv;

    @JSONField(name="api_rank",ordinal = 2)
    @JsonProperty(value = "api_rank")
    private String rank;

    @JSONField(name="api_deck_name",ordinal = 3)
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
