/**
 * 
 */
package com.kancolle.server.mapper.deckport;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kancolle.server.model.po.deckport.EnemyDeckPort;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
public interface EnemyDeckPortMapper {

    List<EnemyDeckPort> selectEnemyDeckPorts(@Param("map_cell_id") int mapcellId);

}
