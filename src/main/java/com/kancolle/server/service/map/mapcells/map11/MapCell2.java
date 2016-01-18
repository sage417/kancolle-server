/**
 *
 */
package com.kancolle.server.service.map.mapcells.map11;

import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
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

    private final MapNextResult mapResult = new MapNextResult(1, 1, 2);

    public MapCell2() {
        mapResult.setApi_rashin_flg(1);
        mapResult.setApi_rashin_id(2);
        mapResult.setApi_color_no(4);
        mapResult.setApi_event_id(4);
        mapResult.setApi_event_kind(1);
        mapResult.setApi_next(0);
        mapResult.setApi_bosscell_no(3);
        mapResult.setApi_bosscomp(1);

        mapResult.setCommentKind(0);
        mapResult.setProductionKind(0);
    }


    @Override
    public MapNextResult getMapNextResult() {
        int randomInt = RandomUtils.nextInt(0, 2);
        AbstractMapCell nextPoint = randomInt > 0 ? NEXT_POINT1 : NEXT_POINT2;

        return mapResult;
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
