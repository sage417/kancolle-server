/**
 * 
 */
package com.kancolle.server.service.bgm;

import java.util.List;

import com.kancolle.server.model.kcsapi.member.FurnitureCoinResult;
import com.kancolle.server.model.po.furniture.FurnitureBGM;

/**
 * @author J.K.SAGE
 * @Date 2015年7月27日
 *
 */
public interface FurnitureBGMService {

    List<FurnitureBGM> getFurnitureBGMs();

    FurnitureBGM getFurnitureBGMByCond(String music_id);

    FurnitureCoinResult musicPlay(String member_id, String music_id);

    void setPortBGM(String member_id, int music_id);

}
