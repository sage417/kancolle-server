package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.controller.common.AdviceController.DEFAULT_RESPONSE;
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

import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureBuyForm;
import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureChangeForm;
import com.kancolle.server.model.kcsapi.member.FurnitureCoinResult;
import com.kancolle.server.model.po.furniture.FurnitureBGM;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.bgm.FurnitureBGMService;
import com.kancolle.server.service.furniture.MemberFurnitureService;

@RestController
@RequestMapping(value = "/kcsapi/api_req_furniture", method = RequestMethod.POST)
public class ReqFurnitureController {

    @Autowired
    private MemberFurnitureService memberFurnitureService;

    @Autowired
    private FurnitureBGMService furnitureBGMService;

    @RequestMapping("/buy")
    public APIResponse<Object> buy(@ModelAttribute(MEMBER_ID) String member_id, @Valid FurnitureBuyForm form, BindingResult result) {
        if (result.hasErrors()) {
            // TODO
            throw new IllegalArgumentException("do not be evil");
        }

        memberFurnitureService.buyFurniture(member_id, form);
        return DEFAULT_RESPONSE;
    }

    @RequestMapping("/change")
    public APIResponse<Object> change(@ModelAttribute(MEMBER_ID) String member_id, @Valid FurnitureChangeForm form, BindingResult result) {
        if (result.hasErrors()) {
            // TODO
            throw new IllegalArgumentException("do not be evil");
        }

        memberFurnitureService.changeFurniture(member_id, form);
        return DEFAULT_RESPONSE;
    }
    
    @RequestMapping("/music_list")
    public APIResponse<List<FurnitureBGM>> musicList(@ModelAttribute(MEMBER_ID) String member_id) {
        List<FurnitureBGM> api_data = furnitureBGMService.getFurnitureBGMs();
        return new APIResponse<List<FurnitureBGM>>().setApi_data(api_data);
    }

    @RequestMapping("/music_play")
    public APIResponse<FurnitureCoinResult> musicPlay(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_music_id", required = true) String music_id) {
        FurnitureCoinResult api_data = furnitureBGMService.musicPlay(member_id, music_id);
        return new APIResponse<FurnitureCoinResult>().setApi_data(api_data);
    }

    @RequestMapping("/set_portbgm")
    public APIResponse<Object> setPortBGM(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_music_id", required = true) int music_id) {

        furnitureBGMService.setPortBGM(member_id, music_id);

        return new APIResponse<Object>();
    }
}
