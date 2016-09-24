package com.kancolle.server.mapper.slotItem;

import com.kancolle.server.model.kcsapi.start.sub.EquipTypeModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Package: com.kancolle.server.mapper.slotItem
 * Author: mac
 * Date: 16/8/16
 */
@Mapper
public interface SlotItemEquipTypeMapper {

    List<EquipTypeModel> selectEquipTypes();
}
