/**
 * 
 */
package com.kancolle.server.dao.useitem.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.useitem.UseItemDao;
import com.kancolle.server.model.po.useitem.UseItem;

/**
 * @author J.K.SAGE
 * @Date 2015年7月9日
 *
 */
@Repository
public class UseItemDaoImpl extends BaseDaoImpl<UseItem> implements UseItemDao {

    @Override
    public void update(UseItem useitem) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<UseItem> selectUseItems() {
        return getSqlSession().selectList("selectUseItemByCond");
    }

    @Override
    public UseItem selectUseItemById(Integer useitem_id) {
        return getSqlSession().selectOne("selectUseItemByCond", useitem_id);
    }
}
