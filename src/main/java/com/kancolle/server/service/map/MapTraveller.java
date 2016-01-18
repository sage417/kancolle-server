/**
 *
 */
package com.kancolle.server.service.map;

import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.service.map.mapcells.INormalMapCell;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
public interface MapTraveller {

    MapStartResult start(MemberDeckPort deckPort);

    MapNextResult next(MemberDeckPort deckPort);

    INormalMapCell getCurrentMapCell();

    void setMapCell(int mapCellId);
}
