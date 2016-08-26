/**
 * 
 */
package com.kancolle.server.dao.bgm;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.furniture.FurnitureBGM;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年7月27日
 *
 */
public interface FurnitureBGMDao extends BaseDao<FurnitureBGM> {

    List<FurnitureBGM> selectFurnitureBGMs();

    FurnitureBGM selectFurnitureBGMByCond(String music_id);

}
