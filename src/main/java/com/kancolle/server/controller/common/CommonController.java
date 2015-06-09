package com.kancolle.server.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.model.response.APIResponse;

@Controller
@RequestMapping("/common")
public class CommonController {

    @RequestMapping("/tokenerror")
    public @ResponseBody() APIResponse<Object> tokenerror() {
        APIResponse<Object> api_response = new APIResponse<Object>();
        return api_response;
    }

    @RequestMapping("/error")
    public @ResponseBody() APIResponse<Object> error() {
        APIResponse<Object> api_response = new APIResponse<Object>();
        api_response.setApi_result(0);
        api_response.setApi_result_msg("失败");
        return api_response;
    }
}
