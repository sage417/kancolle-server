package com.kancolle.server.dao.start;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.start.StartResult;
import com.kancolle.server.model.kcsapi.start.sub.*;

import java.util.List;

public interface StartDao extends BaseDao<StartResult> {

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
