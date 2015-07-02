/**
 * 
 */
package com.kancolle.server.service.furniture.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.furniture.FurnitureDao;
import com.kancolle.server.model.po.furniture.Furniture;
import com.kancolle.server.service.furniture.FurnitureService;

/**
 * @author J.K.SAGE
 * @Date 2015年7月2日
 *
 */
@Service
public class FurnitureServiceImpl implements FurnitureService {
    @Autowired
    private FurnitureDao furnitureDao;

    @Override
    public List<Furniture> getFurnitures() {
        return furnitureDao.selectFurnitures();
    }

}
