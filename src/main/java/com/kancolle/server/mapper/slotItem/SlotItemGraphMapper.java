package com.kancolle.server.mapper.slotItem;

import com.kancolle.server.model.kcsapi.start.sub.SlotItemGraphModel;

import java.util.List;

/**
 * Package: com.kancolle.server.mapper.slotItem
 * Author: mac
 * Date: 16/8/10
 */
public interface SlotItemGraphMapper {

    List<SlotItemGraphModel> selectSlotItemGraphs();
}
