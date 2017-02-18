/**
 *
 */
package com.kancolle.server.service.map.mapcells;

import com.kancolle.server.mapper.map.MapCellMapper;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
import com.kancolle.server.model.po.map.MapCellModel;
import com.kancolle.server.service.deckport.UnderSeaDeckPortService;
import com.kancolle.server.utils.CollectionsUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 */
public abstract class AbstractMapCell implements INormalMapCell {
    @Autowired
    private MapCellMapper mapCellMapper;
    @Autowired
    private UnderSeaDeckPortService underSeaDeckPortService;
    private MapCellModel mapCell;

    @PostConstruct
    void init() {
        final int mapCellId = this.getMapCellId();
        this.mapCell = Objects.requireNonNull(this.mapCellMapper.selectMapCellById(mapCellId), "mapCell is null, map_cell_id=" + mapCellId);
    }

    protected final UnderSeaDeckPort getUnderSeaDeckPort(int mapCellId) {
        List<UnderSeaDeckPort> deckPorts = underSeaDeckPortService.getUnderSeaDeckPorts(mapCellId);
        return CollectionsUtils.randomGet(deckPorts);
    }

    @Override
    public MapCellModel getMapCell() {
        return mapCell;
    }
}
