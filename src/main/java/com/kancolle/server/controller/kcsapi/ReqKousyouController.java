/**
 * 
 */
package com.kancolle.server.controller.kcsapi;

import com.kancolle.server.controller.kcsapi.form.item.CreateItemForm;
import com.kancolle.server.controller.kcsapi.form.kdock.CreateShipForm;
import com.kancolle.server.model.kcsapi.kcock.GetShipResult;
import com.kancolle.server.model.kcsapi.slotitem.CreateItemResult;
import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemDestoryResult;
import com.kancolle.server.model.po.member.MemberKdock;
import com.kancolle.server.model.po.resource.MemberResourceResult;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberKdockService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.slotitem.MemberSlotItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

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
    public APIResponse<MemberKdock> createShip(@ModelAttribute(MEMBER_ID) String member_id, @Validated CreateShipForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
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
    public APIResponse<MemberResourceResult> destroyShip(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_ship_id", required = true) Long member_ship_id) {
        MemberResourceResult api_data = memberShipService.destroyShipAndReturnResource(member_id, member_ship_id);
        return new APIResponse<MemberResourceResult>().setApi_data(api_data);
    }

    @RequestMapping("/createitem")
    public APIResponse<CreateItemResult> createItem(@ModelAttribute(MEMBER_ID) String member_id, @Validated CreateItemForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
        CreateItemResult api_data = memberSlotItemService.createItem(member_id, form);
        return new APIResponse<CreateItemResult>().setApi_data(api_data);
    }

    @RequestMapping("/destroyitem2")
    public APIResponse<MemberSlotItemDestoryResult> destroyitem2(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_slotitem_ids", required = true) List<Long> slotitem_ids) {
        MemberSlotItemDestoryResult api_data = memberSlotItemService.destroyItemAndReturnResource(member_id, slotitem_ids);
        return new APIResponse<MemberSlotItemDestoryResult>().setApi_data(api_data);
    }
}
