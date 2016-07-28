/**
 *
 */
package com.kancolle.server.service.map.mapcells.map11;

import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import com.kancolle.server.service.map.mapcells.INormalMapCell;
import org.springframework.stereotype.Component;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 */
@Component
public class MapCell3 extends AbstractMapCell {

    private static final int MAPCELL_ID = 3;

    @Override
    public UnderSeaDeckPort getUnderSeaDeckPort() {
        return getUnderSeaDeckPort(MAPCELL_ID);
    }

    @Override
    public MapNextResult getMapResult() {
        return getMapResult(MAPCELL_ID);
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
