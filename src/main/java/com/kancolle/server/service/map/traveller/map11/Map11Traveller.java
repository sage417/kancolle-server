/**
 * 
 */
package com.kancolle.server.service.map.traveller.map11;

import com.kancolle.server.model.kcsapi.battle.MapStartResult;
import com.kancolle.server.service.map.traveller.MapTraveller;
import com.kancolle.server.service.map.traveller.map.AbstractMapPoint;
import com.kancolle.server.service.map.traveller.map11.point.MapPoint1;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
public class Map11Traveller implements MapTraveller {

    private static final AbstractMapPoint START_POINT = new MapPoint1();

    private AbstractMapPoint mapPoint = START_POINT;

    @Override
    public MapStartResult getNext() {
        mapPoint = mapPoint.getNextMapPoint();
        return null;
    }
}
