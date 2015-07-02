/**
 * 
 */
package com.kancolle.server.service.furniture;

import java.util.List;

import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureBuyForm;
import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureChangeForm;
import com.kancolle.server.model.po.furniture.Furniture;
import com.kancolle.server.model.po.furniture.MemberFurniture;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
public interface MemberFurnitureService {

    List<MemberFurniture> getFurniture(String member_id);

    Furniture getMemberFurniture(String member_id, Integer furniture_id);

    Furniture getFurniture(Integer type, Integer no);

    void changeFurniture(String member_id, FurnitureChangeForm form);

    void buyFurniture(String member_id, FurnitureBuyForm form);

    int getCountOfMemberFurniture(String member_id);
}
