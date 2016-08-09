package com.kancolle.server.controller.update;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.kancolle.server.mapper.ship.ShipGraphMapper;
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

    @GetMapping("/ship_graph")
    public  void updateShipGraph() throws IOException {
        JSONArray array = JSON.parseObject(FileUtils.readFileToString(new File("E:\\Users\\J.K.SAGE\\Desktop\\now.json"), Charset.forName("UTF-8"))).getJSONObject("api_data").getJSONArray("api_mst_shipgraph");

        List<Map<String, Object>> domains = Lists.newArrayListWithCapacity(array.size());
        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            Map<String, Object> domain = o.entrySet().stream().collect(Collectors.toMap(e -> StringUtils.substringAfter(e.getKey(), "api_").toUpperCase(), Map.Entry::getValue));
            domains.add(domain);
        }

        domains.forEach(shipGraphMapper::replaceShipGraph);
    }
}
