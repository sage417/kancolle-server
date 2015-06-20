package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureBuyForm;
import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureChangeForm;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberFurnitureService;

@Controller
@RequestMapping(value = "/kcsapi/api_req_furniture", method = RequestMethod.POST)
public class ReqFurnitureController {
    private static final APIResponse<Object> SUCCESS_RESPONSE = new APIResponse<Object>();

    @Autowired
    private MemberFurnitureService furnitureService;

    @RequestMapping("/buy")
    public @ResponseBody APIResponse<Object> buy(@ModelAttribute(MEMBER_ID) String member_id, @Valid FurnitureBuyForm form, BindingResult result) {
        if (result.hasErrors()) {
            // TODO
            throw new IllegalArgumentException("do not be evil");
        }

        furnitureService.buyFurniture(member_id, form);
        return SUCCESS_RESPONSE;
    }

    @RequestMapping("/change")
    public @ResponseBody APIResponse<Object> change(@ModelAttribute(MEMBER_ID) String member_id, @Valid FurnitureChangeForm form, BindingResult result) {
        if (result.hasErrors()) {
            // TODO
            throw new IllegalArgumentException("do not be evil");
        }

        furnitureService.changeFurniture(member_id, form);
        return SUCCESS_RESPONSE;
    }
}
