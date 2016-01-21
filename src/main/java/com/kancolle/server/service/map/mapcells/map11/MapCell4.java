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

    private static final int MAPCELL_ID = 4;

    @Override
    public EnemyDeckPort getEnemyDeckPort() {
        return null;
    }

    @Override
    public MapNextResult getMapResult() {
        return null;
    }

    @Override
    public int getMapCellId() {
        return MAPCELL_ID;
    }
}
