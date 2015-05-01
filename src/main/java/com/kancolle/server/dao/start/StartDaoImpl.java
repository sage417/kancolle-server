package com.kancolle.server.dao.start;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.model.kcsapi.start.StartModel;
import com.kancolle.server.model.kcsapi.start.sub.BgmModel;
import com.kancolle.server.model.kcsapi.start.sub.ConstModel;
import com.kancolle.server.model.kcsapi.start.sub.EquipTypeModel;
import com.kancolle.server.model.kcsapi.start.sub.FurnitureGraphModel;
import com.kancolle.server.model.kcsapi.start.sub.FurnitureModel;
import com.kancolle.server.model.kcsapi.start.sub.ItemShopModel;
import com.kancolle.server.model.kcsapi.start.sub.MapAreaModel;
import com.kancolle.server.model.kcsapi.start.sub.MapBgmModel;
import com.kancolle.server.model.kcsapi.start.sub.MapCellModel;
import com.kancolle.server.model.kcsapi.start.sub.MapInfoModel;
import com.kancolle.server.model.kcsapi.start.sub.MissionModel;
import com.kancolle.server.model.kcsapi.start.sub.PayItemModel;
import com.kancolle.server.model.kcsapi.start.sub.ShipGraphModel;
import com.kancolle.server.model.kcsapi.start.sub.ShipModel;
import com.kancolle.server.model.kcsapi.start.sub.ShipTypeModel;
import com.kancolle.server.model.kcsapi.start.sub.ShipUpgradeModel;
import com.kancolle.server.model.kcsapi.start.sub.SlotItemGraphModel;
import com.kancolle.server.model.kcsapi.start.sub.SlotItemModel;
import com.kancolle.server.model.kcsapi.start.sub.UseItemModel;

@Repository
public class StartDaoImpl extends BaseDaoImpl<StartModel> implements StartDao {
    private static final String MST_SHIP_TB = SELECT_ALL + "t_ship";
    private static final String MST_SHIPGRAPH_TB = SELECT_ALL + "t_ship_graph";
    private static final String MST_SLOTITEMEQUIPTYPE_TB = SELECT_ALL + "t_slotitem_equiptype";
    private static final String MST_SHIPTYPE_TB = SELECT_ALL + "t_ship_type";
    private static final String MST_SLOTITEM_TB = SELECT_ALL + "t_slotitem";
    private static final String MST_SLOTITEMGRAPH_TB = SELECT_ALL + "t_slotitem_graph";
    private static final String MST_FURNITURE_TB = SELECT_ALL + "t_furniture";
    private static final String MST_FURNITUREGRAPH_TB = SELECT_ALL + "t_furniture_graph";
    private static final String MST_USEITEM_TB = SELECT_ALL + "t_useitem";
    private static final String MST_PAYITEM_TB = SELECT_ALL + "t_pay_item";
    private static final String MST_ITEMSHOP_TB = "SELECT ITEM_ID FROM t_item_shop WHERE NAME = :shop_name";
    private static final String MST_MAPAREA_TB = SELECT_ALL + "t_map_area";
    private static final String MST_MAPINFO_TB = SELECT_ALL + "t_map_info";
    private static final String MST_MAPBGM_TB = SELECT_ALL + "t_map_bgm";
    private static final String MST_MAPCELL_TB = SELECT_ALL + "t_map_cell";
    private static final String MST_MISSION_TB = SELECT_ALL + "t_mission";
    private static final String MST_SHIPUPGRADE_TB = SELECT_ALL + "t_ship_upgrade";
    private static final String MST_BGM_TB = SELECT_ALL + "t_bgm";

    @Override
    public List<ShipModel> getMstShip() {
        return queryForModels(ShipModel.class, MST_SHIP_TB);
    }

    @Override
    public List<ShipGraphModel> getMstShipgraph() {
        return queryForModels(ShipGraphModel.class, MST_SHIPGRAPH_TB);
    }

    @Override
    public List<EquipTypeModel> getMstSlotitemEquiptype() {
        return queryForModels(EquipTypeModel.class, MST_SLOTITEMEQUIPTYPE_TB);
    }

    @Override
    public List<ShipTypeModel> getMstStype() {
        return queryForModels(ShipTypeModel.class, MST_SHIPTYPE_TB);
    }

    @Override
    public List<SlotItemModel> getMstSlotitem() {
        return queryForModels(SlotItemModel.class, MST_SLOTITEM_TB);
    }

    @Override
    public List<SlotItemGraphModel> getMstSlotitemgraph() {
        return queryForModels(SlotItemGraphModel.class, MST_SLOTITEMGRAPH_TB);

    }

    @Override
    public List<FurnitureModel> getMstFurniture() {
        return queryForModels(FurnitureModel.class, MST_FURNITURE_TB);

    }

    @Override
    public List<FurnitureGraphModel> getMstFurnituregraph() {
        return queryForModels(FurnitureGraphModel.class, MST_FURNITUREGRAPH_TB);
    }

    @Override
    public List<UseItemModel> getMstUseitem() {
        return queryForModels(UseItemModel.class, MST_USEITEM_TB);

    }

    @Override
    public List<PayItemModel> getMstPayitem() {
        return queryForModels(PayItemModel.class, MST_PAYITEM_TB);

    }

    @Override
    public ItemShopModel getMstItemShop() {
        ItemShopModel model = new ItemShopModel();
        model.setApi_cabinet_1(parseJSONArray(MST_ITEMSHOP_TB, Collections.singletonMap("shop_name", "api_cabinet_1")));
        model.setApi_cabinet_2(parseJSONArray(MST_ITEMSHOP_TB, Collections.singletonMap("shop_name", "api_cabinet_2")));
        return model;
    }

    @Override
    public List<MapAreaModel> getMstMaparea() {
        return queryForModels(MapAreaModel.class, MST_MAPAREA_TB);
    }

    @Override
    public List<MapInfoModel> getMstMapinfo() {
        return queryForModels(MapInfoModel.class, MST_MAPINFO_TB);
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
    public List<MissionModel> getMstMission() {
        return queryForModels(MissionModel.class, MST_MISSION_TB);
    }

    @Override
    public ConstModel getMstConst() {
        return ConstModel.getInstance();
    }

    @Override
    public List<ShipUpgradeModel> getMstShipupgrade() {
        return queryForModels(ShipUpgradeModel.class, MST_SHIPUPGRADE_TB);
    }

    @Override
    public List<BgmModel> getMstBgm() {
        return queryForModels(BgmModel.class, MST_BGM_TB);
    }
}
