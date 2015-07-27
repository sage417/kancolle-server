package com.kancolle.server.dao.start;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.start.StartModel;
import com.kancolle.server.model.kcsapi.start.sub.ConstModel;
import com.kancolle.server.model.kcsapi.start.sub.EquipTypeModel;
import com.kancolle.server.model.kcsapi.start.sub.FurnitureGraphModel;
import com.kancolle.server.model.kcsapi.start.sub.ItemShopModel;
import com.kancolle.server.model.kcsapi.start.sub.MapAreaModel;
import com.kancolle.server.model.kcsapi.start.sub.MapBgmModel;
import com.kancolle.server.model.kcsapi.start.sub.MapCellModel;
import com.kancolle.server.model.kcsapi.start.sub.MapInfoModel;
import com.kancolle.server.model.kcsapi.start.sub.PayItemModel;
import com.kancolle.server.model.kcsapi.start.sub.ShipGraphModel;
import com.kancolle.server.model.kcsapi.start.sub.ShipUpgradeModel;
import com.kancolle.server.model.kcsapi.start.sub.SlotItemGraphModel;

public interface StartDao extends BaseDao<StartModel> {

    ConstModel getMstConst();

    List<FurnitureGraphModel> getMstFurnituregraph();

    ItemShopModel getMstItemShop();

    List<MapAreaModel> getMstMaparea();

    List<MapBgmModel> getMstMapbgm();

    List<MapCellModel> getMstMapcell();

    List<MapInfoModel> getMstMapinfo();

    List<PayItemModel> getMstPayitem();

    List<ShipGraphModel> getMstShipgraph();

    List<ShipUpgradeModel> getMstShipupgrade();

    List<EquipTypeModel> getMstSlotitemEquiptype();

    List<SlotItemGraphModel> getMstSlotitemgraph();
}
