package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.controller.kcsapi.form.picturebook.PictureBookForm;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;
import com.kancolle.server.model.kcsapi.member.MemberKdock;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.MemberSlotItem;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;
import com.kancolle.server.model.kcsapi.member.record.MemberRecord;
import com.kancolle.server.model.po.ship.ShipPictureBook;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberService;

@Controller
@RequestMapping(value = "/kcsapi/api_get_member", method = RequestMethod.POST)
public class GetMemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/basic")
    public @ResponseBody APIResponse<MemberBasic> basic(@ModelAttribute(MEMBER_ID) String member_id) {
        MemberBasic api_data = memberService.getBasic(member_id);
        return new APIResponse<MemberBasic>().setApi_data(api_data);
    }

    @RequestMapping("/furniture")
    public @ResponseBody APIResponse<List<MemberFurniture>> furniture(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberFurniture> api_data = memberService.getFurniture(member_id);
        return new APIResponse<List<MemberFurniture>>().setApi_data(api_data);
    }

    @RequestMapping("/kdock")
    public @ResponseBody APIResponse<List<MemberKdock>> kdock(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberKdock> api_data = memberService.getKdock(member_id);
        return new APIResponse<List<MemberKdock>>().setApi_data(api_data);
    }

    @RequestMapping("/mission")
    public @ResponseBody APIResponse<List<MemberMission>> mission(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberMission> api_data = memberService.getMission(member_id);
        return new APIResponse<List<MemberMission>>().setApi_data(api_data);
    }

    @RequestMapping("/record")
    public @ResponseBody APIResponse<MemberRecord> record(@ModelAttribute(MEMBER_ID) String member_id) {
        MemberRecord api_data = memberService.getRecord(member_id);
        return new APIResponse<MemberRecord>().setApi_data(api_data);
    }

    @RequestMapping("/slot_item")
    public @ResponseBody APIResponse<List<MemberSlotItem>> slot_item(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberSlotItem> api_data = memberService.getSlotItem(member_id);
        return new APIResponse<List<MemberSlotItem>>().setApi_data(api_data);
    }

    @RequestMapping("/unsetslot")
    public @ResponseBody APIResponse<Map<String, Object>> unsetslot(@ModelAttribute(MEMBER_ID) String member_id) {
        Map<String, Object> api_data = memberService.getUnsetSlot(member_id);
        return new APIResponse<Map<String, Object>>().setApi_data(api_data);
    }

    @RequestMapping("/useitem")
    public @ResponseBody APIResponse<List<MemberUseItem>> useitem(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberUseItem> api_data = memberService.getUseItem(member_id);
        return new APIResponse<List<MemberUseItem>>().setApi_data(api_data);
    }

    @RequestMapping("/picture_book")
    public @ResponseBody APIResponse<List<ShipPictureBook>> pictureBook(@ModelAttribute(MEMBER_ID) String member_id, @Valid PictureBookForm form, BindingResult result) {
        return null;
    }
}
