package com.kancolle.server.service.map.traveller;

import com.kancolle.server.mapper.map.MapCellMapper;
import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.map.MapCellModel;
import com.kancolle.server.model.po.map.MapCellNext;
import com.kancolle.server.service.map.mapcells.IMapCell;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Package: com.kancolle.server.service.map.traveller
 * Author: mac
 * Date: 2016/11/13
 */
public abstract class AbstractMapTraveller implements MapTraveller {

    @Autowired
    private MapCellMapper mapCellMapper;

    protected MapStartResult generateMapStartResult(final MemberDeckPort deckPort, final IMapCell fromCell, final IMapCell toCell) {
        final int cellId = toCell.getMapCellId();
        final MapCellNext mapCellNext = mapCellMapper.selectMapCellNextById(cellId);
        fillInfo(mapCellNext);

        final MapStartResult result = new MapStartResult(mapCellNext);
        result.setNext(mapCellNext.getNo());

        return result;
    }

    protected MapNextResult generateMapNextResult(final MemberDeckPort deckPort, final IMapCell fromCell, final IMapCell toCell) {
        final int cellId = toCell.getMapCellId();
        final MapCellNext mapCellNext = mapCellMapper.selectMapCellNextById(cellId);
        fillInfo(mapCellNext);

        final MapNextResult result = new MapNextResult(mapCellNext);
        result.setNext(mapCellNext.getNo());
        return result;
    }

    private void fillInfo(final MapCellNext nextInfo) {
        final MapCellModel toMapCell = mapCellMapper.selectMapCellById(nextInfo.getCellId());
        nextInfo.setMapareaId(toMapCell.getApi_maparea_id());
        nextInfo.setMapinfoId(toMapCell.getApi_mapinfo_no());
        nextInfo.setNo(toMapCell.getApi_no());
    }
}
