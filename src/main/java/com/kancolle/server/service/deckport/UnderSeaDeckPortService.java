/**
 * 
 */
package com.kancolle.server.service.deckport;

import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
public interface UnderSeaDeckPortService {

    List<UnderSeaDeckPort> getUnderSeaDeckPorts(int mapCellId);

    UnderSeaDeckPort getUnderSeaDeckPortById(int underSeaDeckPortId);
}
