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
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.controller.kcsapi.form.ship.ShipChargeForm;
import com.kancolle.server.model.kcsapi.charge.ChargeModel;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.ship.ShipService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
@Controller
@RequestMapping(value = "/kcsapi/api_req_hokyu", method = RequestMethod.POST)
public class ReqHokyuController {
    @Autowired
    private ShipService shipService;

    @RequestMapping("/charge")
    public @ResponseBody APIResponse<ChargeModel> charge(@ModelAttribute(MEMBER_ID) String member_id, @Valid ShipChargeForm form, BindingResult result) {
        ChargeModel api_data = shipService.chargeShips(member_id, form);
        return new APIResponse<ChargeModel>().setApi_data(api_data);
    }
}
