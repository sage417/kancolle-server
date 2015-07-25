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
import com.kancolle.server.model.kcsapi.kcock.GetShipResult;
import com.kancolle.server.model.kcsapi.slotitem.CreateItemResult;
import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemDestoryResult;
import com.kancolle.server.model.po.member.MemberKdock;
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
    public APIResponse<MemberKdock> createShip(@ModelAttribute(MEMBER_ID) String member_id, @Valid CreateShipForm form, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException();
        }
        MemberKdock api_data = memberKdockService.createShip(member_id, form);
        return new APIResponse<MemberKdock>().setApi_data(api_data);
    }

    @RequestMapping("/createship_speedchange")
    public APIResponse<Object> speedUp(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_highspeed", required = true) boolean speedUp, @RequestParam(value = "api_kdock_id", required = true) Integer kdock_id) {
        memberKdockService.speedUp(member_id, kdock_id);
        return new APIResponse<>();
    }

    @RequestMapping("/getship")
    public APIResponse<GetShipResult> getShip(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_kdock_id", required = true) Integer kdock_id) {
        GetShipResult api_data = memberKdockService.getShip(member_id, kdock_id);
        return new APIResponse<GetShipResult>().setApi_data(api_data);
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
