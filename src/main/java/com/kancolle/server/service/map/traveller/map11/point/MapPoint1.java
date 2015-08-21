/**
 * 
 */
package com.kancolle.server.service.map.traveller.map11.point;

import com.kancolle.server.service.map.traveller.map.AbstractMapPoint;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
public class MapPoint1 extends AbstractMapPoint {

    private static final AbstractMapPoint NEXT_POINT = new MapPoint2();

    @Override
    public AbstractMapPoint getNextMapPoint() {
        return NEXT_POINT;
    }
}
