/**
 *
 */
package com.kancolle.server.service.map.impl;

import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.service.map.MapTraveller;
import com.kancolle.server.service.map.mapcells.INormalMapCell;
import com.kancolle.server.service.map.mapcells.IStartMapCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 */
@Component
@Scope("prototype")
public class Map11Traveller implements MapTraveller {

    private IStartMapCell cell1;

    private INormalMapCell cell2;

    private INormalMapCell cell3;

    private INormalMapCell cell4;

    private INormalMapCell currentMapCell;

    @Override
    public MapStartResult start(MemberDeckPort deckPort) {
        setMapCell(2);
        return cell1.getMapStartResult();
    }

    @Override
    public MapNextResult next(MemberDeckPort deckPort) {
        return currentMapCell.getMapNextResult();
    }

    @Override
    public void setMapCell(int mapCellId) {
        switch (mapCellId) {
            case 2:
                setCurrentMapCell(cell2);
                break;
            case 3:
                setCurrentMapCell(cell3);
                break;
            case 4:
                setCurrentMapCell(cell4);
                break;
            default:
                throw new IllegalArgumentException("illegal mapCellId:" + mapCellId);
        }
    }


    @Autowired
    @Qualifier("mapCell1")
    public void setCell1(IStartMapCell cell1) {
        this.cell1 = cell1;
    }

    @Autowired
    @Qualifier("mapCell2")
    public void setCell2(INormalMapCell cell2) {
        this.cell2 = cell2;
    }

    @Autowired
    @Qualifier("mapCell3")
    public void setCell3(INormalMapCell cell3) {
        this.cell3 = cell3;
    }

    @Autowired
    @Qualifier("mapCell4")
    public void setCell4(INormalMapCell cell4) {
        this.cell4 = cell4;
    }

    @Override
    public INormalMapCell getCurrentMapCell() {
        return currentMapCell;
    }

    public void setCurrentMapCell(INormalMapCell currentMapCell) {
        this.currentMapCell = currentMapCell;
    }
}
