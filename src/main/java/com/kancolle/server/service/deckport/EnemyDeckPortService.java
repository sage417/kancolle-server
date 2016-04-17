/**
 * 
 */
package com.kancolle.server.service.deckport;

import com.kancolle.server.model.po.deckport.EnemyDeckPort;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
public interface EnemyDeckPortService {

    List<EnemyDeckPort> getEnemyDeckports(int mapcellId);

    EnemyDeckPort getEnemyDeckportById(int enemyDeckPortId);
}
