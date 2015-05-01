package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.model.json.kcsapi.get_member.BasicData;
import com.kancolle.server.model.json.kcsapi.get_member.FurnitureData;
import com.kancolle.server.model.json.kcsapi.get_member.KdockData;
import com.kancolle.server.model.json.kcsapi.get_member.SlotItemData;
import com.kancolle.server.model.json.kcsapi.get_member.UnsetSlotData;
import com.kancolle.server.model.json.kcsapi.get_member.UseItemData;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.service.member.MemberService;

@Controller
@RequestMapping("/kcsapi/api_get_member")
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
	public @ResponseBody FurnitureData furniture() {
		return new FurnitureData();
	}

	@RequestMapping("/slot_item")
	public @ResponseBody SlotItemData slot_item() {
		return new SlotItemData();
	}

	@RequestMapping("/useitem")
	public @ResponseBody UseItemData useitem() {
		return new UseItemData();
	}

	@RequestMapping("/kdock")
	public @ResponseBody KdockData kdock() {
		return new KdockData();
	}

	@RequestMapping("/unsetslot")
	public @ResponseBody UnsetSlotData unsetslot() {
		return new UnsetSlotData();
	}
}
