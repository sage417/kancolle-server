package com.kancolle.server.model.kcsapi.start;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.kcsapi.start.sub.*;
import com.kancolle.server.model.po.furniture.BGM;
import com.kancolle.server.model.po.furniture.Furniture;
import com.kancolle.server.model.po.map.MapArea;
import com.kancolle.server.model.po.map.MapCellModel;
import com.kancolle.server.model.po.mission.Mission;
import com.kancolle.server.model.po.ship.BaseShip;
import com.kancolle.server.model.po.ship.ShipType;
import com.kancolle.server.model.po.slotitem.SlotItem;
import com.kancolle.server.model.po.useitem.UseItem;

import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder(value = {
        "api_mst_ship", "api_mst_shipgraph", "api_mst_slotitem_equiptype", "api_mst_equip_exslot", "api_mst_stype",
        "api_mst_slotitem", "api_mst_furniture", "api_mst_furnituregraph",
        "api_mst_useitem", "api_mst_payitem", "api_mst_item_shop", "api_mst_maparea",
        "api_mst_mapinfo", "api_mst_mapbgm", "api_mst_mapcell", "api_mst_mission",
        "api_mst_const", "api_mst_shipupgrade", "api_mst_bgm"
})
public class StartResult implements Serializable {

    private static final long serialVersionUID = -8937536676698120512L;

    @JsonProperty(value = "api_mst_ship")
    private List<BaseShip> api_mst_ship;

    @JsonProperty(value = "api_mst_shipgraph")
    private List<ShipGraphModel> api_mst_shipgraph;

    @JsonProperty(value = "api_mst_slotitem_equiptype")
    private List<EquipTypeModel> api_mst_slotitem_equiptype;

    @JsonProperty(value = "api_mst_equip_exslot")
    private int[] api_mst_equip_exslot;

    @JsonProperty(value = "api_mst_stype")
    private List<ShipType> api_mst_stype;

    @JsonProperty(value = "api_mst_slotitem")
    private List<SlotItem> api_mst_slotitem;

    @JsonProperty(value = "api_mst_furniture")
    private List<Furniture> api_mst_furniture;

    @JsonProperty(value = "api_mst_furnituregraph")
    private List<FurnitureGraphModel> api_mst_furnituregraph;

    @JsonProperty(value = "api_mst_useitem")
    private List<UseItem> api_mst_useitem;

    @JsonProperty(value = "api_mst_payitem")
    private List<PayItemModel> api_mst_payitem;

    @JsonProperty(value = "api_mst_item_shop")
    private ItemShopModel api_mst_item_shop;

    @JsonProperty(value = "api_mst_maparea")
    private List<MapArea> api_mst_maparea;

    @JsonProperty(value = "api_mst_mapinfo")
    private List<MapInfoModel> api_mst_mapinfo;

    @JsonProperty(value = "api_mst_mapbgm")
    private List<MapBgmModel> api_mst_mapbgm;

    @JsonProperty(value = "api_mst_mapcell")
    private List<MapCellModel> api_mst_mapcell;

    @JsonProperty(value = "api_mst_mission")
    private List<Mission> api_mst_mission;

    @JsonProperty(value = "api_mst_const")
    private ConstModel api_mst_const;

    @JsonProperty(value = "api_mst_shipupgrade")
    private List<ShipUpgradeModel> api_mst_shipupgrade;

    @JsonProperty(value = "api_mst_bgm")
    private List<BGM> api_mst_bgm;

    public List<BaseShip> getApi_mst_ship() {
        return api_mst_ship;
    }

    public void setApi_mst_ship(List<BaseShip> api_mst_ship) {
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

    public int[] getApi_mst_equip_exslot() {
        return api_mst_equip_exslot;
    }

    public void setApi_mst_equip_exslot(int[] api_mst_equip_exslot) {
        this.api_mst_equip_exslot = api_mst_equip_exslot;
    }

    public List<ShipType> getApi_mst_stype() {
        return api_mst_stype;
    }

    public void setApi_mst_stype(List<ShipType> api_mst_stype) {
        this.api_mst_stype = api_mst_stype;
    }

    public List<SlotItem> getApi_mst_slotitem() {
        return api_mst_slotitem;
    }

    public void setApi_mst_slotitem(List<SlotItem> api_mst_slotitem) {
        this.api_mst_slotitem = api_mst_slotitem;
    }

    public List<Furniture> getApi_mst_furniture() {
        return api_mst_furniture;
    }

    public void setApi_mst_furniture(List<Furniture> api_mst_furniture) {
        this.api_mst_furniture = api_mst_furniture;
    }

    public List<FurnitureGraphModel> getApi_mst_furnituregraph() {
        return api_mst_furnituregraph;
    }

    public void setApi_mst_furnituregraph(List<FurnitureGraphModel> api_mst_furnituregraph) {
        this.api_mst_furnituregraph = api_mst_furnituregraph;
    }

    public List<UseItem> getApi_mst_useitem() {
        return api_mst_useitem;
    }

    public void setApi_mst_useitem(List<UseItem> api_mst_useitem) {
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

    public List<MapArea> getApi_mst_maparea() {
        return api_mst_maparea;
    }

    public void setApi_mst_maparea(List<MapArea> api_mst_maparea) {
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

    public List<Mission> getApi_mst_mission() {
        return api_mst_mission;
    }

    public void setApi_mst_mission(List<Mission> api_mst_mission) {
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

    public List<BGM> getApi_mst_bgm() {
        return api_mst_bgm;
    }

    public void setApi_mst_bgm(List<BGM> api_mst_bgm) {
        this.api_mst_bgm = api_mst_bgm;
    }
}
