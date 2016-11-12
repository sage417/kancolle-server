package com.kancolle.server.service.map.mapcells;

import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.po.deckport.MemberDeckPort;

/**
 * Created by J.K.SAGE on 2016/1/18.
 */
public interface IStartMapCell extends IMapCell {

    MapStartResult getMapStartResult(MemberDeckPort deckPort);

}
