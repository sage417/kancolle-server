/**
 * 
 */
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

import com.kancolle.server.controller.kcsapi.form.ship.ShipSetSlotForm;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.ship.MemberShipService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
@Controller
@RequestMapping(value = "/kcsapi/api_req_kaisou", method = RequestMethod.POST)
public class ReqKaisouController {

    @Autowired
    private MemberShipService memberShipService;

    @RequestMapping("/slotset")
    public @ResponseBody APIResponse<Object> slotset(@ModelAttribute(MEMBER_ID) String member_id, @Valid ShipSetSlotForm form, BindingResult result) {
        if (result.hasErrors()) {
            // TODO
            throw new IllegalArgumentException();
        }
        memberShipService.setSlot(member_id, form);
        return new APIResponse<Object>();
    }

    @RequestMapping("/unsetslot_all")
    public @ResponseBody APIResponse<Object> unsetslotAll(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_id", required = true) Long memberShip_id) {
        memberShipService.unsetslotAll(member_id, memberShip_id);
        return new APIResponse<Object>();
    }

}
