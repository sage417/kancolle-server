package com.kancolle.server.controller.kcsapi;

import com.fasterxml.jackson.annotation.JsonView;
import com.kancolle.server.controller.kcsapi.form.map.MapCellForm;
import com.kancolle.server.controller.kcsapi.form.picturebook.PictureBookForm;
import com.kancolle.server.controller.kcsapi.form.ship.Ship2Form;
import com.kancolle.server.controller.kcsapi.form.ship.Ship3Form;
import com.kancolle.server.dao.port.PortDao;
import com.kancolle.server.model.kcsapi.deck.PresetDeckResponse;
import com.kancolle.server.model.kcsapi.duty.MemberDutyPageList;
import com.kancolle.server.model.kcsapi.member.MemberMaterialDto;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.record.MemberRecord;
import com.kancolle.server.model.kcsapi.picturebook.ShipPictureBookResult;
import com.kancolle.server.model.kcsapi.ship.Ship2Result;
import com.kancolle.server.model.kcsapi.ship.Ship3Result;
import com.kancolle.server.model.kcsapi.ship.ShipDeckResult;
import com.kancolle.server.model.kcsapi.start.requireInfo.RequireInfo;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.furniture.MemberFurniture;
import com.kancolle.server.model.po.map.MemberMapCell;
import com.kancolle.server.model.po.map.MemberMapInfo;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.model.po.member.MemberKdock;
import com.kancolle.server.model.po.member.MemberNdock;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.model.po.useitem.MemberUseItem;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.deckport.MemberDeckPortService;
import com.kancolle.server.service.duty.MemberDutyService;
import com.kancolle.server.service.furniture.MemberFurnitureService;
import com.kancolle.server.service.map.MemberMapService;
import com.kancolle.server.service.member.MemberKdockService;
import com.kancolle.server.service.member.MemberNDockService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.picturebook.MemberPictureBookService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.slotitem.MemberSlotItemService;
import com.kancolle.server.service.start.StartService;
import com.kancolle.server.service.useitem.MemberUseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

