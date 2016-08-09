package com.kancolle.server.controller.update;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.kancolle.server.mapper.function.FunctionMapper;
import com.kancolle.server.mapper.ship.ShipGraphMapper;
import com.kancolle.server.mapper.ship.ShipMapper;
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

    @GetMapping("/ship_graph")
    public void updateShipGraph() throws IOException {
        JSONArray array = JSON.parseObject(FileUtils.readFileToString(new File("E:\\Users\\J.K.SAGE\\Desktop\\now.json"), Charset.forName("UTF-8"))).getJSONObject("api_data").getJSONArray("api_mst_shipgraph");

        List<Map<String, Object>> domains = getDomainMap(array);
        domains.forEach(shipGraphMapper::replaceShipGraph);
    }

    @GetMapping("/ship")
    void updateShip() throws IOException {
        JSONArray array = JSON.parseObject(FileUtils.readFileToString(new File("/Users/mac/Desktop/new.json"), Charset.forName("UTF-8"))).getJSONObject("api_data").getJSONArray("api_mst_ship");

        try {
            functionMapper.disableForeignKeyChecks();
            List<Map<String, Object>> domains = getDomainMap(array);
            domains.stream().filter(d -> (Integer) d.get("ID") < 501).forEach(shipMapper::replaceShip);
        } finally {
            functionMapper.enableForeignKeyChecks();
        }
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
