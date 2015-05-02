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

import com.alibaba.fastjson.JSON;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;
import com.kancolle.server.model.kcsapi.member.MemberKdock;
import com.kancolle.server.model.kcsapi.member.MemberSlotItem;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;
import com.kancolle.server.model.kcsapi.start.sub.SlotItemModel;

@Repository
public class MemberDaoImpl<T> extends BaseDaoImpl<T> implements MemberDao<T> {
    private static final String SLOT_STR = "api_slottype";

    protected Map<String, Object> getMemParamMap(String value) {
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
        String furniture_ids = getTemplate().queryForObject("SELECT furniture FROM t_member WHERE member_id = :member_id", getMemParamMap(member_id), String.class);
        furniture_ids = furniture_ids.substring(1, furniture_ids.length() - 1);

        List<MemberFurniture> furnitures = queryForModels(MemberFurniture.class, "SELECT * FROM t_furniture WHERE ID IN (:ids)",
                Collections.singletonMap("ids", Arrays.asList(furniture_ids.split(","))));

        return furnitures.parallelStream().map(furniture -> {
            furniture.setApi_member_id(member_id);
            return furniture;
        }).collect(Collectors.toList());
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
        List<Long> onslotitem_ids = str_onslotitem_ids.parallelStream().map(JSON::parseArray)
                .flatMap(array -> Arrays.asList(array.toArray(new Long[array.size()])).stream().filter(value -> value > 0)).collect(Collectors.toList());
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
            List<Long> id = unsetSlotitems.stream().filter(slotitem -> slotitem.getApi_type().getIntValue(2) == i).mapToLong(slotitem -> slotitem.getApi_id())
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            result.put(SLOT_STR + i, JSON.toJSONString(id));
        });
        return result;
    }
}
