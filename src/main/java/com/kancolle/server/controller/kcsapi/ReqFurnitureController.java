package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kancolle.server.controller.kcsapi.form.ChangeFurnitureForm;
import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.service.member.MemberService;

@Controller
@RequestMapping("/kcsapi/api_req_furniture")
public class ReqFurnitureController {
    @Autowired
    private MemberService memberService;

    @ModelAttribute(MEMBER_ID)
    public String getMemberId(HttpServletRequest request) {
        return (String) request.getAttribute(MEMBER_ID);
    }

    @RequestMapping("change")
    public String change(@ModelAttribute(MEMBER_ID) String member_id, ChangeFurnitureForm form) {
        memberService.changeFurniture(member_id, form);
        return JSON.toJSONString(new APIResponse<Object>(), SerializerFeature.BrowserCompatible);
    }

    public void buy(@ModelAttribute(MEMBER_ID) String member_id) {
    }
}
