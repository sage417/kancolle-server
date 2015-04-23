package com.kancolle.server.controller.kcsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.model.kcsapi.start.StartModel;
import com.kancolle.server.model.response.kcsapi.StartData;
import com.kancolle.server.service.start.StartService;

@Controller
@RequestMapping("/kcsapi")
public class StartController {

    @Autowired
    private StartService startService;

    @Deprecated
    @RequestMapping("api_start")
    public @ResponseBody String Start() {
        return "";
    }

    @RequestMapping(value = "api_start2", method = RequestMethod.GET)
    public @ResponseBody StartData Start2() throws Exception {
        StartModel api_data = startService.getStartModel();

        StartData response = new StartData();
        response.setApi_data(api_data);

        return response;
    }
}
