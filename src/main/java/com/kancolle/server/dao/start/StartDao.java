package com.kancolle.server.dao.start;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.mapper.furniture.FurnitureGraphMapper;
import com.kancolle.server.mapper.item.PayItemMapper;
import com.kancolle.server.mapper.map.MapAreaMapper;
import com.kancolle.server.mapper.map.MapBGMMapper;
import com.kancolle.server.mapper.map.MapInfoMapper;
import com.kancolle.server.mapper.ship.ShipGraphMapper;
import com.kancolle.server.mapper.ship.ShipUpgradeMapper;
import com.kancolle.server.mapper.slotItem.SlotItemEquipTypeMapper;
import com.kancolle.server.mapper.slotItem.SlotItemGraphMapper;
import com.kancolle.server.model.kcsapi.start.StartResult;
import com.kancolle.server.model.kcsapi.start.sub.*;
import com.kancolle.server.model.po.furniture.BGM;
import com.kancolle.server.model.po.furniture.Furniture;
import com.kancolle.server.model.po.map.MapArea;
import com.kancolle.server.model.po.mission.Mission;
import com.kancolle.server.model.po.ship.BaseShip;
import com.kancolle.server.model.po.ship.ShipType;
import com.kancolle.server.model.po.slotitem.SlotItem;
import com.kancolle.server.model.po.useitem.UseItem;
import com.kancolle.server.service.furniture.FurnitureService;
import com.kancolle.server.service.mission.MissionService;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.service.slotitem.SlotItemService;
import com.kancolle.server.service.useitem.UseItemService;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class StartDao extends BaseDaoImpl<StartResult> {
    private static final String MST_MAPCELL_TB = SELECT_ALL + "t_map_cell";

    @Autowired
    private ShipGraphMapper shipGraphMapper;
    @Autowired
    private FurnitureGraphMapper furnitureGraphMapper;
    @Autowired
    private SlotItemGraphMapper slotItemGraphMapper;
    @Autowired
    private MapAreaMapper mapAreaMapper;
    @Autowired
    private PayItemMapper payItemMapper;
    @Autowired
    private MapBGMMapper mapBGMMapper;
    @Autowired
    private MapInfoMapper mapInfoMapper;
    @Autowired
    private ShipUpgradeMapper shipUpgradeMapper;
    @Autowired
    private SlotItemEquipTypeMapper slotItemEquipTypeMapper;
    @Autowired
    private MongoClient mongoClient;

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
    private com.kancolle.server.service.bgm.BGMService BGMService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("equip_exslot")
    private int[] api_mst_equip_exslot;

    public ConstModel getMstConst() {
        return ConstModel.getInstance();
    }

    public List<FurnitureGraphModel> getMstFurnituregraph() {
        return furnitureGraphMapper.selectFurnitureGraphs();
    }

    public ItemShopModel getMstItemShop() {
        MongoCollection<Document> shops = mongoClient.getDatabase("kancolle").getCollection("shop");
        Document shop = shops.find().first();
        return readValue(shop, ItemShopModel.class);
    }

    private <T> T readValue(final Document doc, final Class<T> type) {
        try {
            return objectMapper.readValue(doc.toJson(), type);
        } catch (IOException e) {
            return null;
        }
    }

    public List<MapArea> getMstMaparea() {
        return mapAreaMapper.selectMapAreas();
    }

    public List<MapBgmModel> getMstMapbgm() {
        return mapBGMMapper.selectMapBgms();
    }

    public List<MapCellModel> getMstMapcell() {
        return queryForModels(MapCellModel.class, MST_MAPCELL_TB);
    }

    public List<MapInfoModel> getMstMapinfo() {
        return mapInfoMapper.selectMapInfos();
    }

    public List<PayItemModel> getMstPayitem() {
        return payItemMapper.selectPayItems();
    }

    public List<ShipGraphModel> getMstShipgraph() {
        return shipGraphMapper.selectShipGraphs();
    }

    public List<ShipUpgradeModel> getMstShipupgrade() {
        return shipUpgradeMapper.selectShipUpgrades();
    }

    public List<EquipTypeModel> getMstSlotitemEquiptype() {
        return slotItemEquipTypeMapper.selectEquipTypes();
    }

    public List<SlotItemGraphModel> getMstSlotitemgraph() {
        return slotItemGraphMapper.selectSlotItemGraphs();
    }

    public int[] getMstEquipExslot() {
        return api_mst_equip_exslot;
    }

    public List<BaseShip> getMstShip() {
        return shipService.getShips();
    }

    public List<ShipType> getMstStype() {
        return shipService.getShipTypes();
    }

    public List<SlotItem> getMstSlotitem() {
        return slotItemService.getSlotItems();
    }

    public List<Furniture> getMstFurniture() {
        return furnitureService.getFurnitures();
    }

    public List<Mission> getMstMission() {
        return missionService.getMissions();
    }

    public List<UseItem> getMstUseitem() {
        return useItemService.getUseItems();
    }

    public List<BGM> getMstBgm() {
        return BGMService.getBGMs();
    }
}
