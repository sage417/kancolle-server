/**
 * 
 */
package com.kancolle.server.mapper.deckport;

import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
public interface EnemyDeckPortMapper {

    List<EnemyDeckPort> selectEnemyDeckPorts(@Param("map_cell_id") int mapcellId);

    EnemyDeckPort selectEnemyDeckPortById(@Param("id") int enemyDeckPortId);
}
