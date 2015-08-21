/**
 * 
 */
package com.kancolle.server.service.map.traveller;

import com.kancolle.server.model.kcsapi.battle.MapStartResult;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
public interface MapTraveller {

    MapStartResult getNext();

}
