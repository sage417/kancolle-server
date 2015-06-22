/**
 * 
 */
package com.kancolle.server.controller.kcsapi.form.picturebook;

import javax.validation.constraints.Min;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
public class PictureBookForm {

    @Min(value = 1)
    private int api_type;

    @Min(value = 1)
    private int api_no;

    public int getApi_type() {
        return api_type;
    }

    public void setApi_type(int api_type) {
        this.api_type = api_type;
    }

    public int getApi_no() {
        return api_no;
    }

    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }
}
