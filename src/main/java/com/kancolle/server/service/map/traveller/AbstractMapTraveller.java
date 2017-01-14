package com.kancolle.server.service.map.traveller;

import com.kancolle.server.mapper.map.MapCellMapper;
import com.kancolle.server.mapper.map.MemberMapCellMapper;
import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.kcsapi.battle.map.MemberMapCellView;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.map.MapCellModel;
import com.kancolle.server.model.po.map.MapCellNext;
import com.kancolle.server.model.po.map.MemberMapCell;
import com.kancolle.server.service.map.mapcells.IMapCell;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Package: com.kancolle.server.service.map.traveller
 * Author: mac
 * Date: 2016/11/13
 */
public abstract class AbstractMapTraveller implements MapTraveller {

    @Autowired
    private MapCellMapper mapCellMapper;
    @Autowired
    private MemberMapCellMapper memberMapCellMapper;

    protected MapStartResult generateMapStartResult(final MemberDeckPort deckPort, final IMapCell fromCell, final IMapCell toCell, int mapareaId, int mapinfoNo) {
        final int cellId = toCell.getMapCellId();
        final MapCellNext mapCellNext = mapCellMapper.selectMapCellNextById(cellId);
        fillInfo(mapCellNext);

        final MapStartResult result = new MapStartResult(mapCellNext);

        final List<MemberMapCell> memberMapCells = memberMapCellMapper.selectMemberMapCellInfos(deckPort.getMemberId(), mapareaId, mapinfoNo);
        result.setCell_data(memberMapCells.stream().map(c -> {
            final MemberMapCellView view = new MemberMapCellView();
            BeanUtils.copyProperties(c, view);
            final MapCellModel mapCell = mapCellMapper.selectMapCellById(view.getMapCellId());
            BeanUtils.copyProperties(mapCell, view);
            return view;
        }).collect(Collectors.toList()));
        result.setNext(mapCellNext.getNo());

        return result;
    }

    protected MapNextResult generateMapNextResult(final MemberDeckPort deckPort, final IMapCell fromCell, final IMapCell toCell) {
        final int cellId = toCell.getMapCellId();
        final MapCellNext mapCellNext = mapCellMapper.selectMapCellNextById(cellId);
        fillInfo(mapCellNext);

        return new MapNextResult(mapCellNext);
    }

    private void fillInfo(final MapCellNext nextInfo) {
        final MapCellModel toMapCell = mapCellMapper.selectMapCellById(nextInfo.getCellId());
        nextInfo.setMapareaId(toMapCell.getApi_maparea_id());
        nextInfo.setMapinfoId(toMapCell.getApi_mapinfo_no());
        nextInfo.setNo(toMapCell.getApi_no());
    }
}
