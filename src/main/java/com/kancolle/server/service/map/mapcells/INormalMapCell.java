package com.kancolle.server.service.map.mapcells;

import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;

/**
 * Created by J.K.SAGE on 2016/1/18.
 */
public interface INormalMapCell extends IMapCell {

    INormalMapCell nextPoint(MemberDeckPort deckPort);

    UnderSeaDeckPort getUnderSeaDeckPort();
}
