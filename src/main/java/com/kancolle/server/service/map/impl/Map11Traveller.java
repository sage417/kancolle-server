/**
 *
 */
package com.kancolle.server.service.map.impl;

import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.service.map.mapcells.IMapCell;
import com.kancolle.server.service.map.mapcells.INormalMapCell;
import com.kancolle.server.service.map.mapcells.IStartMapCell;
import com.kancolle.server.service.map.traveller.AbstractMapTraveller;
import com.kancolle.server.service.map.traveller.MapTraveller;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 */
@Component
public class Map11Traveller extends AbstractMapTraveller implements MapTraveller {

    private final IStartMapCell cell1;

    private final INormalMapCell cell2;

    private final INormalMapCell cell3;

    private final INormalMapCell cell4;

    private IMapCell fromMapCell;

    private INormalMapCell toMapCell;

    public Map11Traveller(
            @Qualifier("mapCell1") IStartMapCell cell1,
            @Qualifier("mapCell2") INormalMapCell cell2,
            @Qualifier("mapCell3") INormalMapCell cell3,
            @Qualifier("mapCell4") INormalMapCell cell4) {
        this.cell1 = cell1;
        this.cell2 = cell2;
        this.cell3 = cell3;
        this.cell4 = cell4;
    }

    @Override
    public MapStartResult start(MemberDeckPort deckPort, int mapareaId,int  mapinfoNo) {
        this.fromMapCell = cell1;
        this.toMapCell = cell1.nextPoint(deckPort);
        return super.generateMapStartResult(deckPort, cell1, cell2,mapareaId, mapinfoNo);
    }

    @Override
    public MapNextResult next(int fromMapCellId, MemberDeckPort deckPort) {
        this.fromMapCell = getCell(fromMapCellId);
        this.toMapCell = ((INormalMapCell) this.fromMapCell).nextPoint(deckPort);
        return super.generateMapNextResult(deckPort, this.fromMapCell, this.toMapCell);
    }

    @Override
    public IMapCell getFromMapCell() {
        return fromMapCell;
    }

    @Override
    public INormalMapCell getToMapCell() {
        return toMapCell;
    }

    @Override
    public void setFromMapCell(int mapCellId) {
        this.fromMapCell = getCell(mapCellId);
    }

    private IMapCell getCell(int mapCellId) {
        switch (mapCellId) {
            case 1:
                return cell1;
            case 2:
                return cell2;
            case 3:
                return cell3;
            case 4:
                return cell4;
            default:
                throw new IllegalArgumentException("illegal cellId:" + mapCellId);
        }
    }
}
