/**
 * 
 */
package com.kancolle.server.service.furniture;

import com.kancolle.server.model.po.furniture.Furniture;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年7月2日
 *
 */
public interface FurnitureService {

    List<Furniture> getFurnitures();

    Furniture getFurnitureById(int furniture_id);

}
