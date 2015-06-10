/**
 * 
 */
package com.kancolle.server.service.member;

import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureBuyForm;
import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureChangeForm;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
public interface MemberFurnitureService {

    void changeFurniture(String member_id, FurnitureChangeForm form);

    /**
     * @param member_id
     * @param form
     */
    void buyFurniture(String member_id, FurnitureBuyForm form);
}
