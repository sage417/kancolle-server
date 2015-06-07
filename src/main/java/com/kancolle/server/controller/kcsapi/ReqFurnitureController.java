package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.controller.kcsapi.form.ChangeFurnitureForm;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberFurnitureService;

@Controller
@RequestMapping("/kcsapi/api_req_furniture")
public class ReqFurnitureController {
    @Autowired
    private MemberFurnitureService furnitureService;

    @ModelAttribute(MEMBER_ID)
    public String getMemberId(HttpServletRequest request) {
        return (String) request.getAttribute(MEMBER_ID);
    }

    public void buy(@ModelAttribute(MEMBER_ID) String member_id) {
    }

    @RequestMapping("/change")
    public @ResponseBody APIResponse<Object> change(@ModelAttribute(MEMBER_ID) String member_id, ChangeFurnitureForm form) {
        furnitureService.changeFurniture(member_id, form);
        return new APIResponse<Object>();
    }
}
