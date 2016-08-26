package com.kancolle.server.mapper.item;

import com.kancolle.server.model.kcsapi.start.sub.PayItemModel;

import java.util.List;

/**
 * Package: com.kancolle.server.mapper.item
 * Author: mac
 * Date: 16/8/10
 */
public interface PayItemMapper {

    List<PayItemModel> selectPayItems();
}
