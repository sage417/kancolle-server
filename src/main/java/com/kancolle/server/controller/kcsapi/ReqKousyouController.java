/**
 * 
 */
package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kancolle.server.controller.kcsapi.form.item.CreateItemForm;
import com.kancolle.server.controller.kcsapi.form.kdock.CreateShipForm;
import com.kancolle.server.model.kcsapi.slotitem.CreateItemResult;
import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemDestoryResult;
import com.kancolle.server.model.po.kdock.CreateShipResult;
import com.kancolle.server.model.po.resource.MemberRescourceResult;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberKdockService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.slotitem.MemberSlotItemService;

/**
 * @author J.K.SAGE
 * @Date 2015年7月10日
 *
 */
@RestController
@RequestMapping(value = "/kcsapi/api_req_kousyou", method = RequestMethod.POST)
public class ReqKousyouController {

    @Autowired
    private MemberShipService memberShipService;

    @Autowired
    private MemberSlotItemService memberSlotItemService;

    @Autowired
    private MemberKdockService memberKdockService;

    @RequestMapping("/createship")
    public APIResponse<CreateShipResult> createShip(@ModelAttribute(MEMBER_ID) String member_id, @Valid CreateShipForm form, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException();
        }
        CreateShipResult api_data = memberKdockService.createShip(member_id, form);
        return new APIResponse<CreateShipResult>().setApi_data(api_data);
    }

    @RequestMapping("/destroyship")
    public APIResponse<MemberRescourceResult> destroyShip(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_ship_id", required = true) Long member_ship_id) {
        MemberRescourceResult api_data = memberShipService.destroyShipAndReturnResource(member_id, member_ship_id);
        return new APIResponse<MemberRescourceResult>().setApi_data(api_data);
    }

    @RequestMapping("/createitem")
    public APIResponse<CreateItemResult> createItem(@ModelAttribute(MEMBER_ID) String member_id, @Valid CreateItemForm form, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException();
        }
        CreateItemResult api_data = memberSlotItemService.createItem(member_id, form);
        return new APIResponse<CreateItemResult>().setApi_data(api_data);
    }

    @RequestMapping("/destroyitem2")
    public APIResponse<MemberSlotItemDestoryResult> destroyitem2(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_slotitem_ids", required = true) List<Long> slotitem_ids) {
        MemberSlotItemDestoryResult api_data = memberSlotItemService.destroyItemAndReturnResource(member_id, slotitem_ids);
        return new APIResponse<MemberSlotItemDestoryResult>().setApi_data(api_data);
    }
}
