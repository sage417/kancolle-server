package com.kancolle.server.dao.start.impl;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.start.StartDao;
import com.kancolle.server.mapper.furniture.FurnitureGraphMapper;
import com.kancolle.server.mapper.map.MapAreaMapper;
import com.kancolle.server.mapper.ship.ShipGraphMapper;
import com.kancolle.server.mapper.slotItem.SlotItemGraphMapper;
import com.kancolle.server.model.kcsapi.start.StartResult;
import com.kancolle.server.model.kcsapi.start.sub.*;
import com.kancolle.server.model.po.map.MapArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class StartDaoImpl extends BaseDaoImpl<StartResult> implements StartDao {
    private static final String MST_SHIPGRAPH_TB = SELECT_ALL + "t_ship_graph";
    private static final String MST_SLOTITEMEQUIPTYPE_TB = SELECT_ALL + "t_slotitem_equiptype";
    private static final String MST_SLOTITEMGRAPH_TB = SELECT_ALL + "t_slotitem_graph";
    private static final String MST_FURNITUREGRAPH_TB = SELECT_ALL + "t_furniture_graph";
    private static final String MST_PAYITEM_TB = SELECT_ALL + "t_pay_item";
    private static final String MST_ITEMSHOP_TB = "SELECT ITEM_ID FROM t_item_shop WHERE NAME = :shop_name";
    private static final String MST_MAPAREA_TB = SELECT_ALL + "t_map_area";
    private static final String MST_MAPINFO_TB = SELECT_ALL + "t_map_info";
    private static final String MST_MAPBGM_TB = SELECT_ALL + "t_map_bgm";
    private static final String MST_MAPCELL_TB = SELECT_ALL + "t_map_cell";
    private static final String MST_SHIPUPGRADE_TB = SELECT_ALL + "t_ship_upgrade";

    @Autowired
    private ShipGraphMapper shipGraphMapper;
    @Autowired
    private FurnitureGraphMapper furnitureGraphMapper;
    @Autowired
    private SlotItemGraphMapper slotItemGraphMapper;
    @Autowired
    private MapAreaMapper mapAreaMapper;

    @Override
    public ConstModel getMstConst() {
        return ConstModel.getInstance();
    }

    @Override
    public List<FurnitureGraphModel> getMstFurnituregraph() {
        return furnitureGraphMapper.selectFurnitureGraphs();
    }

    @Override
    public ItemShopModel getMstItemShop() {
        ItemShopModel model = new ItemShopModel();
        model.setApi_cabinet_1(parseJSONArray(MST_ITEMSHOP_TB, Collections.singletonMap("shop_name", "api_cabinet_1")));
        model.setApi_cabinet_2(parseJSONArray(MST_ITEMSHOP_TB, Collections.singletonMap("shop_name", "api_cabinet_2")));
        return model;
    }

    @Override
    public List<MapArea> getMstMaparea() {
        return mapAreaMapper.selectMapAreas();
    }

    @Override
    public List<MapBgmModel> getMstMapbgm() {
        return queryForModels(MapBgmModel.class, MST_MAPBGM_TB);
    }

    @Override
    public List<MapCellModel> getMstMapcell() {
        return queryForModels(MapCellModel.class, MST_MAPCELL_TB);
    }

    @Override
    public List<MapInfoModel> getMstMapinfo() {
        return queryForModels(MapInfoModel.class, MST_MAPINFO_TB);
    }

    @Override
    public List<PayItemModel> getMstPayitem() {
        return queryForModels(PayItemModel.class, MST_PAYITEM_TB);

    }

    @Override
    public List<ShipGraphModel> getMstShipgraph() {
        return shipGraphMapper.selectShipGraphs();
    }

    @Override
    public List<ShipUpgradeModel> getMstShipupgrade() {
        return queryForModels(ShipUpgradeModel.class, MST_SHIPUPGRADE_TB);
    }

    @Override
    public List<EquipTypeModel> getMstSlotitemEquiptype() {
        return queryForModels(EquipTypeModel.class, MST_SLOTITEMEQUIPTYPE_TB);
    }

    @Override
    public List<SlotItemGraphModel> getMstSlotitemgraph() {
        return slotItemGraphMapper.selectSlotItemGraphs();
    }
}
