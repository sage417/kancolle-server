/**
 * 
 */
package com.kancolle.server.service.map.mapcells;

import com.kancolle.server.service.deckport.EnemyDeckPortService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
public abstract class AbstractMapCell implements INormalMapCell {

    @Autowired
    protected EnemyDeckPortService enemyDeckPortService;
}
