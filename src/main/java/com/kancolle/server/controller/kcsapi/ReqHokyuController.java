/**
 * 
 */
package com.kancolle.server.controller.kcsapi;

import com.kancolle.server.controller.kcsapi.form.ship.ShipChargeForm;
import com.kancolle.server.model.kcsapi.charge.ChargeModel;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.ship.MemberShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.google.common.base.Preconditions.checkArgument;
import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
@RestController
@RequestMapping(value = "/kcsapi/api_req_hokyu", method = RequestMethod.POST)
public class ReqHokyuController {
    @Autowired
    private MemberShipService memberShipService;

    @RequestMapping("/charge")
    public APIResponse<ChargeModel> charge(@ModelAttribute(MEMBER_ID) String member_id, @Validated ShipChargeForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
        ChargeModel api_data = memberShipService.chargeShips(member_id, form);
        return new APIResponse<ChargeModel>().setApi_data(api_data);
    }
}
