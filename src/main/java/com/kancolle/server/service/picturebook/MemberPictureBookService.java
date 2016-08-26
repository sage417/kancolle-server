package com.kancolle.server.service.picturebook;

import com.kancolle.server.controller.kcsapi.form.picturebook.PictureBookForm;
import com.kancolle.server.model.kcsapi.picturebook.ShipPictureBookResult;

public interface MemberPictureBookService {

    ShipPictureBookResult pictureBook(String member_id, PictureBookForm form);

}
