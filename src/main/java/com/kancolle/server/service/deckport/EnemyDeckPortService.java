/**
 * 
 */
package com.kancolle.server.service.deckport;

import java.util.List;

import com.kancolle.server.model.po.deckport.EnemyDeckPort;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
public interface EnemyDeckPortService {

    List<EnemyDeckPort> getEnemyDeckports(int mapcellId);

}
