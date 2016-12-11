package com.kancolle.server.service.picturebook.impl;

import com.kancolle.server.controller.kcsapi.form.picturebook.PictureBookForm;
import com.kancolle.server.dao.picturebook.MemberPictureBookDao;
import com.kancolle.server.model.kcsapi.picturebook.ShipPictureBookResult;
import com.kancolle.server.model.po.picturebook.ShipPictureBook;
import com.kancolle.server.service.picturebook.MemberPictureBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberPictureBookServiceImpl implements MemberPictureBookService {

    private static final int PAGE_SIZE = 70;

    @Autowired
    private MemberPictureBookDao memberPictureBookDao;

    @Override
    public ShipPictureBookResult pictureBook(long member_id, PictureBookForm form) {
        int type = form.getApi_type();
        int no = form.getApi_no();

        List<ShipPictureBook> shipList = memberPictureBookDao.selectPictureBook(member_id, type, no, PAGE_SIZE);

        return new ShipPictureBookResult(shipList);
    }

}
