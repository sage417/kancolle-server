/**
 * 
 */
package com.kancolle.server.service.map.impl;

import org.springframework.stereotype.Service;

import com.kancolle.server.service.map.MapTraveller;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import com.kancolle.server.service.map.mapcells.map11.MapCell1;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
@Service
public class Map11Traveller implements MapTraveller {

    private AbstractMapCell startPoint = new MapCell1();

    @Override
    public AbstractMapCell getNextPoint(int mapCellNo) {
        return null;
    }

    @Override
    public AbstractMapCell getStartPoint() {
        return startPoint;
    }
}
