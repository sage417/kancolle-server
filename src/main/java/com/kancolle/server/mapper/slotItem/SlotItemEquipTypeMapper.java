package com.kancolle.server.mapper.slotItem;

import com.kancolle.server.model.kcsapi.start.sub.EquipTypeModel;

import java.util.List;

/**
 * Package: com.kancolle.server.mapper.slotItem
 * Author: mac
 * Date: 16/8/16
 */
public interface SlotItemEquipTypeMapper {

    List<EquipTypeModel> selectEquipTypes();
}
