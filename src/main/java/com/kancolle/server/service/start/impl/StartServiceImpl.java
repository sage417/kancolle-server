package com.kancolle.server.service.start.impl;

import com.kancolle.server.dao.start.StartDao;
import com.kancolle.server.model.kcsapi.start.StartResult;
import com.kancolle.server.service.bgm.BGMService;
import com.kancolle.server.service.furniture.FurnitureService;
import com.kancolle.server.service.mission.MissionService;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.service.slotitem.SlotItemService;
import com.kancolle.server.service.start.StartService;
import com.kancolle.server.service.useitem.UseItemService;
import com.kancolle.server.utils.DaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private UseItemService useItemService;

    @Autowired
    private BGMService BGMService;

    @Override
    public StartResult getStartModel() throws InstantiationException, IllegalAccessException {
        StartResult startResult = DaoUtils.setBean(startDao, new Class<?>[] {}, new Object[] {}, "setApi_mst_slotitem", "setApi_mst_ship", "setApi_mst_stype", "setApi_mst_furniture",
                "setApi_mst_mission", "setApi_mst_useitem", "setApi_mst_slotitemgraph", "setApi_mst_bgm");
        startResult.setApi_mst_ship(shipService.getShips());
        startResult.setApi_mst_stype(shipService.getShipTypes());
        startResult.setApi_mst_slotitem(slotItemService.getSlotItems());
        startResult.setApi_mst_furniture(furnitureService.getFurnitures());
        startResult.setApi_mst_mission(missionService.getMissions());
        startResult.setApi_mst_useitem(useItemService.getUseItems());
        startResult.setApi_mst_bgm(BGMService.getBGMs());
        return startResult;
    }
}
