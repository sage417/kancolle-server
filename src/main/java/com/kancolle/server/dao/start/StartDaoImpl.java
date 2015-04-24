package com.kancolle.server.dao.start;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.mapper.annotation.Column;
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
        getTemplate().query(MST_SHIP_TB, (rs, rn) -> {
            ShipModel model = new ShipModel();
            return model;
        });
        return null;
    }

    @Override
    public List<ShipGraphModel> getMstShipgraph() {
        getTemplate().query(MST_SHIPGRAPH_TB, (rs, rn) -> {
            ShipGraphModel model = new ShipGraphModel();

            return model;
        });
        return null;
    }

    @Override
    public List<EquipTypeModel> getMstSlotitemEquiptype() {
        getTemplate().query(MST_SLOTITEMEQUIPTYPE_TB, (rs, rn) -> {
            EquipTypeModel model = new EquipTypeModel();

            return model;
        });
        return null;
    }

    @Override
    public List<ShipTypeModel> getMstStype() {
        return getTemplate().query(MST_SHIPTYPE_TB, (rs, rn) -> {
            ShipTypeModel model = new ShipTypeModel();

            Arrays.asList(model.getClass().getDeclaredMethods()).stream().filter(method -> method.getName().startsWith("set") && method.getParameterCount() == 1).forEach(method -> {
                Column columm = method.getAnnotationsByType(Column.class)[0];
                String name = columm.name();
                Class<?> type = columm.type();
                try {
                    if (type == int.class) {
                        method.invoke(model, rs.getInt(name));
                        return;
                    }
                    if (type == String.class) {
                        method.invoke(model, rs.getString(name));
                        return;
                    }
                    if (type == long.class) {
                        method.invoke(model, rs.getLong(name));
                        return;
                    }
                    if (type == double.class) {
                        method.invoke(model, rs.getDouble(name));
                    }
                    if(type==JSONObject.class){
                        method.invoke(model, rs.getString(name));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return model;
        });
    }

    @Override
    public List<SlotItemModel> getMstSlotitem() {
        getTemplate().query(MST_SLOTITEM_TB, (rs, rn) -> {
            SlotItemModel model = new SlotItemModel();

            return model;
        });
        return null;
    }

    @Override
    public List<SlotItemGraphModel> getMstSlotitemgraph() {
        getTemplate().query(MST_SLOTITEMGRAPH_TB, (rs, rn) -> {
            SlotItemGraphModel model = new SlotItemGraphModel();

            return model;
        });
        return null;
    }

    @Override
    public List<FurnitureModel> getMstFurniture() {
        getTemplate().query(MST_FURNITURE_TB, (rs, rn) -> {
            FurnitureModel model = new FurnitureModel();

            return model;
        });
        return null;
    }

    @Override
    public List<FurnitureGraphModel> getMstFurnituregraph() {
        getTemplate().query(MST_FURNITUREGRAPH_TB, (rs, rn) -> {
            ShipModel model = new ShipModel();

            return model;
        });
        return null;
    }

    @Override
    public List<UseItemModel> getMstUseitem() {
        getTemplate().query(MST_USEITEM_TB, (rs, rn) -> {
            UseItemModel model = new UseItemModel();

            return model;
        });
        return null;
    }

    @Override
    public List<PayItemModel> getMstPayitem() {
        getTemplate().query(MST_PLAYITEM_TB, (rs, rn) -> {
            PayItemModel model = new PayItemModel();

            return model;
        });
        return null;
    }

    @Override
    public ItemShopModel getMstItemShop() {
        getTemplate().query(MST_ITEMSHOP_TB, (rs, rn) -> {
            ShipModel model = new ShipModel();
            return model;
        });
        return null;
    }

    @Override
    public List<MapAreaModel> getMstMaparea() {
        getTemplate().query(MST_MAPAREA_TB, (rs, rn) -> {
            MapAreaModel model = new MapAreaModel();

            return model;
        });
        return null;
    }

    @Override
    public List<MapInfoModel> getMstMapinfo() {
        getTemplate().query(MST_MAPINFO_TB, (rs, rn) -> {
            ShipModel model = new ShipModel();

            return model;
        });
        return null;
    }

    @Override
    public List<MapBgmModel> getMstMapbgm() {
        getTemplate().query(MST_MAPBGM_TB, (rs, rn) -> {
            MapBgmModel model = new MapBgmModel();

            return model;
        });
        return null;
    }

    @Override
    public List<MapCellModel> getMstMapcell() {
        getTemplate().query(MST_MAPCELL_TB, (rs, rn) -> {
            MapCellModel model = new MapCellModel();

            return model;
        });
        return null;
    }

    @Override
    public List<MissionModel> getMstMission() {
        getTemplate().query(MST_MISSION_TB, (rs, rn) -> {
            MissionModel model = new MissionModel();

            return model;
        });
        return null;
    }

    @Override
    public ConstModel getMstConst() {
        getTemplate().query(MST_CONST_TB, (rs, rn) -> {
            ConstModel model = new ConstModel();

            return model;
        });
        return null;
    }

    @Override
    public List<ShipUpgradeModel> getMstShipupgrade() {
        getTemplate().query(MST_SHIPUPGRADE_TB, (rs, rn) -> {
            ShipUpgradeModel model = new ShipUpgradeModel();

            return model;
        });
        return null;
    }

    @Override
    public List<BgmModel> getMstBgm() {
        getTemplate().query(MST_BGM_TB, (rs, rn) -> {
            BgmModel model = new BgmModel();

            return model;
        });
        return null;
    }
}
