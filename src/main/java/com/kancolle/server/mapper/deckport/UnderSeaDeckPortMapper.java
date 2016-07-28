/**
 * 
 */
package com.kancolle.server.mapper.deckport;

import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
public interface UnderSeaDeckPortMapper {

    List<UnderSeaDeckPort> selectUnderSeaDeckPorts(@Param("map_cell_id") int mapcellId);

    UnderSeaDeckPort selectUnderSeaDeckPortById(@Param("id") int underSeaDeckPortId);
}
