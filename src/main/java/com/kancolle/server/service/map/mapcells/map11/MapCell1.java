/**
 *
 */
package com.kancolle.server.service.map.mapcells.map11;

import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import com.kancolle.server.service.map.mapcells.IStartMapCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 */
@Component
public class MapCell1 implements IStartMapCell {

    @Autowired
    @Qualifier("mapCell2")
    private AbstractMapCell nextPoint;

    @Override
    public AbstractMapCell nextPoint(MemberDeckPort deckPort) {
        return nextPoint;
    }

    @Override
    public int getMapCellId() {
        return 1;
    }
}
