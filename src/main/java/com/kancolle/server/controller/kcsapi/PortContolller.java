package com.kancolle.server.controller.kcsapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.model.response.kcsapi.port.PortData;

@Controller
@RequestMapping("/kcsapi/api_port")
public class PortContolller {

    @RequestMapping("/port")
    public @ResponseBody PortData port() {
        return new PortData();
    }
}
