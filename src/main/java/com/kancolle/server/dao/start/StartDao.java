package com.kancolle.server.dao.start;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
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
import com.kancolle.server.model.kcsapi.start.sub.ShipUpgradeModel;
import com.kancolle.server.model.kcsapi.start.sub.SlotItemGraphModel;
import com.kancolle.server.model.kcsapi.start.sub.SlotItemModel;
import com.kancolle.server.model.kcsapi.start.sub.UseItemModel;
import com.kancolle.server.model.po.ship.ShipType;

public interface StartDao extends BaseDao<StartModel> {
    List<BgmModel> getMstBgm();

    ConstModel getMstConst();

    List<FurnitureModel> getMstFurniture();

    List<FurnitureGraphModel> getMstFurnituregraph();

    ItemShopModel getMstItemShop();

    List<MapAreaModel> getMstMaparea();

    List<MapBgmModel> getMstMapbgm();

    List<MapCellModel> getMstMapcell();

    List<MapInfoModel> getMstMapinfo();

    List<MissionModel> getMstMission();

    List<PayItemModel> getMstPayitem();

    List<ShipModel> getMstShip();

    List<ShipGraphModel> getMstShipgraph();

    List<ShipUpgradeModel> getMstShipupgrade();

    List<SlotItemModel> getMstSlotitem();

    List<EquipTypeModel> getMstSlotitemEquiptype();

    List<SlotItemGraphModel> getMstSlotitemgraph();

    List<ShipType> getMstStype();

    List<UseItemModel> getMstUseitem();

}
