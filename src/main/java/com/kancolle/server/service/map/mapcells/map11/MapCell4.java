/**
 *
 */
package com.kancolle.server.service.map.mapcells.map11;

import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import org.springframework.stereotype.Component;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 */
@Component
public class MapCell4 extends AbstractMapCell {
    @Override
    public EnemyDeckPort getEnemyDeckPort() {
        return null;
    }

    @Override
    public AbstractMapCell getNextMapPoint() {
        return null;
    }

    @Override
    public MapNextResult getMapNextResult() {
        return null;
    }
}
