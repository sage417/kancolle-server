package com.kancolle.server.controller.kcsapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.model.response.kcsapi.get_member.BasicData;
import com.kancolle.server.model.response.kcsapi.get_member.FurnitureData;
import com.kancolle.server.model.response.kcsapi.get_member.KdockData;
import com.kancolle.server.model.response.kcsapi.get_member.SlotItemData;
import com.kancolle.server.model.response.kcsapi.get_member.UnsetSlotData;
import com.kancolle.server.model.response.kcsapi.get_member.UseItemData;

@Controller
@RequestMapping("/kcsapi/api_get_member")
public class GetMemberController {

    @RequestMapping("/basic")
    public @ResponseBody BasicData basic() {
        return new BasicData();
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
