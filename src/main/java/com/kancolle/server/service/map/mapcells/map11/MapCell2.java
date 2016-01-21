/**
 *
 */
package com.kancolle.server.service.map.mapcells.map11;

import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.model.po.map.MapCell;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import com.kancolle.server.utils.CollectionsUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 */
@Component
public class MapCell2 extends AbstractMapCell{

    private static final int MAPCELL_ID = 2;

    @Autowired
    @Qualifier("mapCell3")
    private AbstractMapCell NEXT_POINT1;

    @Autowired
    @Qualifier("mapCell4")
    private AbstractMapCell NEXT_POINT2;

    private MapCell cell;

    @Override
    public MapNextResult getMapResult() {
        MapNextResult result = new MapNextResult();
        cell = mapCellMapper.selectMapCellById(MAPCELL_ID);
        org.springframework.beans.BeanUtils.copyProperties(cell,result);
        return result;
    }

    private AbstractMapCell nextPoint(){
        int randomInt = RandomUtils.nextInt(0, 2);
        AbstractMapCell nextPoint = randomInt > 0 ? NEXT_POINT1 : NEXT_POINT2;
        return nextPoint;
    }


    @Override
    public EnemyDeckPort getEnemyDeckPort() {
        List<EnemyDeckPort> enemyDeckPorts = enemyDeckPortService.getEnemyDeckports(MAPCELL_ID);
        return CollectionsUtils.randomGet(enemyDeckPorts);
    }

    @Override
    public int getMapCellId() {
        return MAPCELL_ID;
    }
}
