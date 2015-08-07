/**
 * 
 */
package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.controller.common.AdviceController.DEFAULT_RESPONSE;
import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kancolle.server.controller.kcsapi.form.ndock.NdockStartForm;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberNdockService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
@RestController
@RequestMapping(value = "/kcsapi/api_req_nyukyo", method = RequestMethod.POST)
public class ReqNyukyoController {

    @Autowired
    private MemberNdockService memberNdockService;

    @RequestMapping("/start")
    public APIResponse<Object> start(@ModelAttribute(MEMBER_ID) String member_id, NdockStartForm form, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException();
        }

        memberNdockService.start(member_id, form);
        return DEFAULT_RESPONSE;
    }

    @RequestMapping("/speedchange")
    public APIResponse<Object> speedchange(@ModelAttribute(MEMBER_ID) String member_id, int api_ndock_id) {
        memberNdockService.speedChange(member_id, api_ndock_id);
        return DEFAULT_RESPONSE;
    }
}
