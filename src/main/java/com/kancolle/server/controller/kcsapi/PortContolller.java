package com.kancolle.server.controller.kcsapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kcsapi/api_port")
public class PortContolller {

    @RequestMapping("/port")
    public @ResponseBody String port() {
        return "";
    }
}
