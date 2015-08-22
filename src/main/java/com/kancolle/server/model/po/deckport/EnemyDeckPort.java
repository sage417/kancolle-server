/**
 * 
 */
package com.kancolle.server.model.po.deckport;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kancolle.server.model.po.ship.EnemyShip;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
@Alias("EnemyDeckPort")
public class EnemyDeckPort {

    private int mapCellId;

    private int no;

    private List<EnemyShip> enemyShips;

    private int formation;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + mapCellId;
        result = prime * result + no;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EnemyDeckPort other = (EnemyDeckPort) obj;
        if (mapCellId != other.mapCellId)
            return false;
        if (no != other.no)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("EnemyDeckPort [mapCellId=%s, no=%s, enemyShips=%s, formation=%s]", mapCellId, no, enemyShips, formation);
    }
}