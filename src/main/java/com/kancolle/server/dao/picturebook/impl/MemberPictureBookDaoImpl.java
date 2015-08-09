package com.kancolle.server.dao.picturebook.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.picturebook.MemberPictureBookDao;
import com.kancolle.server.model.po.ship.ShipPictureBook;

@Repository
public class MemberPictureBookDaoImpl extends BaseDaoImpl<ShipPictureBook>implements MemberPictureBookDao {

    @Override
    public List<ShipPictureBook> selectPictureBook(String member_id, int type, int no, int pageSize) {
        PageHelper.startPage(no, pageSize);
        Map<String, Object> params = Maps.newHashMap();
        params.put("member_id", member_id);
        params.put("type", type);
        return getSqlSession().selectList("selectPictureBook", params);
    }
}
