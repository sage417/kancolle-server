package com.kancolle.server.mapper.item;

import com.kancolle.server.model.kcsapi.start.sub.PayItemModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Package: com.kancolle.server.mapper.item
 * Author: mac
 * Date: 16/8/10
 */
@Mapper
public interface PayItemMapper {

    List<PayItemModel> selectPayItems();
}
