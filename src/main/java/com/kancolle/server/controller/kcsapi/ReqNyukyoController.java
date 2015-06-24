/**
 * 
 */
package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.controller.kcsapi.form.ndock.NdockStartForm;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberNdockService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
@Controller
@RequestMapping(value = "/kcsapi/api_req_nyukyo", method = RequestMethod.POST)
public class ReqNyukyoController {
    private static final APIResponse<Object> RESPONSE = new APIResponse<Object>();

    @Autowired
    private MemberNdockService memberNdockService;

    @RequestMapping("/start")
    public @ResponseBody APIResponse<Object> start(@ModelAttribute(MEMBER_ID) String member_id, NdockStartForm form, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException();
        }

        memberNdockService.start(member_id, form);
        return RESPONSE;
    }

    @RequestMapping("/speedchange")
    public @ResponseBody APIResponse<Object> speedchange(@ModelAttribute(MEMBER_ID) String member_id, int api_ndock_id) {
        memberNdockService.speedchange(member_id, api_ndock_id);
        return RESPONSE;
    }
}