package com.kancolle.server.model.kcsapi.start;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.kcsapi.start.sub.BgmModel;
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
import com.kancolle.server.model.po.furniture.Furniture;
import com.kancolle.server.model.po.mission.Mission;
import com.kancolle.server.model.po.ship.BaseShip;
import com.kancolle.server.model.po.ship.ShipType;
import com.kancolle.server.model.po.slotitem.SlotItem;
import com.kancolle.server.model.po.useitem.UseItem;

public class StartModel {
    @JSONField(ordinal = 1)
    private List<BaseShip> api_mst_ship;
    @JSONField(ordinal = 2)
    private List<ShipGraphModel> api_mst_shipgraph;
    @JSONField(ordinal = 3)
    private List<EquipTypeModel> api_mst_slotitem_equiptype;
    @JSONField(ordinal = 4)
    private List<ShipType> api_mst_stype;
    @JSONField(ordinal = 5)
    private List<SlotItem> api_mst_slotitem;
    @JSONField(ordinal = 6)
    private List<SlotItemGraphModel> api_mst_slotitemgraph;
    @JSONField(ordinal = 7)
    private List<Furniture> api_mst_furniture;
    @JSONField(ordinal = 8)
    private List<FurnitureGraphModel> api_mst_furnituregraph;
    @JSONField(ordinal = 9)
    private List<UseItem> api_mst_useitem;
    @JSONField(ordinal = 10)
    private List<PayItemModel> api_mst_payitem;
    @JSONField(ordinal = 11)
    private ItemShopModel api_mst_item_shop;
    @JSONField(ordinal = 12)
    private List<MapAreaModel> api_mst_maparea;
    @JSONField(ordinal = 13)
    private List<MapInfoModel> api_mst_mapinfo;
    @JSONField(ordinal = 14)
    private List<MapBgmModel> api_mst_mapbgm;
    @JSONField(ordinal = 15)
    private List<MapCellModel> api_mst_mapcell;
    @JSONField(ordinal = 16)
    private List<Mission> api_mst_mission;
    @JSONField(ordinal = 17)
    private ConstModel api_mst_const;
    @JSONField(ordinal = 18)
    private List<ShipUpgradeModel> api_mst_shipupgrade;
    @JSONField(ordinal = 19)
    private List<BgmModel> api_mst_bgm;

    public List<BgmModel> getApi_mst_bgm() {
        return api_mst_bgm;
    }

    public ConstModel getApi_mst_const() {
        return api_mst_const;
    }

    public List<Furniture> getApi_mst_furniture() {
        return api_mst_furniture;
    }

    public List<FurnitureGraphModel> getApi_mst_furnituregraph() {
        return api_mst_furnituregraph;
    }

    public ItemShopModel getApi_mst_item_shop() {
        return api_mst_item_shop;
    }

    public List<MapAreaModel> getApi_mst_maparea() {
        return api_mst_maparea;
    }

    public List<MapBgmModel> getApi_mst_mapbgm() {
        return api_mst_mapbgm;
    }

    public List<MapCellModel> getApi_mst_mapcell() {
        return api_mst_mapcell;
    }

    public List<MapInfoModel> getApi_mst_mapinfo() {
        return api_mst_mapinfo;
    }

    public List<Mission> getApi_mst_mission() {
        return api_mst_mission;
    }

    public List<PayItemModel> getApi_mst_payitem() {
        return api_mst_payitem;
    }

    public List<BaseShip> getApi_mst_ship() {
        return api_mst_ship;
    }

    public List<ShipGraphModel> getApi_mst_shipgraph() {
        return api_mst_shipgraph;
    }

    public List<ShipUpgradeModel> getApi_mst_shipupgrade() {
        return api_mst_shipupgrade;
    }

    public List<SlotItem> getApi_mst_slotitem() {
        return api_mst_slotitem;
    }

    public List<EquipTypeModel> getApi_mst_slotitem_equiptype() {
        return api_mst_slotitem_equiptype;
    }

    public List<SlotItemGraphModel> getApi_mst_slotitemgraph() {
        return api_mst_slotitemgraph;
    }

    public List<ShipType> getApi_mst_stype() {
        return api_mst_stype;
    }

    public List<UseItem> getApi_mst_useitem() {
        return api_mst_useitem;
    }

    public void setApi_mst_bgm(List<BgmModel> api_mst_bgm) {
        this.api_mst_bgm = api_mst_bgm;
    }

    public void setApi_mst_const(ConstModel api_mst_const) {
        this.api_mst_const = api_mst_const;
    }

    public void setApi_mst_furniture(List<Furniture> api_mst_furniture) {
        this.api_mst_furniture = api_mst_furniture;
    }

    public void setApi_mst_furnituregraph(List<FurnitureGraphModel> api_mst_furnituregraph) {
        this.api_mst_furnituregraph = api_mst_furnituregraph;
    }

    public void setApi_mst_item_shop(ItemShopModel api_mst_item_shop) {
        this.api_mst_item_shop = api_mst_item_shop;
    }

    public void setApi_mst_maparea(List<MapAreaModel> api_mst_maparea) {
        this.api_mst_maparea = api_mst_maparea;
    }

    public void setApi_mst_mapbgm(List<MapBgmModel> api_mst_mapbgm) {
        this.api_mst_mapbgm = api_mst_mapbgm;
    }

    public void setApi_mst_mapcell(List<MapCellModel> api_mst_mapcell) {
        this.api_mst_mapcell = api_mst_mapcell;
    }

    public void setApi_mst_mapinfo(List<MapInfoModel> api_mst_mapinfo) {
        this.api_mst_mapinfo = api_mst_mapinfo;
    }

    public void setApi_mst_mission(List<Mission> api_mst_mission) {
        this.api_mst_mission = api_mst_mission;
    }

    public void setApi_mst_payitem(List<PayItemModel> api_mst_payitem) {
        this.api_mst_payitem = api_mst_payitem;
    }

    public void setApi_mst_ship(List<BaseShip> api_mst_ship) {
        this.api_mst_ship = api_mst_ship;
    }

    public void setApi_mst_shipgraph(List<ShipGraphModel> api_mst_shipgraph) {
        this.api_mst_shipgraph = api_mst_shipgraph;
    }

    public void setApi_mst_shipupgrade(List<ShipUpgradeModel> api_mst_shipupgrade) {
        this.api_mst_shipupgrade = api_mst_shipupgrade;
    }

    public void setApi_mst_slotitem(List<SlotItem> api_mst_slotitem) {
        this.api_mst_slotitem = api_mst_slotitem;
    }

    public void setApi_mst_slotitem_equiptype(List<EquipTypeModel> api_mst_slotitem_equiptype) {
        this.api_mst_slotitem_equiptype = api_mst_slotitem_equiptype;
    }

    public void setApi_mst_slotitemgraph(List<SlotItemGraphModel> api_mst_slotitemgraph) {
        this.api_mst_slotitemgraph = api_mst_slotitemgraph;
    }

    public void setApi_mst_stype(List<ShipType> api_mst_stype) {
        this.api_mst_stype = api_mst_stype;
    }

    public void setApi_mst_useitem(List<UseItem> api_mst_useitem) {
        this.api_mst_useitem = api_mst_useitem;
    }
}
