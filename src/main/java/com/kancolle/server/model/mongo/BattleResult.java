package com.kancolle.server.model.mongo;

import com.google.common.base.MoreObjects;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by J.K.SAGE on 2017/3/25.
 */
public class BattleResult {

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

    public String getWin_rank() {
        return win_rank;
    }

    public void setWin_rank(String win_rank) {
        this.win_rank = win_rank;
    }

    public Integer getMvp() {
        return mvp;
    }

    public void setMvp(Integer mvp) {
        this.mvp = mvp;
    }

    public Integer getEnemy_deckport_id() {
        return enemy_deckport_id;
    }

    public void setEnemy_deckport_id(Integer enemy_deckport_id) {
        this.enemy_deckport_id = enemy_deckport_id;
    }

    public Integer getDests() {
        return dests;
    }

    public void setDests(Integer dests) {
        this.dests = dests;
    }

    public Integer getDestsf() {
        return destsf;
    }

    public void setDestsf(Integer destsf) {
        this.destsf = destsf;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("win_rank", win_rank)
                .add("mvp", mvp)
                .add("enemy_deckport_id", enemy_deckport_id)
                .add("dests", dests)
                .add("destsf", destsf)
                .toString();
    }
}
