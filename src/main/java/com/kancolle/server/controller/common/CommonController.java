package com.kancolle.server.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/common")
public class CommonController {

    @RequestMapping("/badrequest")
    public @ResponseBody String badRequest() {
        return "";
    }
}
