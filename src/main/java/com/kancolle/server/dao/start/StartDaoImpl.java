package com.kancolle.server.dao.start;

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
import com.kancolle.server.utils.DaoUtils;

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
    private static final String MST_PLAYITEM_TB = SELECT_ALL + "t_play_item";
    private static final String MST_ITEMSHOP_TB = SELECT_ALL + "t_itemshop";
    private static final String MST_MAPAREA_TB = SELECT_ALL + "t_map_area";
    private static final String MST_MAPINFO_TB = SELECT_ALL + "t_map_info";
    private static final String MST_MAPBGM_TB = SELECT_ALL + "t_map_bgm";
    private static final String MST_MAPCELL_TB = SELECT_ALL + "t_map_cell";
    private static final String MST_MISSION_TB = SELECT_ALL + "t_mission";
    private static final String MST_CONST_TB = SELECT_ALL + "t_const";
    private static final String MST_SHIPUPGRADE_TB = SELECT_ALL + "t_ship_upgrade";
    private static final String MST_BGM_TB = SELECT_ALL + "t_bgm";

    @Override
    public List<ShipModel> getMstShip() {
        return getTemplate().query(MST_SHIP_TB, (rs, rn) -> {
            ShipModel model = new ShipModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public List<ShipGraphModel> getMstShipgraph() {
        return getTemplate().query(MST_SHIPGRAPH_TB, (rs, rn) -> {
            ShipGraphModel model = new ShipGraphModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public List<EquipTypeModel> getMstSlotitemEquiptype() {
        return getTemplate().query(MST_SLOTITEMEQUIPTYPE_TB, (rs, rn) -> {
            EquipTypeModel model = new EquipTypeModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public List<ShipTypeModel> getMstStype() {
        return getTemplate().query(MST_SHIPTYPE_TB, (rs, rn) -> {
            ShipTypeModel model = new ShipTypeModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public List<SlotItemModel> getMstSlotitem() {
        return getTemplate().query(MST_SLOTITEM_TB, (rs, rn) -> {
            SlotItemModel model = new SlotItemModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public List<SlotItemGraphModel> getMstSlotitemgraph() {
        return getTemplate().query(MST_SLOTITEMGRAPH_TB, (rs, rn) -> {
            SlotItemGraphModel model = new SlotItemGraphModel();
            DaoUtils.setObject(model, rs);
            return model;
        });

    }

    @Override
    public List<FurnitureModel> getMstFurniture() {
        return getTemplate().query(MST_FURNITURE_TB, (rs, rn) -> {
            FurnitureModel model = new FurnitureModel();
            DaoUtils.setObject(model, rs);
            return model;
        });

    }

    @Override
    public List<FurnitureGraphModel> getMstFurnituregraph() {
        return getTemplate().query(MST_FURNITUREGRAPH_TB, (rs, rn) -> {
            FurnitureGraphModel model = new FurnitureGraphModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public List<UseItemModel> getMstUseitem() {
        return getTemplate().query(MST_USEITEM_TB, (rs, rn) -> {
            UseItemModel model = new UseItemModel();
            DaoUtils.setObject(model, rs);
            return model;
        });

    }

    @Override
    public List<PayItemModel> getMstPayitem() {
        return getTemplate().query(MST_PLAYITEM_TB, (rs, rn) -> {
            PayItemModel model = new PayItemModel();
            DaoUtils.setObject(model, rs);
            return model;
        });

    }

    @Override
    public ItemShopModel getMstItemShop() {
        return getTemplate().queryForObject(MST_ITEMSHOP_TB, (rs, rn) -> {
            ItemShopModel model = new ItemShopModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public List<MapAreaModel> getMstMaparea() {
        return getTemplate().query(MST_MAPAREA_TB, (rs, rn) -> {
            MapAreaModel model = new MapAreaModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public List<MapInfoModel> getMstMapinfo() {
        return getTemplate().query(MST_MAPINFO_TB, (rs, rn) -> {
            MapInfoModel model = new MapInfoModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public List<MapBgmModel> getMstMapbgm() {
        return getTemplate().query(MST_MAPBGM_TB, (rs, rn) -> {
            MapBgmModel model = new MapBgmModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public List<MapCellModel> getMstMapcell() {
        return getTemplate().query(MST_MAPCELL_TB, (rs, rn) -> {
            MapCellModel model = new MapCellModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public List<MissionModel> getMstMission() {
        return getTemplate().query(MST_MISSION_TB, (rs, rn) -> {
            MissionModel model = new MissionModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public ConstModel getMstConst() {
        return getTemplate().queryForObject(MST_CONST_TB, (rs, rn) -> {
            ConstModel model = new ConstModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public List<ShipUpgradeModel> getMstShipupgrade() {
        return getTemplate().query(MST_SHIPUPGRADE_TB, (rs, rn) -> {
            ShipUpgradeModel model = new ShipUpgradeModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }

    @Override
    public List<BgmModel> getMstBgm() {
        return getTemplate().query(MST_BGM_TB, (rs, rn) -> {
            BgmModel model = new BgmModel();
            DaoUtils.setObject(model, rs);
            return model;
        });
    }
}
