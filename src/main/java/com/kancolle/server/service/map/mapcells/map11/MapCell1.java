/**
 * 
 */
package com.kancolle.server.service.map.mapcells.map11;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
@Component
public class MapCell1 extends AbstractMapCell {
    private static final int MAPCELL_ID = 1;

    private static final MapStartResult RESULT = new MapStartResult(1, 1, 1);

    private static final AbstractMapCell NEXT_POINT = new MapCell2();

    static {
        RESULT.setApi_rashin_flg(0);
        RESULT.setApi_rashin_id(0);
        RESULT.setApi_color_no(4);
        RESULT.setApi_event_id(4);
        RESULT.setApi_event_kind(1);
        RESULT.setApi_next(2);
        RESULT.setApi_bosscell_no(3);
        RESULT.setApi_bosscomp(1);
    }

    @Override
    public AbstractMapCell getNextMapPoint() {
        return NEXT_POINT;
    }

    @Override
    public MapStartResult getMapCellInfo() {
        return RESULT;
    }

    @Override
    public EnemyDeckPort getEnemyDeckPort() {
        List<EnemyDeckPort> enemyDeckPorts = enemyDeckPortService.getEnemyDeckports(MAPCELL_ID);
        return enemyDeckPorts.get(RandomUtils.nextInt(0, enemyDeckPorts.size()));
    }
}
