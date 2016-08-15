/**
 * 
 */
package com.kancolle.server.service.deckport;

import com.kancolle.server.mapper.deckport.UnderSeaDeckPortMapper;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
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
}
