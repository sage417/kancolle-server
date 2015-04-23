package com.kancolle.server.controller.kcsapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.modle.response.kcsapi.req_member.GetIncentiveData;

@Controller
@RequestMapping("/kcsapi/api_req_member")
public class ReqMemberController {

    @RequestMapping("get_incentive")
    public @ResponseBody GetIncentiveData getIncentive() {
        return new GetIncentiveData();
    }

}
