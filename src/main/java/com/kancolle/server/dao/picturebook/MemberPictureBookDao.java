package com.kancolle.server.dao.picturebook;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.ship.ShipPictureBook;

public interface MemberPictureBookDao extends BaseDao<ShipPictureBook> {

    List<ShipPictureBook> selectPictureBook(String member_id, int type, int no, int pageSize);

}
