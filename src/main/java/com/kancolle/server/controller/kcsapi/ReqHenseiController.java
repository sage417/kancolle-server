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

import com.kancolle.server.controller.kcsapi.form.deckport.ShipChangeForm;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberDeckPortService;

@Controller
@RequestMapping(value = "/kcsapi/api_req_hensei", method = RequestMethod.POST)
public class ReqHenseiController {
    @Autowired
    private MemberDeckPortService memberDeckPortService;

    @RequestMapping("/change")
    public @ResponseBody APIResponse<Object> change(@ModelAttribute(MEMBER_ID) String member_id, @Valid ShipChangeForm form, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException();
        }
        memberDeckPortService.changeShip(member_id, form);
        return new APIResponse<Object>();
    }
}
