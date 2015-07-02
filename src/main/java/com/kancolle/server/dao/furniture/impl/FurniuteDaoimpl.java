/**
 * 
 */
package com.kancolle.server.dao.furniture.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.furniture.FurnitureDao;
import com.kancolle.server.model.po.furniture.Furniture;

/**
 * @author J.K.SAGE
 * @Date 2015年7月2日
 *
 */
@Repository
public class FurniuteDaoimpl extends BaseDaoImpl<Furniture> implements FurnitureDao {

    @Override
    public List<Furniture> selectFurnitures() {
        return getSqlSession().selectList("selectFurnitureByCond");
    }
}
