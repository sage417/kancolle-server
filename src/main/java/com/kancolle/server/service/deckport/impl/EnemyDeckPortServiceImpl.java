/**
 * 
 */
package com.kancolle.server.service.deckport.impl;

import com.kancolle.server.mapper.deckport.EnemyDeckPortMapper;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.service.deckport.EnemyDeckPortService;
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
public class EnemyDeckPortServiceImpl implements EnemyDeckPortService {

    @Autowired
    private EnemyDeckPortMapper enemyDeckPortMapper;

    @Override
    @Cacheable(value = "enemyDeckPorts", key = "#mapcellId", cacheManager = "ehcacheManager")
    public List<EnemyDeckPort> getEnemyDeckports(int mapcellId) {
        return enemyDeckPortMapper.selectEnemyDeckPorts(mapcellId);
    }
}
