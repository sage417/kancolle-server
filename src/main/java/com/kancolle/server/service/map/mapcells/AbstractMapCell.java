/**
 * 
 */
package com.kancolle.server.service.map.mapcells;

import org.springframework.beans.factory.annotation.Autowired;

import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.service.deckport.EnemyDeckPortService;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
public abstract class AbstractMapCell {

    @Autowired
    protected EnemyDeckPortService enemyDeckPortService;

    public abstract AbstractMapCell getNextMapPoint();

    public abstract MapStartResult getMapCellInfo();

    public abstract EnemyDeckPort getEnemyDeckPort();
}
