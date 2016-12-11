/**
 *
 */
package com.kancolle.server.controller.kcsapi;

import com.kancolle.server.controller.kcsapi.form.ship.ExChangeSlotForm;
import com.kancolle.server.controller.kcsapi.form.ship.ShipPowerUpForm;
import com.kancolle.server.controller.kcsapi.form.ship.ShipSetSlotForm;
import com.kancolle.server.model.kcsapi.ship.ExchangeSlotResult;
import com.kancolle.server.model.kcsapi.ship.MemberShipPowerUpResult;
import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemLockResult;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.slotitem.MemberSlotItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 */
@RestController
@RequestMapping(value = "/kcsapi/api_req_kaisou", method = RequestMethod.POST)
public class ReqKaisouController {

    @Autowired
    private MemberShipService memberShipService;

    @Autowired
    private MemberSlotItemService memberSlotItemService;

    @RequestMapping("/slotset")
    public APIResponse<?> slotset(@ModelAttribute(MEMBER_ID) long member_id, @Validated ShipSetSlotForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
        memberShipService.setSlot(member_id, form);
        return new APIResponse<>();
    }

    @RequestMapping("/unsetslot_all")
    public APIResponse<?> unsetslotAll(@ModelAttribute(MEMBER_ID) long member_id, @RequestParam(value = "api_id", required = true) long memberShip_id) {
        memberShipService.unSetSlotsAll(member_id, memberShip_id);
        return new APIResponse<>();
    }

    @RequestMapping("/lock")
    public APIResponse<MemberSlotItemLockResult> lock(@ModelAttribute(MEMBER_ID) long member_id, @RequestParam(value = "api_slotitem_id", required = true) Long slotitem_id) {
        MemberSlotItemLockResult api_data = memberSlotItemService.lock(member_id, slotitem_id);
        return new APIResponse<MemberSlotItemLockResult>().setApi_data(api_data);
    }

    @RequestMapping("/powerup")
    public APIResponse<MemberShipPowerUpResult> powerup(@ModelAttribute(MEMBER_ID) long member_id, @Validated ShipPowerUpForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
        MemberShipPowerUpResult api_data = memberShipService.powerUp(member_id, form);
        return new APIResponse<MemberShipPowerUpResult>().setApi_data(api_data);
    }

    @RequestMapping("/slot_exchange_index")
    public APIResponse<ExchangeSlotResult> slotExchangeIndex(@ModelAttribute(MEMBER_ID) long member_id, @Validated ExChangeSlotForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
        final ExchangeSlotResult api_data = memberShipService.exchangeIndex(member_id, form);
        return new APIResponse.Builder<ExchangeSlotResult>().data(api_data).build();
    }
}
