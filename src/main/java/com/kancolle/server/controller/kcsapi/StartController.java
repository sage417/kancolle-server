package com.kancolle.server.controller.kcsapi;

import com.kancolle.server.model.kcsapi.start.StartResult;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.start.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kcsapi", method = RequestMethod.POST)
public class StartController {

    @Autowired
    private StartService startService;

    @RequestMapping("/api_start2")
    public APIResponse<StartResult> start2() throws Exception {
        StartResult api_data = startService.getStartModel();
        return new APIResponse<StartResult>().setApi_data(api_data);
    }
}
