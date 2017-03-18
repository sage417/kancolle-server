package com.kancolle.server.controller.update;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.google.common.collect.Lists;
import com.kancolle.server.mapper.function.FunctionMapper;
import com.kancolle.server.mapper.furniture.FurnitureGraphMapper;
import com.kancolle.server.mapper.furniture.FurnitureMapper;
import com.kancolle.server.mapper.map.MapBGMMapper;
import com.kancolle.server.mapper.map.MapCellMapper;
import com.kancolle.server.mapper.map.MapInfoMapper;
import com.kancolle.server.mapper.ship.ShipGraphMapper;
import com.kancolle.server.mapper.ship.ShipMapper;
import com.kancolle.server.mapper.ship.ShipTypeMapper;
import com.kancolle.server.mapper.ship.ShipUpgradeMapper;
import com.kancolle.server.mapper.slotItem.SlotItemMapper;
import com.kancolle.server.mapper.useItem.UseItemMapper;
import io.reactivex.Single;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by J.K.SAGE on 2016/8/8 0008.
 */
@Profile("dev")
@RestController
@RequestMapping("/update")
public class UpdateController {
    @Autowired
    private ShipGraphMapper shipGraphMapper;
    @Autowired
    private ShipMapper shipMapper;
    @Autowired
    private FunctionMapper functionMapper;
    @Autowired
    private ShipTypeMapper shipTypeMapper;
    @Autowired
    private SlotItemMapper slotItemMapper;
    @Autowired
    private FurnitureMapper furnitureMapper;
    @Autowired
    private FurnitureGraphMapper furnitureGraphMapper;
    @Autowired
    private MapBGMMapper mapBGMMapper;
    @Autowired
    private UseItemMapper useItemMapper;
    @Autowired
    private MapCellMapper mapCellMapper;
    @Autowired
    private ShipUpgradeMapper shipUpgradeMapper;
    @Autowired
    private MapInfoMapper mapInfoMapper;
    @Autowired
    private ObserverService observerService;

    @GetMapping("/sayHelloAsync")
    Single<String> sayHello() {
        return observerService.sayHello();
    }

    @GetMapping("/sayHello")
    String say() {
        return observerService.helloWorld();
    }

    @GetMapping("/ship")
    void updateShip() throws IOException {
        JSONArray array = JSON.parseObject(IOUtils.toString(Files.newInputStream(ResourceUtils.getFile("json/star2.json").toPath()), Charset.forName("utf-8")), Feature.OrderedField).
                getJSONObject("api_data").getJSONArray("api_mst_ship");

        try {
            functionMapper.disableForeignKeyChecks();
            List<Map<String, Object>> domains = getDomainMap(array);
            domains.stream().filter(d -> (Integer) d.get("ID") < 501).forEach(shipMapper::replaceShip);
            domains.stream().filter(d -> (Integer) d.get("ID") > 613).forEach(shipMapper::insertBaseShip);
        } finally {
            functionMapper.enableForeignKeyChecks();
        }
    }

    @GetMapping("/ship_graph")
    void updateShipGraph() throws IOException {
        JSONArray array = JSON.parseObject(IOUtils.toString(Files.newInputStream(ResourceUtils.getFile("json/star2.json").toPath()), Charset.forName("utf-8")), Feature.OrderedField).
                getJSONObject("api_data").getJSONArray("api_mst_shipgraph");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(shipGraphMapper::replaceShipGraph);
    }

    @GetMapping("/shipType")
    void updateShipType() throws IOException {
        JSONArray array = JSON.parseObject(IOUtils.toString(Files.newInputStream(ResourceUtils.getFile("json/star2.json").toPath()), Charset.forName("utf-8")), Feature.OrderedField).getJSONObject("api_data").getJSONArray("api_mst_stype");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(shipTypeMapper::updateShipType);
    }

    @GetMapping("/shipSlotItem")
    void replaceSlotItem() throws IOException {
        JSONArray array = JSON.parseObject(IOUtils.toString(Files.newInputStream(ResourceUtils.getFile("json/star2.json").toPath()), Charset.forName("utf-8")), Feature.OrderedField).getJSONObject("api_data").getJSONArray("api_mst_slotitem");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(slotItemMapper::replaceSlotItem);
    }

    @GetMapping("/furniture")
    void replaceFurneture() throws IOException {
        JSONArray array = JSON.parseObject(IOUtils.toString(Files.newInputStream(ResourceUtils.getFile("json/star2.json").toPath()), Charset.forName("utf-8")), Feature.OrderedField).getJSONObject("api_data").getJSONArray("api_mst_furniture");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(furnitureMapper::replaceFurniture);
    }

    @GetMapping("/furniture_graph")
    void replaceFurnetureGraph() throws IOException {
        JSONArray array = JSON.parseObject(IOUtils.toString(Files.newInputStream(ResourceUtils.getFile("json/star2.json").toPath()), Charset.forName("utf-8")), Feature.OrderedField).getJSONObject("api_data").getJSONArray("api_mst_furnituregraph");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(furnitureGraphMapper::replaceFurnitureGraph);
    }

    @GetMapping("/map_bgm")
    void replaceMapBgm() throws IOException {
        JSONArray array = JSON.parseObject(IOUtils.toString(Files.newInputStream(ResourceUtils.getFile("json/star2.json").toPath()), Charset.forName("utf-8")), Feature.OrderedField).getJSONObject("api_data").getJSONArray("api_mst_mapbgm");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(mapBGMMapper::replaceMapBgm);
    }

    @GetMapping("/useItem")
    void replaceUseItem() throws IOException {
        JSONArray array = JSON.parseObject(IOUtils.toString(Files.newInputStream(ResourceUtils.getFile("json/star2.json").toPath()), Charset.forName("utf-8")), Feature.OrderedField).getJSONObject("api_data").getJSONArray("api_mst_useitem");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(useItemMapper::replaceUseItem);
    }

    @GetMapping("/mapcell")
    void replaceMapcell() throws IOException {
        JSONArray array = JSON.parseObject(IOUtils.toString(Files.newInputStream(ResourceUtils.getFile("json/star2.json").toPath()), Charset.forName("utf-8")), Feature.OrderedField).getJSONObject("api_data").getJSONArray("api_mst_mapcell");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(mapCellMapper::replaceMapCell);
    }

    @GetMapping("/mapinfo")
    void replaceMapInfo() throws IOException {
        JSONArray array = JSON.parseObject(IOUtils.toString(Files.newInputStream(ResourceUtils.getFile("json/star2.json").toPath()), Charset.forName("utf-8")), Feature.OrderedField).getJSONObject("api_data").getJSONArray("api_mst_mapinfo");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(mapInfoMapper::replaceMapInfo);
    }

    @GetMapping("/ship_upgrade")
    void replaceShipUpgrade() throws IOException {
        JSONArray array = JSON.parseObject(IOUtils.toString(Files.newInputStream(ResourceUtils.getFile("json/star2.json").toPath()), Charset.forName("utf-8")), Feature.OrderedField).getJSONObject("api_data").getJSONArray("api_mst_shipupgrade");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(shipUpgradeMapper::replaceShipGrade);
    }

    private List<Map<String, Object>> getDomainMap(JSONArray array) {
        List<Map<String, Object>> domains = Lists.newArrayListWithCapacity(array.size());
        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            Map<String, Object> domain = o.entrySet().stream()
                    .filter(e -> !Objects.isNull(e.getValue()))
                    .collect(Collectors.toMap(e -> StringUtils.substringAfter(e.getKey(), "api_").toUpperCase(), Map.Entry::getValue));
            domains.add(domain);
        }
        return domains;
    }
}
