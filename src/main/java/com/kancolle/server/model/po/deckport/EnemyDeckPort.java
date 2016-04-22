/**
 * 
 */
package com.kancolle.server.model.po.deckport;

import com.kancolle.server.model.po.ship.EnemyShip;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.Objects;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
@Alias("EnemyDeckPort")
public class EnemyDeckPort {

    private int id;

    private int mapCellId;

    private int no;

    private String deckName;

    private List<EnemyShip> enemyShips;

    private int formation;

    private String rank;

    private String lv;

    private int exp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMapCellId() {
        return mapCellId;
    }

    public void setMapCellId(int mapCellId) {
        this.mapCellId = mapCellId;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public List<EnemyShip> getEnemyShips() {
        return enemyShips;
    }

    public void setEnemyShips(List<EnemyShip> enemyShips) {
        this.enemyShips = enemyShips;
    }

    public int getFormation() {
        return formation;
    }

    public void setFormation(int formation) {
        this.formation = formation;
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

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnemyDeckPort that = (EnemyDeckPort) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("EnemyDeckPort [mapCellId=%s, no=%s, enemyShips=%s, formation=%s]", mapCellId, no, enemyShips, formation);
    }
}