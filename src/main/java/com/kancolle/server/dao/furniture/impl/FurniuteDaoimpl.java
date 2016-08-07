/**
 * 
 */
package com.kancolle.server.dao.furniture.impl;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.furniture.FurnitureDao;
import com.kancolle.server.model.po.furniture.Furniture;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

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

    @Override
    public Furniture selectFurnitureById(int furniture_id) {
        return getSqlSession().selectOne("selectFurnitureById", Collections.singletonMap("furniture_id", furniture_id));
    }
}
