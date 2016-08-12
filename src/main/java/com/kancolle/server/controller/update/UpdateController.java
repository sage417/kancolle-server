package com.kancolle.server.controller.update;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.google.common.collect.Lists;
import com.kancolle.server.mapper.function.FunctionMapper;
import com.kancolle.server.mapper.ship.ShipGraphMapper;
import com.kancolle.server.mapper.ship.ShipMapper;
import com.kancolle.server.mapper.ship.ShipTypeMapper;
import com.kancolle.server.mapper.slotItem.SlotItemMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by J.K.SAGE on 2016/8/8 0008.
 */
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

    @GetMapping("/ship_graph")
    void updateShipGraph() throws IOException {
        JSONArray array = JSON.parseObject(FileUtils.readFileToString(new File("E:\\Users\\J.K.SAGE\\Desktop\\now.json"), Charset.forName("UTF-8"))).getJSONObject("api_data").getJSONArray("api_mst_shipgraph");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(shipGraphMapper::replaceShipGraph);
    }

    @GetMapping("/ship")
    void updateShip() throws IOException {
        JSONArray array = JSON.parseObject(FileUtils.readFileToString(new File("E:\\Users\\J.K.SAGE\\Desktop\\now.json"), Charset.forName("UTF-8"))).getJSONObject("api_data").getJSONArray("api_mst_ship");

        try {
            functionMapper.disableForeignKeyChecks();
            List<Map<String, Object>> domains = getDomainMap(array);
            //domains.stream().filter(d -> (Integer) d.get("ID") < 501).forEach(shipMapper::replaceShip);
            domains.stream().filter(d -> (Integer) d.get("ID") > 613).forEach(shipMapper::insertBaseShip);
        } finally {
            functionMapper.enableForeignKeyChecks();
        }
    }

    @GetMapping("/shipType")
    void updateShipType() throws IOException {
        JSONArray array = JSON.parseObject(FileUtils.readFileToString(new File("E:\\Users\\J.K.SAGE\\Desktop\\now.json"), Charset.forName("UTF-8")), Feature.OrderedField).getJSONObject("api_data").getJSONArray("api_mst_stype");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(shipTypeMapper::updateShipType);
    }

    @GetMapping("/shipSlotItem")
    void replaceSlotItem() throws IOException {
        JSONArray array = JSON.parseObject(FileUtils.readFileToString(new File("E:\\Users\\J.K.SAGE\\Desktop\\now.json"), Charset.forName("UTF-8")), Feature.OrderedField).getJSONObject("api_data").getJSONArray("api_mst_slotitem");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(slotItemMapper::replaceSlotItem);
    }

    private List<Map<String, Object>> getDomainMap(JSONArray array) {
        List<Map<String, Object>> domains = Lists.newArrayListWithCapacity(array.size());
        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            Map<String, Object> domain = o.entrySet().stream()
                    .collect(Collectors.toMap(e -> StringUtils.substringAfter(e.getKey(), "api_").toUpperCase(), Map.Entry::getValue));
            domains.add(domain);
        }
        return domains;
    }
}
