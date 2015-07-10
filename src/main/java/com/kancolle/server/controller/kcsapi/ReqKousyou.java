/**
 * 
 */
package com.kancolle.server.controller.kcsapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemDestoryResult;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberKdockService;
import com.kancolle.server.service.slotitem.MemberSlotItemService;

/**
 * @author J.K.SAGE
 * @Date 2015年7月10日
 *
 */
@RestController
@RequestMapping(value = "/kcsapi/api_req_kousyou", method = RequestMethod.POST)
public class ReqKousyou {

    @Autowired
    private MemberKdockService memberKdockService;

    @Autowired
    private MemberSlotItemService memberSlotItemService;

    @RequestMapping("/destroyitem2")
    public APIResponse<MemberSlotItemDestoryResult> destroyitem2(@ModelAttribute("") String member_id, @RequestParam(value = "api_slotitem_ids", required = true) List<Long> slotitem_ids) {
        if (slotitem_ids.isEmpty()) {
            throw new IllegalArgumentException();
        }
        MemberSlotItemDestoryResult api_data = memberSlotItemService.destroyItemAndReturnResource(member_id, slotitem_ids);

        return new APIResponse<MemberSlotItemDestoryResult>().setApi_data(api_data);
    }

}
