package com.kancolle.server.dao.start.impl;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.start.StartDao;
import com.kancolle.server.mapper.furniture.FurnitureGraphMapper;
import com.kancolle.server.mapper.item.PayItemMapper;
import com.kancolle.server.mapper.map.MapAreaMapper;
import com.kancolle.server.mapper.map.MapBGMMapper;
import com.kancolle.server.mapper.map.MapInfoMapper;
import com.kancolle.server.mapper.ship.ShipGraphMapper;
import com.kancolle.server.mapper.ship.ShipUpgradeMapper;
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
    private static final String MST_SLOTITEMEQUIPTYPE_TB = SELECT_ALL + "t_slotitem_equiptype";
    private static final String MST_ITEMSHOP_TB = "SELECT ITEM_ID FROM t_item_shop WHERE NAME = :shop_name";
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
        return mapBGMMapper.selectMapBgms();
    }

    @Override
    public List<MapCellModel> getMstMapcell() {
        return queryForModels(MapCellModel.class, MST_MAPCELL_TB);
    }

    @Override
    public List<MapInfoModel> getMstMapinfo() {
        return mapInfoMapper.selectMapInfos();
    }

    @Override
    public List<PayItemModel> getMstPayitem() {
        return payItemMapper.selectPayItems();
    }

    @Override
    public List<ShipGraphModel> getMstShipgraph() {
        return shipGraphMapper.selectShipGraphs();
    }

    @Override
    public List<ShipUpgradeModel> getMstShipupgrade() {
        return shipUpgradeMapper.selectShipUpgrades();
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
