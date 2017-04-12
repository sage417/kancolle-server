package com.kancolle.server.model.mongo;

import lombok.Data;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by J.K.SAGE on 2017/3/25.
 */
public @Data class BattleResult {

    @Property("win_rank")
    private String win_rank;

    @Property("mvp")
    private Integer mvp;

    @Property("enemy_deckport_id")
    private Integer enemy_deckport_id;

    @Property("dests")
    private Integer dests;

    @Property("destsf")
    private Integer destsf;
}
