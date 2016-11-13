/**
 *
 */
package com.kancolle.server.service.map.mapcells.map11;

import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 */
@Component
public class MapCell2 extends AbstractMapCell{

    private static final int MAPCELL_ID = 2;

    @Autowired
    @Qualifier("mapCell3")
    private AbstractMapCell NEXT_POINT1;

    @Autowired
    @Qualifier("mapCell4")
    private AbstractMapCell NEXT_POINT2;

    @Override
    public AbstractMapCell nextPoint(MemberDeckPort deckPort){
        int randomInt = RandomUtils.nextInt(0, 2);
        return  randomInt > 0 ? NEXT_POINT1 : NEXT_POINT2;
    }


    @Override
    public UnderSeaDeckPort getUnderSeaDeckPort() {
        return getUnderSeaDeckPort(MAPCELL_ID);
    }

    @Override
    public int getMapCellId() {
        return MAPCELL_ID;
    }
}
