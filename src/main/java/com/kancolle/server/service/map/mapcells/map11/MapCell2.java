/**
 * 
 */
package com.kancolle.server.service.map.mapcells.map11;

import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
@Component
public class MapCell2 extends AbstractMapCell {

    private static final AbstractMapCell NEXT_POINT1 = new MapCell3();
    private static final AbstractMapCell NEXT_POINT2 = new MapCell4();

    @Override
    public AbstractMapCell getNextMapPoint() {
        int randomInt = RandomUtils.nextInt(0, 2);
        return randomInt > 0 ? NEXT_POINT1 : NEXT_POINT2;
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
