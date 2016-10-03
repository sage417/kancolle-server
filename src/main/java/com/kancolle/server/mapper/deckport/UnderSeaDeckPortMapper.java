/**
 * 
 */
package com.kancolle.server.mapper.deckport;

import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
import com.kancolle.server.model.po.ship.UnderSeaShip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
@Mapper
public interface UnderSeaDeckPortMapper {

    List<UnderSeaDeckPort> selectUnderSeaDeckPorts(@Param("map_cell_id") int mapCellId);

    UnderSeaDeckPort selectUnderSeaDeckPortById(@Param("id") int underSeaDeckPortId);

    List<UnderSeaShip> selectUnderSeaDeckPortShips(@Param("map_cell_id") int mapCellId, @Param("no") int no);
}
