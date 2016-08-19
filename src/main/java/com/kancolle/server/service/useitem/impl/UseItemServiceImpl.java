/**
 * 
 */
package com.kancolle.server.service.useitem.impl;

import com.kancolle.server.mapper.useItem.UseItemMapper;
import com.kancolle.server.model.po.useitem.UseItem;
import com.kancolle.server.service.useitem.UseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年7月9日
 *
 */
@Service
public class UseItemServiceImpl implements UseItemService {
    @Autowired
    private UseItemMapper useItemMapper;

    @Override
    public List<UseItem> getUseItems() {
        return useItemMapper.selectUseItems();
    }

    @Override
    public UseItem getUseItemById(Integer useitem_id) {
        return useItemMapper.selectUseItemById(useitem_id);
    }
}
