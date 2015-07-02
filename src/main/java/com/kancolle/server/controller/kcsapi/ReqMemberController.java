package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import java.util.Collections;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kancolle.server.controller.kcsapi.form.item.UseItemForm;
import com.kancolle.server.model.kcsapi.useitem.UseItemResult;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberUseItemService;

@RestController
@RequestMapping(value = "/kcsapi/api_req_member", method = RequestMethod.POST)
public class ReqMemberController {
    private static final APIResponse<Map<String, Object>> svdata = new APIResponse<Map<String, Object>>().setApi_data(Collections.singletonMap("api_count", 0));

    @Autowired
    private MemberUseItemService memberUseItemService;

    @RequestMapping("/get_incentive")
    public APIResponse<Map<String, Object>> getIncentive(@ModelAttribute(MEMBER_ID) String member_id) {
        return svdata;
    }

    @RequestMapping("/itemuse")
    public APIResponse<Object> itemUse(@ModelAttribute(MEMBER_ID) String member_id, @Valid UseItemForm form, BindingResult result) {
        if (result.hasErrors()) {

            throw new IllegalArgumentException();
        }
        UseItemResult api_data = memberUseItemService.useItem(member_id, form);
        return new APIResponse<Object>().setApi_data(api_data);
    }
}
