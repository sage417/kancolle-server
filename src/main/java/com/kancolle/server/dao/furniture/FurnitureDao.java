/**
 * 
 */
package com.kancolle.server.dao.furniture;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.furniture.Furniture;

/**
 * @author J.K.SAGE
 * @Date 2015年7月2日
 *
 */
public interface FurnitureDao extends BaseDao<Furniture> {

    List<Furniture> selectFurnitures();

    Furniture selectFurnitureById(int furniture_id);

}
