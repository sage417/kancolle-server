package com.kancolle.server.dao.member;

import java.util.List;
import java.util.Map;

import com.kancolle.server.controller.kcsapi.form.ChangeFurnitureForm;
import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;
import com.kancolle.server.model.kcsapi.member.MemberKdock;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.MemberRecord;
import com.kancolle.server.model.kcsapi.member.MemberSlotItem;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;

public interface MemberDao<T> extends BaseDao<T>{

	String getMemberByApiToken(String api_token);

	MemberBasic getBasic(String member_id);

    List<MemberFurniture> getFurniture(String member_id);

    List<MemberSlotItem> getSlotItem(String member_id);

    List<MemberUseItem> getUseItem(String member_id);

    List<MemberKdock> getKdock(String member_id);

    Map<String, Object> getUnsetSlot(String member_id);

    void destroyShip(String member_id, long api_ship_id);

    void changeShip(String member_id, int fleet_id, long ship_id, int ship_idx);

    void changeFurniture(String member_id, ChangeFurnitureForm form);

    MemberRecord getRecord(String member_id);

    List<MemberMission> getMission(String member_id);

}
