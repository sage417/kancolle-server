/**
 *
 */
package com.kancolle.server.service.map;

import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
public interface MapTraveller {

    AbstractMapCell getStartPoint();

    AbstractMapCell getNextPoint(MemberDeckPort deckPort);

    AbstractMapCell getCurrentMapCell();

    void setMapCell(int mapCellId);

}
