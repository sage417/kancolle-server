package com.kancolle.server.service.map.mapcells;

import com.kancolle.server.model.po.deckport.EnemyDeckPort;

/**
 * Created by J.K.SAGE on 2016/1/18.
 */
public interface INormalMapCell extends IMapCell {

    EnemyDeckPort getEnemyDeckPort();
}
