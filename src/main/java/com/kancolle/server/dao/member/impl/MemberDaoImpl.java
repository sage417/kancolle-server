package com.kancolle.server.dao.member.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kancolle.server.controller.kcsapi.form.ChangeFurnitureForm;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberDeckPort;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;
import com.kancolle.server.model.kcsapi.member.MemberKdock;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.MemberRecord;
import com.kancolle.server.model.kcsapi.member.MemberSlotItem;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;
import com.kancolle.server.model.kcsapi.start.sub.ShipModel;
import com.kancolle.server.model.kcsapi.start.sub.SlotItemModel;

@Repository
public class MemberDaoImpl<T> extends BaseDaoImpl<T> implements MemberDao<T> {
    private static final String UPDATE_SHIP = "UPDATE v_member_deckport SET SHIP = :ships WHERE member_id = :member_id AND ID = :fleet_id";

    private static final String SLOT_STR = "api_slottype";

    private Map<String, Object> getMemParamMap(String value) {
        return Collections.singletonMap("member_id", value);
    }

    @Override
    public String getMemberByApiToken(String api_token) {
        return getTemplate().queryForObject("SELECT member_id FROM t_member WHERE api_token = :token", Collections.singletonMap("token", api_token), String.class);
    }

    @Override
    public MemberBasic getBasic(String member_id) {
        return queryForSingleModel(MemberBasic.class, "SELECT * FROM t_member WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberFurniture> getFurniture(String member_id) {
        return queryForModels(MemberFurniture.class, "SELECT * FROM v_member_furniture WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberSlotItem> getSlotItem(String member_id) {
        return queryForModels(MemberSlotItem.class, "SELECT * FROM v_member_slotitem WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberUseItem> getUseItem(String member_id) {
        return queryForModels(MemberUseItem.class, "SELECT * FROM v_member_useitem WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberKdock> getKdock(String member_id) {
        return queryForModels(MemberKdock.class, "SELECT * FROM t_member_kdock WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public Map<String, Object> getUnsetSlot(String member_id) {
        // 获取所有装备ID
        List<Long> all_slotitem_ids = getTemplate().queryForList("SELECT ID FROM v_member_slotitem WHERE member_id = :member_id", getMemParamMap(member_id), Long.class);
        // 获取舰娘装备信息(未处理)
        List<String> str_onslotitem_ids = getTemplate().queryForList("SELECT SLOT FROM v_member_ship WHERE member_id = :member_id", getMemParamMap(member_id), String.class);
        // 获取舰娘装备信息(处理)
        List<Long> onslotitem_ids = str_onslotitem_ids.parallelStream().map(JSON::parseArray).flatMap(array -> Arrays.asList(array.toArray(new Long[array.size()])).stream().filter(value -> value > 0)).collect(Collectors.toList());
        // 获取未装备ID
        all_slotitem_ids.removeAll(onslotitem_ids);

        List<SlotItemModel> unsetSlotitems = new ArrayList<>(all_slotitem_ids.size());

        Map<String, Object> params = new HashMap<>(2);
        params.put("member_id", member_id);

        Stream.iterate(0, n -> ++n).limit((all_slotitem_ids.size() + 100 - 1) / 100).forEach(i -> {
            int end = (i + 1) * 100;
            end = end > all_slotitem_ids.size() ? all_slotitem_ids.size() : end;

            List<Long> ids = all_slotitem_ids.subList(i * 100, end);
            params.put("ids", ids);

            unsetSlotitems.addAll(queryForModels(SlotItemModel.class, "SELECT ID,TYPE FROM v_member_slotitem WHERE member_id = :member_id AND ID IN (:ids)", params));
        });

        int slotitemTypeCount = getTemplate().queryForObject("SELECT count(*) FROM t_slotitem_equiptype", Collections.emptyMap(), int.class);

        Map<String, Object> result = new HashMap<>(slotitemTypeCount);

        Stream.iterate(1, n -> ++n).limit(slotitemTypeCount).forEach(i -> {
            List<Long> ids = unsetSlotitems.stream().filter(slotitem -> slotitem.getApi_type().getIntValue(2) == i).map(SlotItemModel::getApi_id).collect(Collectors.toList());
            // TODO
                result.put(SLOT_STR + i, JSON.toJSONString(ids));
            });
        return result;
    }

    @Transactional
    @Override
    public void destroyShip(String member_id, long api_ship_id) {
        // 删除舰娘身上装备
        Map<String, Object> params = new HashMap<String, Object>(5);
        params.put("member_id", member_id);
        params.put("api_ship_id", api_ship_id);
        String str_slotitem_ids = getTemplate().queryForObject("SELECT SLOT FROM v_member_ship WHERE member_id = :member_id AND ID = :api_ship_id", params, String.class);
        JSONArray slotitem_ids = JSON.parseArray(str_slotitem_ids);
        List<Long> ids = Stream.iterate(0, n -> ++n).limit(slotitem_ids.size()).map(i -> slotitem_ids.getLong(i)).collect(Collectors.toList());

        params.put("ids", ids);
        getTemplate().update("DELETE FROM v_member_itemslot WHERE member_id = :member_id AND ID IN (:ids)", params);
        // 如果舰娘在舰队中则移除由触发器实现
        // 删除舰娘
        int ship_id = getTemplate().queryForObject("SELECT SHIP_ID FROM v_member_ship WHERE member_id = :member_id AND ID = :api_ship_id", params, int.class);
        getTemplate().update("DELETE FROM v_member_ship WHERE member_id = :member_id AND ID = :api_ship_id", params);
        params.put("ship_id", ship_id);
        // 返还资源
        ShipModel ship = queryForSingleModel(ShipModel.class, "SELECT BROKEN FROM t_ship WHERE ID = :ship_id", params);
        JSONArray rtn_res = ship.getApi_broken();
        for (int j = 0; j < rtn_res.size(); j++) {
            params.put("value", rtn_res.getIntValue(j));
            params.put("id", j);
            getTemplate().update("UPDATE v_member_useitem SET VALUE = VALUE + :value WHERE member_id = :member_id AND ID = :id", params);
        }
    }

    @Override
    public void changeShip(String member_id, int fleet_id, long ship_id, int ship_idx) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("member_id", member_id);
        params.put("fleet_id", fleet_id);

        MemberDeckPort targetDeck = queryForSingleModel(MemberDeckPort.class, "SELECT SHIP FROM v_member_deckport WHERE member_id = :member_id AND ID = :fleet_id", params);

        if ((ship_id == -2L) && (ship_idx == -1)) {
            // 旗艦以外解除
            int count = targetDeck.removeOthers();
        } else if (ship_id == -1L) {
            targetDeck.removeShip(ship_idx);
        } else {
            params.put("ship_id", ship_id);

            long rship_id = targetDeck.indexOf(ship_idx);

            MemberDeckPort otherDock = queryForSingleModel(MemberDeckPort.class, "SELECT * FROM v_member_deckport WHERE member_id = :member_id AND SHIP LIKE '%:ship_id%'", params);

            if (ship_idx < targetDeck.size()) {
                if (otherDock != null)
                    otherDock.replaceShip(otherDock.indexOf(rship_id), targetDeck.replaceShip(ship_idx, rship_id));
                else
                    targetDeck.replaceShip(ship_idx, ship_id);
            } else {
                if (otherDock != null)
                    otherDock.removeShip(otherDock.indexOf(rship_id));
                targetDeck.addShip(ship_id);
            }

            if (otherDock != null && fleet_id != otherDock.getApi_id()) {
                params.put("fleet_id", otherDock.getApi_id());
                params.put("ships", otherDock.getApi_ship().toJSONString());
                getTemplate().update(UPDATE_SHIP, params);
            }
        }

        params.put("fleet_id", fleet_id);
        params.put("ships", targetDeck.getApi_ship().toJSONString());
        getTemplate().update(UPDATE_SHIP, params);
    }

    @Override
    public void changeFurniture(String member_id, ChangeFurnitureForm form) {
        List<Integer> ids = Arrays.asList(form.getApi_wallpaper(), form.getApi_floor(), form.getApi_desk(), form.getApi_window(), form.getApi_wallhanging(), form.getApi_shelf());

        Map<String, Object> params = new HashMap<>(2);
        params.put("member_id", member_id);
        params.put("furniture", JSON.toJSONString(ids));

        getTemplate().update("UPDATE t_member SET furniture = :furniture where member_id = :member_id", params);
    }

    @Override
    public MemberRecord getRecord(String member_id) {
        // TODO
        return null;
    }

    @Override
    public List<MemberMission> getMission(String member_id) {
        return queryForModels(MemberMission.class, "SELECT * FROM v_member_mission WHERE member_id = :member_id", getMemParamMap(member_id));
    }
}
