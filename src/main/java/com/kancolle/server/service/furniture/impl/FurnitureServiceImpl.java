/**
 * 
 */
package com.kancolle.server.service.furniture.impl;

import com.kancolle.server.mapper.furniture.FurnitureMapper;
import com.kancolle.server.model.po.furniture.Furniture;
import com.kancolle.server.service.furniture.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年7月2日
 *
 */
@Service
public class FurnitureServiceImpl implements FurnitureService {
    @Autowired
    private FurnitureMapper furnitureDao;

    @Override
    public List<Furniture> getFurnitures() {
        return furnitureDao.selectFurnitureByCond();
    }

    @Override
    public Furniture getFurnitureById(int furniture_id) {
        return furnitureDao.selectFurnitureById(furniture_id);
    }
}
