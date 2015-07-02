package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.controller.kcsapi.form.deckport.ShipChangeForm;
import com.kancolle.server.model.kcsapi.deck.MemberDeckPortChangeResult;
import com.kancolle.server.model.kcsapi.ship.MemberShipLockResult;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberDeckPortService;
import com.kancolle.server.service.ship.MemberShipService;

@Controller
@RequestMapping(value = "/kcsapi/api_req_hensei", method = RequestMethod.POST)
public class ReqHenseiController {
    @Autowired
    private MemberDeckPortService memberDeckPortService;

    @Autowired
    private MemberShipService memberShipService;

    @RequestMapping("/change")
    public @ResponseBody APIResponse<MemberDeckPortChangeResult> change(@ModelAttribute(MEMBER_ID) String member_id, @Valid ShipChangeForm form, BindingResult result) {
        if (result.hasErrors()) {
            // TODO
            throw new IllegalArgumentException();
        }
        MemberDeckPortChangeResult api_data = memberDeckPortService.changeShip(member_id, form);
        return new APIResponse<MemberDeckPortChangeResult>().setApi_data(api_data);
    }

    @RequestMapping("/lock")
    public @ResponseBody APIResponse<MemberShipLockResult> lock(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_ship_id", required = true) Long member_ship_id) {
        MemberShipLockResult api_data = memberShipService.lock(member_id, member_ship_id);
        return new APIResponse<MemberShipLockResult>().setApi_data(api_data);
    }
}
