package com.kancolle.server.model.kcsapi.start;

import java.util.List;

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

public class StartModel {

    private List<ShipModel> api_mst_ship;

    private List<ShipGraphModel> api_mst_shipgraph;

    private List<EquipTypeModel> api_mst_slotitem_equiptype;

    private List<ShipTypeModel> api_mst_stype;

    private List<SlotItemModel> api_mst_slotitem;

    private List<SlotItemGraphModel> api_mst_slotitemgraph;

    private List<FurnitureModel> api_mst_furniture;

    private List<FurnitureGraphModel> api_mst_furnituregraph;

    private List<UseItemModel> api_mst_useitem;

    private List<PayItemModel> api_mst_payitem;

    private ItemShopModel api_mst_item_shop;

    private List<MapAreaModel> api_mst_maparea;

    private List<MapInfoModel> api_mst_mapinfo;

    private List<MapBgmModel> api_mst_mapbgm;

    private List<MapCellModel> api_mst_mapcell;

    private List<MissionModel> api_mst_mission;

    private ConstModel api_mst_const;

    private List<ShipUpgradeModel> api_mst_shipupgrade;

    private List<BgmModel> api_mst_bgm;

    public List<ShipModel> getApi_mst_ship() {
        return api_mst_ship;
    }

    public void setApi_mst_ship(List<ShipModel> api_mst_ship) {
        this.api_mst_ship = api_mst_ship;
    }

    public List<ShipGraphModel> getApi_mst_shipgraph() {
        return api_mst_shipgraph;
    }

    public void setApi_mst_shipgraph(List<ShipGraphModel> api_mst_shipgraph) {
        this.api_mst_shipgraph = api_mst_shipgraph;
    }

    public List<EquipTypeModel> getApi_mst_slotitem_equiptype() {
        return api_mst_slotitem_equiptype;
    }

    public void setApi_mst_slotitem_equiptype(List<EquipTypeModel> api_mst_slotitem_equiptype) {
        this.api_mst_slotitem_equiptype = api_mst_slotitem_equiptype;
    }

    public List<ShipTypeModel> getApi_mst_stype() {
        return api_mst_stype;
    }

    public void setApi_mst_stype(List<ShipTypeModel> api_mst_stype) {
        this.api_mst_stype = api_mst_stype;
    }

    public List<SlotItemModel> getApi_mst_slotitem() {
        return api_mst_slotitem;
    }

    public void setApi_mst_slotitem(List<SlotItemModel> api_mst_slotitem) {
        this.api_mst_slotitem = api_mst_slotitem;
    }

    public List<SlotItemGraphModel> getApi_mst_slotitemgraph() {
        return api_mst_slotitemgraph;
    }

    public void setApi_mst_slotitemgraph(List<SlotItemGraphModel> api_mst_slotitemgraph) {
        this.api_mst_slotitemgraph = api_mst_slotitemgraph;
    }

    public List<FurnitureModel> getApi_mst_furniture() {
        return api_mst_furniture;
    }

    public void setApi_mst_furniture(List<FurnitureModel> api_mst_furniture) {
        this.api_mst_furniture = api_mst_furniture;
    }

    public List<FurnitureGraphModel> getApi_mst_furnituregraph() {
        return api_mst_furnituregraph;
    }

    public void setApi_mst_furnituregraph(List<FurnitureGraphModel> api_mst_furnituregraph) {
        this.api_mst_furnituregraph = api_mst_furnituregraph;
    }

    public List<UseItemModel> getApi_mst_useitem() {
        return api_mst_useitem;
    }

    public void setApi_mst_useitem(List<UseItemModel> api_mst_useitem) {
        this.api_mst_useitem = api_mst_useitem;
    }

    public List<PayItemModel> getApi_mst_payitem() {
        return api_mst_payitem;
    }

    public void setApi_mst_payitem(List<PayItemModel> api_mst_payitem) {
        this.api_mst_payitem = api_mst_payitem;
    }

    public ItemShopModel getApi_mst_item_shop() {
        return api_mst_item_shop;
    }

    public void setApi_mst_item_shop(ItemShopModel api_mst_item_shop) {
        this.api_mst_item_shop = api_mst_item_shop;
    }

    public List<MapAreaModel> getApi_mst_maparea() {
        return api_mst_maparea;
    }

    public void setApi_mst_maparea(List<MapAreaModel> api_mst_maparea) {
        this.api_mst_maparea = api_mst_maparea;
    }

    public List<MapInfoModel> getApi_mst_mapinfo() {
        return api_mst_mapinfo;
    }

    public void setApi_mst_mapinfo(List<MapInfoModel> api_mst_mapinfo) {
        this.api_mst_mapinfo = api_mst_mapinfo;
    }

    public List<MapBgmModel> getApi_mst_mapbgm() {
        return api_mst_mapbgm;
    }

    public void setApi_mst_mapbgm(List<MapBgmModel> api_mst_mapbgm) {
        this.api_mst_mapbgm = api_mst_mapbgm;
    }

    public List<MapCellModel> getApi_mst_mapcell() {
        return api_mst_mapcell;
    }

    public void setApi_mst_mapcell(List<MapCellModel> api_mst_mapcell) {
        this.api_mst_mapcell = api_mst_mapcell;
    }

    public List<MissionModel> getApi_mst_mission() {
        return api_mst_mission;
    }

    public void setApi_mst_mission(List<MissionModel> api_mst_mission) {
        this.api_mst_mission = api_mst_mission;
    }

    public ConstModel getApi_mst_const() {
        return api_mst_const;
    }

    public void setApi_mst_const(ConstModel api_mst_const) {
        this.api_mst_const = api_mst_const;
    }

    public List<ShipUpgradeModel> getApi_mst_shipupgrade() {
        return api_mst_shipupgrade;
    }

    public void setApi_mst_shipupgrade(List<ShipUpgradeModel> api_mst_shipupgrade) {
        this.api_mst_shipupgrade = api_mst_shipupgrade;
    }

    public List<BgmModel> getApi_mst_bgm() {
        return api_mst_bgm;
    }

    public void setApi_mst_bgm(List<BgmModel> api_mst_bgm) {
        this.api_mst_bgm = api_mst_bgm;
    }
}