@RestController
@RequestMapping(value = "/kcsapi/api_get_member", method = RequestMethod.POST)
public class GetMemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberNDockService memberNDockService;

    @Autowired
    private MemberKdockService memberKdockService;

    @Autowired
    private MemberDeckPortService memberDeckPortService;

    @Autowired
    private MemberShipService memberShipService;

    @Autowired
    private MemberSlotItemService memberSlotItemService;

    @Autowired
    private MemberUseItemService memberUseItemService;

    @Autowired
    private MemberFurnitureService memberFurnitureService;

    @Autowired
    private MemberDutyService memberDutyService;

    @Autowired
    private MemberPictureBookService memberPictureBookService;

    @Autowired
    private MemberMapService memberMapService;

    @Autowired
    private StartService startService;

    @Autowired
    private PortDao portDao;

    @RequestMapping("/require_info")
    public APIResponse<RequireInfo> requireInfo(@ModelAttribute(MEMBER_ID) String member_id) {
        RequireInfo requireInfo = startService.getRequireInfo(member_id);
        return new APIResponse.Builder<RequireInfo>().data(requireInfo).build();
    }

    @JsonView(Member.MemberView.class)
    @RequestMapping("/basic")
    public APIResponse<Member> basic(@ModelAttribute(MEMBER_ID) String member_id) {
        Member api_data = memberService.getBasic(member_id);
        return new APIResponse<Member>().setApi_data(api_data);
    }

    @RequestMapping("/furniture")
    public APIResponse<List<MemberFurniture>> furniture(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberFurniture> api_data = memberFurnitureService.getFurniture(member_id);
        return new APIResponse<List<MemberFurniture>>().setApi_data(api_data);
    }

    @RequestMapping("/kdock")
    public APIResponse<List<MemberKdock>> kdock(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberKdock> api_data = memberKdockService.getMemberKdocks(member_id);
        return new APIResponse<List<MemberKdock>>().setApi_data(api_data);
    }

    @RequestMapping("/ndock")
    public APIResponse<List<MemberNdock>> ndock(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberNdock> api_data = memberNDockService.getMemberNdocks(member_id);
        return new APIResponse<List<MemberNdock>>().setApi_data(api_data);
    }

    @RequestMapping("/deck")
    public APIResponse<List<MemberDeckPort>> deck(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberDeckPort> api_data = memberDeckPortService.getMemberDeckPorts(member_id);
        return new APIResponse<List<MemberDeckPort>>().setApi_data(api_data);
    }

    @RequestMapping("/mission")
    public APIResponse<List<MemberMission>> mission(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberMission> api_data = memberService.getMission(member_id);
        return new APIResponse<List<MemberMission>>().setApi_data(api_data);
    }

    @RequestMapping("/record")
    public APIResponse<MemberRecord> record(@ModelAttribute(MEMBER_ID) String member_id) {
        MemberRecord api_data = memberService.getRecord(member_id);
        return new APIResponse<MemberRecord>().setApi_data(api_data);
    }

    @RequestMapping("/slot_item")
    public APIResponse<List<MemberSlotItem>> slot_item(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberSlotItem> api_data = memberSlotItemService.getMemberSlotItems(member_id);
        return new APIResponse<List<MemberSlotItem>>().setApi_data(api_data);
    }

    @RequestMapping("/unsetslot")
    public APIResponse<Map<String, Object>> unsetslot(@ModelAttribute(MEMBER_ID) String member_id) {
        Map<String, Object> api_data = memberSlotItemService.getUnsetSlotMap(member_id);
        return new APIResponse<Map<String, Object>>().setApi_data(api_data);
    }

    @RequestMapping("/useitem")
    public APIResponse<List<MemberUseItem>> useitem(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberUseItem> api_data = memberUseItemService.getMemberUseItems(member_id);
        return new APIResponse<List<MemberUseItem>>().setApi_data(api_data);
    }

    @RequestMapping("/payitem")
    public APIResponse<List<Object>> payitem(@ModelAttribute(MEMBER_ID) String member_id) {
        return new APIResponse<List<Object>>();
    }

    @RequestMapping("/picture_book")
    public APIResponse<ShipPictureBookResult> pictureBook(@ModelAttribute(MEMBER_ID) String member_id, @Validated PictureBookForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
        ShipPictureBookResult api_data = memberPictureBookService.pictureBook(member_id, form);
        return new APIResponse<ShipPictureBookResult>().setApi_data(api_data);
    }

    @RequestMapping("/ship2")
    public APIResponse<List<MemberShip>> ship2(@ModelAttribute(MEMBER_ID) String member_id, @Validated Ship2Form form, BindingResult result) {
        checkArgument(!result.hasErrors());
        return new Ship2Result(memberShipService.getMemberShips(member_id), memberDeckPortService.getMemberDeckPorts(member_id));
    }

    @RequestMapping("/ship3")
    public APIResponse<Ship3Result> ship3(@ModelAttribute(MEMBER_ID) String member_id, @Validated Ship3Form form, BindingResult result) {
        checkArgument(!result.hasErrors());
        Ship3Result api_data = memberShipService.getShip3(member_id, form);
        return new APIResponse<Ship3Result>().setApi_data(api_data);
    }

    @RequestMapping("/material")
    public APIResponse<List<MemberMaterialDto>> material(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberMaterialDto> api_data = portDao.getMaterial(member_id);
        return new APIResponse<List<MemberMaterialDto>>().setApi_data(api_data);
    }

    @RequestMapping("/questlist")
    public APIResponse<MemberDutyPageList> dutyList(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam("api_page_no") int pageNum) {
        MemberDutyPageList api_data = memberDutyService.getMemberDutyList(member_id, pageNum);
        return new APIResponse<MemberDutyPageList>().setApi_data(api_data);
    }

    @RequestMapping("/mapinfo")
    public APIResponse<List<MemberMapInfo>> mapInfo(@ModelAttribute(MEMBER_ID) String member_id) {
        List<MemberMapInfo> api_data = memberMapService.getMemberMapInfos(member_id);
        return new APIResponse<List<MemberMapInfo>>().setApi_data(api_data);
    }

    @RequestMapping("/mapcell")
    public APIResponse<List<MemberMapCell>> mapcell(@ModelAttribute(MEMBER_ID) String member_id, @Validated MapCellForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
        List<MemberMapCell> api_data = memberMapService.getMemberCellInfos(member_id, form);
        return new APIResponse<List<MemberMapCell>>().setApi_data(api_data);
    }

    @RequestMapping("/ship_deck")
    public APIResponse<ShipDeckResult> shipDeck(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam("api_deck_rid") int deckPortId) {
        ShipDeckResult api_data = memberShipService.getShipDeck(member_id, deckPortId);
        return new APIResponse<ShipDeckResult>().setApi_data(api_data);
    }

    @PostMapping("/preset_deck")
    public APIResponse<PresetDeckResponse> presetDeck(@ModelAttribute(MEMBER_ID) String member_id) {
        PresetDeckResponse api_data = memberDeckPortService.getMemberPresetDeckResponse(member_id);
        return new APIResponse.Builder<PresetDeckResponse>().data(api_data).build();
    }
}
