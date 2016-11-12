package com.kancolle.server.service.map.mapcells;

import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;

/**
 * Created by J.K.SAGE on 2016/1/18.
 */
public interface INormalMapCell extends IMapCell {

    MapNextResult getMapResult(MemberDeckPort deckPort);

    INormalMapCell nextPoint();

    UnderSeaDeckPort getUnderSeaDeckPort();
}
