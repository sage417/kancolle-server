package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.model.json.kcsapi.get_member.BasicData;
import com.kancolle.server.model.json.kcsapi.get_member.FurnitureData;
import com.kancolle.server.model.json.kcsapi.get_member.KdockData;
import com.kancolle.server.model.json.kcsapi.get_member.MissionData;
import com.kancolle.server.model.json.kcsapi.get_member.RecordData;
import com.kancolle.server.model.json.kcsapi.get_member.SlotItemData;
import com.kancolle.server.model.json.kcsapi.get_member.UnsetSlotData;
import com.kancolle.server.model.json.kcsapi.get_member.UseItemData;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;
import com.kancolle.server.model.kcsapi.member.MemberKdock;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.MemberRecord;
import com.kancolle.server.model.kcsapi.member.MemberSlotItem;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;
import com.kancolle.server.service.member.MemberService;

@Controller
@RequestMapping(value = "/kcsapi/api_get_member", method = RequestMethod.GET)
public class GetMemberController {
    @Autowired
    private MemberService memberService;

    @ModelAttribute(MEMBER_ID)
    public String getMemberId(HttpServletRequest request) {
        return (String) request.getAttribute(MEMBER_ID);
    }

    @RequestMapping("/basic")
    public @ResponseBody BasicData basic(@ModelAttribute(MEMBER_ID) String member_id) {
        MemberBasic api_data = memberService.getBasic(member_id);
        return new BasicData().setApi_data(api_data);
    }

    @RequestMapping("/furniture")
    public @ResponseBody FurnitureData furniture(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberFurniture> api_data = memberService.getFurniture(member_id);
        return new FurnitureData().setApi_data(api_data);
    }

    @RequestMapping("/slot_item")
    public @ResponseBody SlotItemData slot_item(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberSlotItem> api_data = memberService.getSlotItem(member_id);
        return new SlotItemData().setApi_data(api_data);
    }

    @RequestMapping("/useitem")
    public @ResponseBody UseItemData useitem(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberUseItem> api_data = memberService.getUseItem(member_id);
        return new UseItemData().setApi_data(api_data);
    }

    @RequestMapping("/kdock")
    public @ResponseBody KdockData kdock(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberKdock> api_data = memberService.getKdock(member_id);
        return new KdockData().setApi_data(api_data);
    }

    @RequestMapping("/unsetslot")
    public @ResponseBody UnsetSlotData unsetslot(@ModelAttribute(MEMBER_ID) String member_id) {
        Map<String, Object> api_data = memberService.getUnsetSlot(member_id);
        return new UnsetSlotData().setApi_data(api_data);
    }

    @RequestMapping("record")
    public @ResponseBody RecordData record(@ModelAttribute(MEMBER_ID) String member_id) {
        MemberRecord api_data = memberService.getRecord(member_id);
        return new RecordData().setApi_data(api_data);
    }

    @RequestMapping("mission")
    public @ResponseBody MissionData mission(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberMission> api_data = memberService.getMission(member_id);
        return new MissionData().setApi_data(api_data);
    }
}
