/**
 * 
 */
package com.kancolle.server.service.map;

import com.kancolle.server.service.map.mapcells.AbstractMapCell;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
public interface MapTraveller {

    AbstractMapCell getStartPoint();

    void reset();

    void setMapCell(int mapCellId);

}
