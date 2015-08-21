/**
 * 
 */
package com.kancolle.server.service.map.traveller.map11.point;

import org.apache.commons.lang3.RandomUtils;

import com.kancolle.server.service.map.traveller.map.AbstractMapPoint;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
public class MapPoint2 extends AbstractMapPoint {

    private static final AbstractMapPoint NEXT_POINT1 = new MapPoint3();
    private static final AbstractMapPoint NEXT_POINT2 = new MapPoint4();

    @Override
    public AbstractMapPoint getNextMapPoint() {
        int randomInt = RandomUtils.nextInt(0, 2);
        return randomInt > 0 ? NEXT_POINT1 : NEXT_POINT2;
    }
}
