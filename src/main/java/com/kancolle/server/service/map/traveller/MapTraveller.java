/**
 *
 */
package com.kancolle.server.service.map.traveller;

import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.service.map.mapcells.IMapCell;
import com.kancolle.server.service.map.mapcells.INormalMapCell;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 */
public interface MapTraveller {

    MapStartResult start(MemberDeckPort deckPort, int mapareaId, int mapinfoNo);

    MapNextResult next(int fromMapCellId, MemberDeckPort deckPort);

    IMapCell getFromMapCell();

    INormalMapCell getToMapCell();

    void setFromMapCell(int mapCellId);
}
