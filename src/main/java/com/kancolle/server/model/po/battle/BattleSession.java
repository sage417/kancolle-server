package com.kancolle.server.model.po.battle;

/**
 * Created by J.K.SAGE on 2016/4/8 0008.
 */
public class BattleSession {

    private int[] ship_id;

    private String win_rank;

    private int mvp;

    private int enemy_deckport_id;

    public BattleSession() {
    }

    public int getEnemy_deckport_id() {
        return enemy_deckport_id;
    }

    public void setEnemy_deckport_id(int enemy_deckport_id) {
        this.enemy_deckport_id = enemy_deckport_id;
    }

    public int getMvp() {
        return mvp;
    }

    public void setMvp(int mvp) {
        this.mvp = mvp;
    }

    public int[] getShip_id() {
        return ship_id;
    }

    public void setShip_id(int[] ship_id) {
        this.ship_id = ship_id;
    }

    public String getWin_rank() {
        return win_rank;
    }

    public void setWin_rank(String win_rank) {
        this.win_rank = win_rank;
    }
}
