package com.kancolle.server.dao.picturebook;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.picturebook.ShipPictureBook;

import java.util.List;

public interface MemberPictureBookDao extends BaseDao<ShipPictureBook> {

    List<ShipPictureBook> selectPictureBook(String member_id, int type, int no, int pageSize);

}
