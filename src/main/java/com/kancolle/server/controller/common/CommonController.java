package com.kancolle.server.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.modle.json.APIResponse;

@Controller
@RequestMapping("/common")
public class CommonController {
    private static final APIResponse<String> RESONSE = new APIResponse<>();

    @RequestMapping("/badrequest")
    public @ResponseBody APIResponse<String> badRequest() {
        return RESONSE;
    }
}
