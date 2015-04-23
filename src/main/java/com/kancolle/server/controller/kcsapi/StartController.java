package com.kancolle.server.controller.kcsapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kcsapi")
public class StartController {

    @RequestMapping("api_start")
    @Deprecated
    public @ResponseBody String Start() {
        return "";
    }

    @RequestMapping(value = "api_start2", method = RequestMethod.POST)
    public @ResponseBody String Start2() {
        return "";
    }
}
