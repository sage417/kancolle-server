package com.kancolle.server.controller.kcsapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kcsapi/api_req_member")
public class ReqMemberController {

    @RequestMapping("get_incentive")
    public @ResponseBody String Start2() {
        return "";
    }

}
