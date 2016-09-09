package com.kancolle.server.controller.kcsapi;

import com.kancolle.server.controller.kcsapi.form.PresetDeckRegisterFrom;
import com.kancolle.server.controller.kcsapi.form.deckport.ShipChangeForm;
import com.kancolle.server.model.kcsapi.deck.MemberDeckPortChangeResult;
import com.kancolle.server.model.kcsapi.ship.MemberShipLockResult;
import com.kancolle.server.model.po.deckport.PresetDeck;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.deckport.MemberDeckPortService;
import com.kancolle.server.service.ship.MemberShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

import static com.google.common.base.Preconditions.checkArgument;
import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

@RestController
@RequestMapping(value = "/kcsapi/api_req_hensei", method = RequestMethod.POST)
public class ReqHenseiController {

    @Autowired
    private MemberDeckPortService memberDeckPortService;
    @Autowired
    private MemberShipService memberShipService;

    @RequestMapping("/change")
    public APIResponse<MemberDeckPortChangeResult> change(
            @ModelAttribute(MEMBER_ID) String member_id,
            @Validated ShipChangeForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
        MemberDeckPortChangeResult api_data = memberDeckPortService.changeShip(member_id, form);
        return new APIResponse<MemberDeckPortChangeResult>().setApi_data(api_data);
    }

    @RequestMapping("/lock")
    public APIResponse<MemberShipLockResult> lock(
            @ModelAttribute(MEMBER_ID) String member_id,
            @RequestParam(value = "api_ship_id") Long member_ship_id) {
        MemberShipLockResult api_data = memberShipService.lock(member_id, member_ship_id);
        return new APIResponse<MemberShipLockResult>().setApi_data(api_data);
    }

    @RequestMapping("/preset_register")
    APIResponse<PresetDeck> register(
            @ModelAttribute(MEMBER_ID) String member_id,
            @Validated PresetDeckRegisterFrom form,
            BindingResult result) {
        checkArgument(!result.hasErrors());
        PresetDeck presetDeck = memberDeckPortService.registerMemberPresetDeck(member_id, form);
        return new APIResponse.Builder<PresetDeck>().data(presetDeck).build();
    }

    @RequestMapping("/preset_delete")
    APIResponse<?> delete(
            @ModelAttribute(MEMBER_ID) String member_id,
            @Validated @Min(1L) int api_preset_no,
            BindingResult result) {
        checkArgument(!result.hasErrors());
        memberDeckPortService.deleteMemberPresetDeck(member_id, api_preset_no);
        return new APIResponse.Builder<>().build();
    }
}
