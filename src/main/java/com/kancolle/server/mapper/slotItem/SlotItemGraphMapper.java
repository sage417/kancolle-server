package com.kancolle.server.mapper.slotItem;

import com.kancolle.server.model.kcsapi.start.sub.SlotItemGraphModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Package: com.kancolle.server.mapper.slotItem
 * Author: mac
 * Date: 16/8/10
 */
@Mapper
public interface SlotItemGraphMapper {

    List<SlotItemGraphModel> selectSlotItemGraphs();
}
