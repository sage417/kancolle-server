package com.kancolle.server.model.kcsapi.deck;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.po.deckport.PresetDeck;

import java.util.Collections;
import java.util.Map;

/**
 * Created by J.K.SAGE on 2016/8/15 0015.
 */
@JsonPropertyOrder(value = {"api_max_num", "api_deck"})
public class PresetDeckResponse {

    @JsonProperty(value = "api_max_num")
    private int api_max_num;

    @JsonProperty(value = "api_deck")
    private Map<String, PresetDeck> api_deck = Collections.emptyMap();

    public PresetDeckResponse(int api_max_num, Map<String, PresetDeck> api_deck) {
        this.api_max_num = api_max_num;
        this.api_deck = api_deck;
    }

    public int getApi_max_num() {
        return api_max_num;
    }

    public void setApi_max_num(int api_max_num) {
        this.api_max_num = api_max_num;
    }

    public Map<String, PresetDeck> getApi_deck() {
        return api_deck;
    }

    public void setApi_deck(Map<String, PresetDeck> api_deck) {
        this.api_deck = api_deck;
    }
}
