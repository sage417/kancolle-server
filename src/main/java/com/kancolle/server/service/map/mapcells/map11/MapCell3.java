/**
 *
 */
package com.kancolle.server.service.map.mapcells.map11;

import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.model.po.map.MapCell;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import org.springframework.stereotype.Component;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 */
@Component
public class MapCell3 extends AbstractMapCell {

    private static final int MAPCELL_ID = 3;

    private MapCell cell;

    @Override
    public EnemyDeckPort getEnemyDeckPort() {
        return null;
    }

    @Override
    public MapNextResult getMapResult() {
        MapNextResult result = new MapNextResult();
        this.cell = mapCellMapper.selectMapCellById(MAPCELL_ID);
        org.springframework.beans.BeanUtils.copyProperties(cell,result);
        return result;
    }

    @Override
    public int getMapCellId() {
        return MAPCELL_ID;
    }
}
