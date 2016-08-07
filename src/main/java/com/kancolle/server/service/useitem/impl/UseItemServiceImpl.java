/**
 * 
 */
package com.kancolle.server.service.useitem.impl;

import com.kancolle.server.dao.useitem.UseItemDao;
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
    private UseItemDao useItemDao;

    @Override
    public List<UseItem> getUseItems() {
        return useItemDao.selectUseItems();
    }

    @Override
    public UseItem getUseItemById(Integer useitem_id) {
        return useItemDao.selectUseItemById(useitem_id);
    }
}
