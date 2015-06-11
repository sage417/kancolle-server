/**
 * 
 */
package com.kancolle.server.service.member;

import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureBuyForm;
import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureChangeForm;
import com.kancolle.server.model.po.furniture.Furniture;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
public interface MemberFurnitureService {

    Furniture getMemberFurniture(String member_id, Integer furniture_id);

    Furniture getFurniture(Integer type, Integer no);

    void changeFurniture(String member_id, FurnitureChangeForm form);

    /**
     * @param member_id
     * @param form
     */
    void buyFurniture(String member_id, FurnitureBuyForm form);
}
