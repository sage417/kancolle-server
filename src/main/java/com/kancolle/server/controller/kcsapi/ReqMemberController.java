package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.model.response.APIResponse;

@Controller
@RequestMapping(value = "/kcsapi/api_req_member", method = RequestMethod.GET)
public class ReqMemberController {
    private static final APIResponse<Map<String, Object>> svdata = new APIResponse<Map<String, Object>>().setApi_data(Collections.singletonMap("api_count", 0));

    @RequestMapping("get_incentive")
    public @ResponseBody APIResponse<Map<String, Object>> getIncentive(@ModelAttribute(MEMBER_ID) String member_id) {
        return svdata;
    }
}
