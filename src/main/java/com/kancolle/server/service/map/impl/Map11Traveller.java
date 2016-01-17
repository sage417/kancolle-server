/**
 *
 */
package com.kancolle.server.service.map.impl;

import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.service.map.MapTraveller;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
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

    private AbstractMapCell cell1;

    private AbstractMapCell cell2;

    private AbstractMapCell cell3;

    private AbstractMapCell cell4;

    private AbstractMapCell currentMapCell = cell1;

    @Override
    public AbstractMapCell getStartPoint() {
        return cell1;
    }

    @Override
    public AbstractMapCell getNextPoint(MemberDeckPort deckPort) {
        return currentMapCell.getNextMapPoint();
    }

    @Override
    public void setMapCell(int mapCellId) {
        switch (mapCellId) {
            case 1:
                setCurrentMapCell(cell1);
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
                throw new IllegalArgumentException("illegal mapCellId:"+mapCellId);
        }
    }


    @Autowired
    @Qualifier("mapCell1")
    public void setCell1(AbstractMapCell cell1) {
        this.cell1 = cell1;
    }

    @Autowired
    @Qualifier("mapCell2")
    public void setCell2(AbstractMapCell cell2) {
        this.cell2 = cell2;
    }

    @Autowired
    @Qualifier("mapCell3")
    public void setCell3(AbstractMapCell cell3) {
        this.cell3 = cell3;
    }

    @Autowired
    @Qualifier("mapCell4")
    public void setCell4(AbstractMapCell cell4) {
        this.cell4 = cell4;
    }

    public AbstractMapCell getCurrentMapCell() {
        return currentMapCell;
    }

    public void setCurrentMapCell(AbstractMapCell currentMapCell) {
        this.currentMapCell = currentMapCell;
    }
}
