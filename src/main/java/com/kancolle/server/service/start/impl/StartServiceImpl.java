package com.kancolle.server.service.start.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.start.StartDao;
import com.kancolle.server.model.kcsapi.start.StartModel;
import com.kancolle.server.service.furniture.FurnitureService;
import com.kancolle.server.service.mission.MissionService;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.service.slotitem.SlotItemService;
import com.kancolle.server.service.start.StartService;
import com.kancolle.server.utils.DaoUtils;

@Service
public class StartServiceImpl implements StartService {
    @Autowired
    private StartDao startDao;

    @Autowired
    private SlotItemService slotItemService;

    @Autowired
    private ShipService shipService;

    @Autowired
    private FurnitureService furnitureService;

    @Autowired
    private MissionService missionService;

    @Override
    @Cacheable(value = "start", key = "#root.methodName")
    public StartModel getStartModel() throws InstantiationException, IllegalAccessException {
        StartModel startModel = DaoUtils.setBean(startDao, new Class<?>[] {}, new Object[] {}, "setApi_mst_slotitem", "setApi_mst_ship", "setApi_mst_stype", "setApi_mst_furniture",
                "setApi_mst_mission");
        startModel.setApi_mst_ship(shipService.getShips());
        startModel.setApi_mst_stype(shipService.getShipTypes());
        startModel.setApi_mst_slotitem(slotItemService.getSlotItems());
        startModel.setApi_mst_furniture(furnitureService.getFurnitures());
        startModel.setApi_mst_mission(missionService.getMissions());
        return startModel;
    }
}
