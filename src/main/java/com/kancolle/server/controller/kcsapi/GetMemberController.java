package com.kancolle.server.controller.kcsapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kcsapi/api_get_member")
public class GetMemberController {

    @RequestMapping("/basic")
    public @ResponseBody String basic() {
        return "";
    }

    @RequestMapping("/furniture")
    public @ResponseBody String furniture() {
        return "";
    }

    @RequestMapping("/slot_item")
    public @ResponseBody String slot_item() {
        return "";
    }

    @RequestMapping("/useitem")
    public @ResponseBody String useitem() {
        return "";
    }

    @RequestMapping("/kdock")
    public @ResponseBody String kdock() {
        return "";
    }

    @RequestMapping("/unsetslot")
    public @ResponseBody String unsetslot() {
        return "";
    }
}
