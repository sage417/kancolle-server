package com.kancolle.server.controller.kcsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.model.kcsapi.start.StartModel;
import com.kancolle.server.service.start.StartService;

@Controller
@RequestMapping(value = "/kcsapi", method = RequestMethod.GET)
public class StartController {

    @Autowired
    private StartService startService;

    @Deprecated
    @RequestMapping("api_start")
    public @ResponseBody String Start() {
        return "";
    }

    @RequestMapping("api_start2")
    public @ResponseBody APIResponse<StartModel> Start2() throws Exception {
        StartModel api_data = startService.getStartModel();
        return new APIResponse<StartModel>().setApi_data(api_data);
    }
}
