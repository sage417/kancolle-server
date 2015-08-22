/**
 * 
 */
package com.kancolle.server.service.map.mapcells.map11;

import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
public class MapCell3 extends AbstractMapCell {

    @Override
    public AbstractMapCell getNextMapPoint() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MapStartResult getMapCellInfo() {
        return null;
    }

    @Override
    public EnemyDeckPort getEnemyDeckPort() {
        // TODO Auto-generated method stub
        return null;
    }
}
