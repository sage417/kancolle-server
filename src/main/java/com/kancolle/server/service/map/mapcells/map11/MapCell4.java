/**
 *
 */
package com.kancolle.server.service.map.mapcells.map11;

import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import com.kancolle.server.service.map.mapcells.INormalMapCell;
import org.springframework.stereotype.Component;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 */
@Component
public class MapCell4 extends AbstractMapCell {

    private static final int MAPCELL_ID = 4;

    @Override
    public UnderSeaDeckPort getUnderSeaDeckPort() {
        return getUnderSeaDeckPort(MAPCELL_ID);
    }

    @Override
    public MapNextResult getMapResult(MemberDeckPort deckPort) {
        return getMapResult(MAPCELL_ID, deckPort);
    }

    @Override
    public INormalMapCell nextPoint() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMapCellId() {
        return MAPCELL_ID;
    }
}
