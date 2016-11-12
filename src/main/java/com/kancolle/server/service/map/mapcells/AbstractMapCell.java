/**
 *
 */
package com.kancolle.server.service.map.mapcells;

import com.kancolle.server.mapper.map.MapCellMapper;
import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
import com.kancolle.server.model.po.map.MapCellNext;
import com.kancolle.server.service.deckport.UnderSeaDeckPortService;
import com.kancolle.server.utils.CollectionsUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 */
public abstract class AbstractMapCell implements INormalMapCell {

    @Autowired
    private UnderSeaDeckPortService underSeaDeckPortService;

    @Autowired
    private MapCellMapper mapCellMapper;

    protected final MapNextResult getMapResult(int mapCellId, MemberDeckPort deckPort) {
        MapCellNext cell = mapCellMapper.selectMapCellNextById(mapCellId);
        MapNextResult result = new MapNextResult(cell);
        return result;
    }

    protected final UnderSeaDeckPort getUnderSeaDeckPort(int mapCellId) {
        List<UnderSeaDeckPort> deckPorts = underSeaDeckPortService.getUnderSeaDeckPorts(mapCellId);
        return CollectionsUtils.randomGet(deckPorts);
    }
}
