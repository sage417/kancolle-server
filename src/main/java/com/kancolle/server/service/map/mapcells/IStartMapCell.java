package com.kancolle.server.service.map.mapcells;

import com.kancolle.server.model.po.deckport.MemberDeckPort;

/**
 * Created by J.K.SAGE on 2016/1/18.
 */
public interface IStartMapCell extends IMapCell {

    INormalMapCell nextPoint(MemberDeckPort deckPort);
}
