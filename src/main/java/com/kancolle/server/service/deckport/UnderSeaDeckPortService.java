/**
 * 
 */
package com.kancolle.server.service.deckport;

import com.kancolle.server.mapper.deckport.UnderSeaDeckPortMapper;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
import com.kancolle.server.model.po.ship.UnderSeaShip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
@Service
public class UnderSeaDeckPortService {

    @Autowired
    private UnderSeaDeckPortMapper underSeaDeckPortMapper;

    @Cacheable(value = "enemyDeckPorts", key = "#mapCellId", cacheManager = "cacheManager")
    public List<UnderSeaDeckPort> getUnderSeaDeckPorts(int mapCellId) {
        return underSeaDeckPortMapper.selectUnderSeaDeckPorts(mapCellId);
    }

    public UnderSeaDeckPort getUnderSeaDeckPortById(int underSeaDeckPortId) {
        return underSeaDeckPortMapper.selectUnderSeaDeckPortById(underSeaDeckPortId);
    }

    /**
     * 通过舰队获取舰队内船只
     * @param underSeaDeckPort
     * @return
     */
    public List<UnderSeaShip> getUnderSeaDeckPortShips(final UnderSeaDeckPort underSeaDeckPort) {
        final int mapCellId = underSeaDeckPort.getMapCellId();
        final int no = underSeaDeckPort.getNo();
        return underSeaDeckPortMapper.selectUnderSeaDeckPortShips(mapCellId, no);
    }
}
