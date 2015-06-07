/**
 * 
 */
package com.kancolle.server.utils.logic;

import static com.kancolle.server.model.po.furniture.FurnitureType.DESK;
import static com.kancolle.server.model.po.furniture.FurnitureType.FLOOR;
import static com.kancolle.server.model.po.furniture.FurnitureType.SHELF;
import static com.kancolle.server.model.po.furniture.FurnitureType.WALLHANGING;
import static com.kancolle.server.model.po.furniture.FurnitureType.WALLPAPER;
import static com.kancolle.server.model.po.furniture.FurnitureType.WINDOW;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.Lists;
import com.kancolle.server.controller.kcsapi.form.ChangeFurnitureForm;
import com.kancolle.server.model.po.furniture.FurnitureType;
import com.kancolle.server.utils.BeanUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
public class FurnitureUtils {
    private static final Map<FurnitureType, Function<ChangeFurnitureForm, Integer>> typeToFurnitureId;

    static {
        typeToFurnitureId = new HashMap<FurnitureType, Function<ChangeFurnitureForm, Integer>>(6);
        typeToFurnitureId.put(FLOOR, ChangeFurnitureForm::getApi_floor);
        typeToFurnitureId.put(WALLPAPER, ChangeFurnitureForm::getApi_wallpaper);
        typeToFurnitureId.put(WINDOW, ChangeFurnitureForm::getApi_window);
        typeToFurnitureId.put(WALLHANGING, ChangeFurnitureForm::getApi_wallhanging);
        typeToFurnitureId.put(SHELF, ChangeFurnitureForm::getApi_shelf);
        typeToFurnitureId.put(DESK, ChangeFurnitureForm::getApi_desk);
    }

    public static Integer getFurnitureIdByType(ChangeFurnitureForm form, FurnitureType type) {
        return typeToFurnitureId.get(type).apply(form);
    }

    public static List<Integer> getChangeFurnitureIds(ChangeFurnitureForm form) {
        int furnitureCount = (int) BeanUtils.getGetMethodStream(ChangeFurnitureForm.class, int.class).count();
        List<Integer> furnitureIds = Lists.newArrayListWithCapacity(furnitureCount);
        BeanUtils.getGetMethodStream(ChangeFurnitureForm.class, int.class).forEach(method -> {
            try {
                furnitureIds.add((int) method.invoke(form, new Object[] {}));
            } catch (Exception e) {
                // TODO LOG
                e.printStackTrace();
            }
        });
        return furnitureIds;
    }
}
